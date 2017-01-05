package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.CoopBusinessTypeManager;
import com.angkasa.model.CoopBusinessType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coopBusinessTypes*")
public class CoopBusinessTypeController {
    private CoopBusinessTypeManager coopBusinessTypeManager;

    @Autowired
    public void setCoopBusinessTypeManager(CoopBusinessTypeManager coopBusinessTypeManager) {
        this.coopBusinessTypeManager = coopBusinessTypeManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(coopBusinessTypeManager.search(query, CoopBusinessType.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(coopBusinessTypeManager.getAll());
        }
        return model;
    }
}
