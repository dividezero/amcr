package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.EmployerManager;
import com.angkasa.model.Employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employers*")
public class EmployerController {
    private EmployerManager employerManager;

    @Autowired
    public void setEmployerManager(EmployerManager employerManager) {
        this.employerManager = employerManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(employerManager.search(query, Employer.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(employerManager.getAll());
        }
        return model;
    }
}
