package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.AmcrLookupManager;
import com.angkasa.model.AmcrLookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/amcrLookups*")
public class AmcrLookupController {
    private AmcrLookupManager amcrLookupManager;

    @Autowired
    public void setAmcrLookupManager(AmcrLookupManager amcrLookupManager) {
        this.amcrLookupManager = amcrLookupManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(amcrLookupManager.search(query, AmcrLookup.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(amcrLookupManager.getAll());
        }
        return model;
    }
}
