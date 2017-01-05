package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.EventMemberManager;
import com.angkasa.model.EventMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/eventMembers*")
public class EventMemberController {
    private EventMemberManager eventMemberManager;

    @Autowired
    public void setEventMemberManager(EventMemberManager eventMemberManager) {
        this.eventMemberManager = eventMemberManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(eventMemberManager.search(query, EventMember.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(eventMemberManager.getAll());
        }
        return model;
    }
}
