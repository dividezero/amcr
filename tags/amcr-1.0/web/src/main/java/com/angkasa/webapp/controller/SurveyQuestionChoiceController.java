package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.SurveyQuestionChoiceManager;
import com.angkasa.model.SurveyQuestionChoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/surveyQuestionChoices*")
public class SurveyQuestionChoiceController {
    private SurveyQuestionChoiceManager surveyQuestionChoiceManager;

    @Autowired
    public void setSurveyQuestionChoiceManager(SurveyQuestionChoiceManager surveyQuestionChoiceManager) {
        this.surveyQuestionChoiceManager = surveyQuestionChoiceManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(surveyQuestionChoiceManager.search(query, SurveyQuestionChoice.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(surveyQuestionChoiceManager.getAll());
        }
        return model;
    }
}
