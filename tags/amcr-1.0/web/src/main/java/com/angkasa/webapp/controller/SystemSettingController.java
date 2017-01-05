package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.SystemSettingManager;
import com.angkasa.model.SystemSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/systemSettings*")
public class SystemSettingController {
    private SystemSettingManager systemSettingManager;

    @Autowired
    public void setSystemSettingManager(SystemSettingManager systemSettingManager) {
        this.systemSettingManager = systemSettingManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(systemSettingManager.search(query, SystemSetting.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(systemSettingManager.getAll());
        }
        return model;
    }
}
