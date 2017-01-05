package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.SurveyQuestionManager;
import com.angkasa.model.SurveyQuestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/surveyQuestions*")
public class SurveyQuestionController {
    private SurveyQuestionManager surveyQuestionManager;

    @Autowired
    public void setSurveyQuestionManager(SurveyQuestionManager surveyQuestionManager) {
        this.surveyQuestionManager = surveyQuestionManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(surveyQuestionManager.search(query, SurveyQuestion.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(surveyQuestionManager.getAll());
        }
        return model;
    }
}
