package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.DmsMemberUpdateManager;
import com.angkasa.model.DmsMemberUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dmsMemberUpdates*")
public class DmsMemberUpdateController {
    private DmsMemberUpdateManager dmsMemberUpdateManager;

    @Autowired
    public void setDmsMemberUpdateManager(DmsMemberUpdateManager dmsMemberUpdateManager) {
        this.dmsMemberUpdateManager = dmsMemberUpdateManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(dmsMemberUpdateManager.search(query, DmsMemberUpdate.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(dmsMemberUpdateManager.getAll());
        }
        return model;
    }
}
