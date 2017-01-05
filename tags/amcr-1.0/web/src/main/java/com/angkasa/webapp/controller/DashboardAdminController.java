package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.model.Coop;
import com.angkasa.service.CoopManager;
import com.angkasa.service.SurveyManager;
import com.angkasa.util.PropsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/dashboard*")
public class DashboardAdminController extends BaseFormController {

    @Autowired
    PropsUtil propsUtil;

    @Autowired
    SurveyManager surveyManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request)
    throws Exception {
        if(getCurrentUser()==null || !getCurrentUser().isAdminUser()){
            return new ModelAndView("redirect:/dashboard");
        }
        return new ModelAndView("dashboardAdmin");
    }

}
