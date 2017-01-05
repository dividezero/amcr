package com.angkasa.webapp.controller;

import com.angkasa.model.Coop;
import com.angkasa.model.Member;
import com.angkasa.service.CoopManager;
import org.apache.commons.lang.StringUtils;
import com.angkasa.service.CoopImportManager;
import com.angkasa.model.CoopImport;
import com.angkasa.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/coopImportform*")
public class CoopImportFormController extends BaseFormController {
    private CoopImportManager coopImportManager = null;

    @Autowired
    CoopManager coopManager;

    @Autowired
    public void setCoopImportManager(CoopImportManager coopImportManager) {
        this.coopImportManager = coopImportManager;
    }

    public CoopImportFormController() {
        setCancelView("redirect:coopImports");
        setSuccessView("redirect:coopImports");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request)
    throws Exception {

        ModelAndView model = new ModelAndView("coopImportform");
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            CoopImport coopImport = coopImportManager.get(new Long(id));

            model.addObject("coopImport", coopImport);
            model.addObject("coopList", coopImport.getCoopList());
            return model;
        }

        model.addObject("coopImport", new CoopImport());
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(CoopImport coopImport, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {

        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(coopImport, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "coopImportform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (coopImport.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            coopImportManager.remove(coopImport.getId());
            saveMessage(request, getText("coopImport.deleted", locale));
        } else {
//            coopImportManager.save(coopImport);
            if (coopImport.getId() == null) {
                coopImportManager.saveWithFile(coopImport);


            }else{
                coopImport = coopImportManager.get(coopImport.getId());
                List<Coop> coopList = coopImport.getCoopList();
                if (request.getParameter("validateAll") != null) {
                    coopList = coopManager.validateCoopImportList(coopList);
                }
                if (request.getParameter("saveAllToMembers") != null) {
                    coopList = coopManager.processCoopImportList(coopList);
                }
                coopImport.setCoopList(coopList);
                coopImportManager.save(coopImport);
            }

            String key = (isNew) ? "coopImport.added" : "coopImport.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:coopImports?id=" + coopImport.getId();
            }else if(request.getParameter("delete")==null){
                success = "redirect:coopImports?id=" + coopImport.getId();
            }
        }

        return success;
    }
}
