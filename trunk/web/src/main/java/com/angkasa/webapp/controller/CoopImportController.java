package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.CoopImportManager;
import com.angkasa.model.CoopImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coopImports*")
public class CoopImportController {
    private CoopImportManager coopImportManager;

    @Autowired
    public void setCoopImportManager(CoopImportManager coopImportManager) {
        this.coopImportManager = coopImportManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();

        model.addAttribute("coopImport", new CoopImport());
        try {
            model.addAttribute(coopImportManager.search(query, CoopImport.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(coopImportManager.getAll());
        }
        return model;
    }
}
