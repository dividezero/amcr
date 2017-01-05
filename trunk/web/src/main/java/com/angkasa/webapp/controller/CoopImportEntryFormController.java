package com.angkasa.webapp.controller;

import com.angkasa.model.Coop;
import com.angkasa.model.CoopImport;
import com.angkasa.service.CoopImportManager;
import com.angkasa.util.PropsUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/coopImportEntryform*")
public class CoopImportEntryFormController extends BaseFormController {
    private CoopImportManager coopImportManager = null;

    @Autowired
    public void setCoopImportManager(CoopImportManager coopImportManager) {
        this.coopImportManager = coopImportManager;
    }

    @Autowired
    PropsUtil propsUtil;

    public CoopImportEntryFormController() {
        setCancelView("redirect:coopImports");
        setSuccessView("redirect:coopImports");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showEntry(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView("coopImportEntryform");
        String coopImportId = request.getParameter("coopImportId");
        String importId = request.getParameter("importId");

        CoopImport coopImport = coopImportManager.get(new Long(coopImportId));

        for (Coop coop : coopImport.getCoopList()) {
            if (StringUtils.equals(coop.getImportId(), importId)) {
                coop.setCoopImportId(coopImportId);
                model.addObject("coop", coop);
                break;
            }
        }

        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmitEntry(Coop coop, BindingResult errors, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes)
            throws Exception {
        String returnUrl = "redirect:/coopImportform";

        if (request.getParameter("cancel") != null) {
            return returnUrl;
        }

        log.debug("entering 'onSubmit' method...");

        String success = getSuccessView();
        Locale locale = request.getLocale();

        CoopImport coopImport = coopImportManager.get(new Long(coop.getCoopImportId()));

        if (request.getParameter("delete") != null) {
            List<Coop> memList = coopImport.getCoopList();
            List<Coop> newList = new ArrayList<>();
            for (Coop listMem : memList) {
                if(!StringUtils.equals(listMem.getImportId(), coop.getImportId())){
                    newList.add(listMem);
                    break;
                }
            }
            coopImport.setCoopList(newList);
            saveMessage(request, getText("coopImport.deleted", locale));
        } else {
            List<Coop> coopList = coopImport.getCoopList();
            for (Coop listCoop : coopList) {
                if(StringUtils.equals(listCoop.getImportId(), coop.getImportId())){
                    listCoop = coop;
                    break;
                }
            }
            coopImport.setCoopList(coopList);

            coopImportManager.save(coopImport);

            String key =  "coopImport.updated";
            saveMessage(request, getText(key, locale));

        }

        return returnUrl;
    }
}
