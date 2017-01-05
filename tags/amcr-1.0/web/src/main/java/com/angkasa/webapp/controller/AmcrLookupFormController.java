package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.AmcrLookupManager;
import com.angkasa.model.AmcrLookup;
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
@RequestMapping("/amcrLookupform*")
public class AmcrLookupFormController extends BaseFormController {
    private AmcrLookupManager amcrLookupManager = null;

    @Autowired
    public void setAmcrLookupManager(AmcrLookupManager amcrLookupManager) {
        this.amcrLookupManager = amcrLookupManager;
    }

    public AmcrLookupFormController() {
        setCancelView("redirect:amcrLookups");
        setSuccessView("redirect:amcrLookups");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected AmcrLookup showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return amcrLookupManager.get(new Long(id));
        }

        return new AmcrLookup();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(AmcrLookup amcrLookup, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(amcrLookup, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "amcrLookupform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (amcrLookup.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            amcrLookupManager.remove(amcrLookup.getId());
            saveMessage(request, getText("amcrLookup.deleted", locale));
        } else {
            amcrLookupManager.save(amcrLookup);
            String key = (isNew) ? "amcrLookup.added" : "amcrLookup.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:amcrLookupform?id=" + amcrLookup.getId();
            }
        }

        return success;
    }
}
