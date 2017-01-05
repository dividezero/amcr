package com.angkasa.webapp.controller;

import com.angkasa.Constants;
import com.angkasa.dao.SearchException;
import com.angkasa.service.CoopManager;
import com.angkasa.model.Coop;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coops*")
public class CoopController extends BaseFormController {
    private CoopManager coopManager;

    @Autowired
    public void setCoopManager(CoopManager coopManager) {
        this.coopManager = coopManager;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query, HttpServletRequest request)
            throws Exception {
        Model model = new ExtendedModelMap();
        String view = request.getParameter("view");
        model.addAttribute("view", view);

        List<Coop> coopList = new ArrayList<Coop>();
        try {
            coopList = coopManager.search(query, Coop.class);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            coopList = coopManager.getAll();
        }
        if (StringUtils.isNotBlank(view) && view.equals("preloaded")) {
            List<Coop> all = new ArrayList<Coop>();
            all.addAll(coopList);
            coopList.clear();
            for (Coop coop : all) {
                if (coop.getTypeFlag().equals(Constants.COOP_FLAG_ANGKASA)
                        || coop.getTypeFlag().equals(Constants.COOP_FLAG_COOP)) {
                    coopList.add(coop);
                }
            }
        } else if (StringUtils.isNotBlank(view) && view.equals("new")) {
            List<Coop> all = new ArrayList<Coop>();
            all.addAll(coopList);
            coopList.clear();
            for (Coop coop : all) {
                if (coop.getTypeFlag().equals(Constants.COOP_FLAG_NEW)) {
                    coopList.add(coop);
                }
            }
        }
        model.addAttribute(coopList);

        return model;
    }

}
