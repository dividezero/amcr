package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.BankManager;
import com.angkasa.model.Bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/banks*")
public class BankController {
    private BankManager bankManager;

    @Autowired
    public void setBankManager(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(bankManager.search(query, Bank.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(bankManager.getAll());
        }
        return model;
    }
}
