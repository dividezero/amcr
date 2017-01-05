package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.MemberEmployerManager;
import com.angkasa.model.MemberEmployer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/memberEmployers*")
public class MemberEmployerController {
    private MemberEmployerManager memberEmployerManager;

    @Autowired
    public void setMemberEmployerManager(MemberEmployerManager memberEmployerManager) {
        this.memberEmployerManager = memberEmployerManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(memberEmployerManager.search(query, MemberEmployer.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(memberEmployerManager.getAll());
        }
        return model;
    }
}
