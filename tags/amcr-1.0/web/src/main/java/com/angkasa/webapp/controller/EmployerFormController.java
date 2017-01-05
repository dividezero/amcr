package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.EmployerManager;
import com.angkasa.model.Employer;
import com.angkasa.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/employerform*")
public class EmployerFormController extends BaseFormController {
    private EmployerManager employerManager = null;

    @Autowired
    public void setEmployerManager(EmployerManager employerManager) {
        this.employerManager = employerManager;
    }

    public EmployerFormController() {
        setCancelView("redirect:employers");
        setSuccessView("redirect:employers");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Employer showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return employerManager.get(new Long(id));
        }

        return new Employer();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Employer employer, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(employer, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "employerform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (employer.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            employerManager.remove(employer.getId());
            saveMessage(request, getText("employer.deleted", locale));
        } else {
            employerManager.save(employer);
            String key = (isNew) ? "employer.added" : "employer.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:employerform?id=" + employer.getId();
            }
        }

        return success;
    }
}
