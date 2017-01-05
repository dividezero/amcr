package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.SurveyResponseAnswerManager;
import com.angkasa.model.SurveyResponseAnswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/surveyResponseAnswers*")
public class SurveyResponseAnswerController {
    private SurveyResponseAnswerManager surveyResponseAnswerManager;

    @Autowired
    public void setSurveyResponseAnswerManager(SurveyResponseAnswerManager surveyResponseAnswerManager) {
        this.surveyResponseAnswerManager = surveyResponseAnswerManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(surveyResponseAnswerManager.search(query, SurveyResponseAnswer.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(surveyResponseAnswerManager.getAll());
        }
        return model;
    }
}
