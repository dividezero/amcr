package com.angkasa.webapp.controller;

import com.angkasa.model.Employer;
import com.angkasa.model.EmployerImport;
import com.angkasa.service.EmployerImportManager;
import com.angkasa.util.XlsxParserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/employerImportform*")
public class EmployerImportFormController extends BaseFormController {
    private EmployerImportManager employerImportManager = null;

    @Autowired
    public void setEmployerImportManager(EmployerImportManager employerImportManager) {
        this.employerImportManager = employerImportManager;
    }

    public EmployerImportFormController() {
        setCancelView("redirect:employerImports");
        setSuccessView("redirect:employerImports");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request)
    throws Exception {

        ModelAndView model = new ModelAndView("employerImportform");
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            EmployerImport employerImport = employerImportManager.get(new Long(id));

            model.addObject("employerImport", employerImport);
            model.addObject("empList", employerImport.getEmployerList());

            return model;
        }

        model.addObject("employerImport", new EmployerImport());
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(EmployerImport employerImport, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(employerImport, errors);
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "employerImportform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (employerImport.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            employerImportManager.remove(employerImport.getId());
            saveMessage(request, getText("employerImport.deleted", locale));
        } else {

            if(employerImport.getId() == null){
                employerImportManager.saveWithFile(employerImport);
            }

            String key = (isNew) ? "employerImport.added" : "employerImport.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:employerImportform?id=" + employerImport.getId();
            }
        }

        return success;
    }
}
