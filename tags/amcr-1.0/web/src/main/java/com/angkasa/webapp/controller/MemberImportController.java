package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.model.Coop;
import com.angkasa.service.CoopManager;
import com.angkasa.service.MemberImportManager;
import com.angkasa.model.MemberImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/memberImports*")
public class MemberImportController extends BaseFormController {
    private MemberImportManager memberImportManager;

    @Autowired
    CoopManager coopManager;

    @Autowired
    public void setMemberImportManager(MemberImportManager memberImportManager) {
        this.memberImportManager = memberImportManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
            throws Exception {
        Model model = new ExtendedModelMap();

        model.addAttribute("memberImport", new MemberImport());

        List<MemberImport> memberImportList = new ArrayList<MemberImport>();
        try {
            memberImportList = memberImportManager.search(query, MemberImport.class);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            memberImportList = memberImportManager.getAll();
        }
        if (getCurrentUser() != null && getCurrentUser().isCoopUser()) {
            Coop coop = coopManager.getByUserId(getCurrentUser().getId());
            List<MemberImport> result = new ArrayList<MemberImport>();
            for (MemberImport memberImport : memberImportList) {
                if (memberImport.getCoopId() != null && memberImport.getCoopId().equals(coop.getId())) {
                    result.add(memberImport);
                }
            }
            model.addAttribute(result);
        } else {
            model.addAttribute(memberImportList);
        }
        return model;
    }
}
