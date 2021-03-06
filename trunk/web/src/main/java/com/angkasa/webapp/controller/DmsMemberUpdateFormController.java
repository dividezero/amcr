package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.DmsMemberUpdateManager;
import com.angkasa.model.DmsMemberUpdate;
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
@RequestMapping("/dmsMemberUpdateform*")
public class DmsMemberUpdateFormController extends BaseFormController {
    private DmsMemberUpdateManager dmsMemberUpdateManager = null;

    @Autowired
    public void setDmsMemberUpdateManager(DmsMemberUpdateManager dmsMemberUpdateManager) {
        this.dmsMemberUpdateManager = dmsMemberUpdateManager;
    }

    public DmsMemberUpdateFormController() {
        setCancelView("redirect:dmsMemberUpdates");
        setSuccessView("redirect:dmsMemberUpdates");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected DmsMemberUpdate showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return dmsMemberUpdateManager.get(new Long(id));
        }

        return new DmsMemberUpdate();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(DmsMemberUpdate dmsMemberUpdate, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(dmsMemberUpdate, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "dmsMemberUpdateform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (dmsMemberUpdate.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            dmsMemberUpdateManager.remove(dmsMemberUpdate.getId());
            saveMessage(request, getText("dmsMemberUpdate.deleted", locale));
        } else {
            dmsMemberUpdateManager.save(dmsMemberUpdate);
            String key = (isNew) ? "dmsMemberUpdate.added" : "dmsMemberUpdate.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:dmsMemberUpdateform?id=" + dmsMemberUpdate.getId();
            }
        }

        return success;
    }
}
