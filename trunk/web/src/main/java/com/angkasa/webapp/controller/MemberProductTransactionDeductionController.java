package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.MemberProductTransactionDeductionManager;
import com.angkasa.model.MemberProductTransactionDeduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/memberProductTransactionDeductions*")
public class MemberProductTransactionDeductionController {
    private MemberProductTransactionDeductionManager memberProductTransactionDeductionManager;

    @Autowired
    public void setMemberProductTransactionDeductionManager(MemberProductTransactionDeductionManager memberProductTransactionDeductionManager) {
        this.memberProductTransactionDeductionManager = memberProductTransactionDeductionManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(memberProductTransactionDeductionManager.getAll());

//            model.addAttribute(memberProductTransactionDeductionManager.search(query, MemberProductTransactionDeduction.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(memberProductTransactionDeductionManager.getAll());
        }
        return model;
    }
}
