package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.CoopBusinessTypeManager;
import com.angkasa.model.CoopBusinessType;
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
@RequestMapping("/coopBusinessTypeform*")
public class CoopBusinessTypeFormController extends BaseFormController {
    private CoopBusinessTypeManager coopBusinessTypeManager = null;

    @Autowired
    public void setCoopBusinessTypeManager(CoopBusinessTypeManager coopBusinessTypeManager) {
        this.coopBusinessTypeManager = coopBusinessTypeManager;
    }

    public CoopBusinessTypeFormController() {
        setCancelView("redirect:coopBusinessTypes");
        setSuccessView("redirect:coopBusinessTypes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected CoopBusinessType showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return coopBusinessTypeManager.get(new Long(id));
        }

        return new CoopBusinessType();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(CoopBusinessType coopBusinessType, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(coopBusinessType, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "coopBusinessTypeform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (coopBusinessType.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            coopBusinessTypeManager.remove(coopBusinessType.getId());
            saveMessage(request, getText("coopBusinessType.deleted", locale));
        } else {
            coopBusinessTypeManager.save(coopBusinessType);
            String key = (isNew) ? "coopBusinessType.added" : "coopBusinessType.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:coopBusinessTypeform?id=" + coopBusinessType.getId();
            }
        }

        return success;
    }
}
