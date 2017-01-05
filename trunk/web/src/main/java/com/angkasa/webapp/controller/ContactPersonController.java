package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.ContactPersonManager;
import com.angkasa.model.ContactPerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contactPersons*")
public class ContactPersonController {
    private ContactPersonManager contactPersonManager;

    @Autowired
    public void setContactPersonManager(ContactPersonManager contactPersonManager) {
        this.contactPersonManager = contactPersonManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(contactPersonManager.search(query, ContactPerson.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(contactPersonManager.getAll());
        }
        return model;
    }
}
