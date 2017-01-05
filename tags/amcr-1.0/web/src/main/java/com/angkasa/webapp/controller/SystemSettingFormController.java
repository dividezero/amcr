package com.angkasa.webapp.controller;

import com.angkasa.util.PropsUtil;
import org.apache.commons.lang.StringUtils;
import com.angkasa.service.SystemSettingManager;
import com.angkasa.model.SystemSetting;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/systemSettingform*")
public class SystemSettingFormController extends BaseFormController {
    private SystemSettingManager systemSettingManager = null;

    @Autowired
    public void setSystemSettingManager(SystemSettingManager systemSettingManager) {
        this.systemSettingManager = systemSettingManager;
    }

    @Autowired
    private PropsUtil propsUtil;

    public SystemSettingFormController() {
        setCancelView("redirect:systemSettings");
        setSuccessView("redirect:systemSettings");
    }

    @ModelAttribute("booleanList")
    public Map<String, String> getGenderList() {
        return propsUtil.getBooleanList();
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected SystemSetting showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return systemSettingManager.get(new Long(id));
        }

        return new SystemSetting();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(SystemSetting systemSetting, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(systemSetting, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "systemSettingform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (systemSetting.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            systemSettingManager.remove(systemSetting.getId());
            saveMessage(request, getText("systemSetting.deleted", locale));
        } else {
            systemSettingManager.save(systemSetting);
            String key = (isNew) ? "systemSetting.added" : "systemSetting.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:systemSettingform?id=" + systemSetting.getId();
            }
        }

        return success;
    }
}
