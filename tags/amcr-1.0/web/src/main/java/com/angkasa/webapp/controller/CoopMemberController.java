package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.CoopMemberManager;
import com.angkasa.model.CoopMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coopMembers*")
public class CoopMemberController {
    private CoopMemberManager coopMemberManager;

    @Autowired
    public void setCoopMemberManager(CoopMemberManager coopMemberManager) {
        this.coopMemberManager = coopMemberManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(coopMemberManager.search(query, CoopMember.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(coopMemberManager.getAll());
        }
        return model;
    }
}
