package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.MemberProductManager;
import com.angkasa.model.MemberProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/memberProducts*")
public class MemberProductController {
    private MemberProductManager memberProductManager;

    @Autowired
    public void setMemberProductManager(MemberProductManager memberProductManager) {
        this.memberProductManager = memberProductManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(memberProductManager.getAll());

//            model.addAttribute(memberProductManager.search(query, MemberProduct.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(memberProductManager.getAll());
        }
        return model;
    }
}
