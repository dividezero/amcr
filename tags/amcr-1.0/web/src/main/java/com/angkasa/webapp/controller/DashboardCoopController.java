package com.angkasa.webapp.controller;

import com.angkasa.service.SurveyManager;
import com.angkasa.util.PropsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/coop/dashboard*")
public class DashboardCoopController extends BaseFormController {

    @Autowired
    PropsUtil propsUtil;

    @Autowired
    SurveyManager surveyManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request)
    throws Exception {

        return new ModelAndView("dashboardCoop");
    }

}
