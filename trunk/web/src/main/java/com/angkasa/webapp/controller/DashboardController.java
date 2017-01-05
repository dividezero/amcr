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
@RequestMapping("/dashboard*")
public class DashboardController extends BaseFormController {

    @Autowired
    PropsUtil propsUtil;

    @Autowired
    SurveyManager surveyManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request)
            throws Exception {
        if (getCurrentUser() != null && getCurrentUser().isAdminUser()) {
            return new ModelAndView("redirect:/admin/dashboard");
        } else if (getCurrentUser() != null && getCurrentUser().isCoopUser()) {
            return new ModelAndView("redirect:/coop/dashboard");
        } else if (getCurrentUser() != null && getCurrentUser().isMemberUser()) {
            return new ModelAndView("redirect:/memberform");
        } else if (getCurrentUser() != null && getCurrentUser().isEventUser()) {
            return new ModelAndView("redirect:/events");
        } else {
            return new ModelAndView("redirect:/coop/dashboard");
        }

    }

}
