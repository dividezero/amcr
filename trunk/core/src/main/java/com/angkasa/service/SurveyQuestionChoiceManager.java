package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.SurveyQuestionChoice;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface SurveyQuestionChoiceManager extends GenericManager<SurveyQuestionChoice, Long> {
    
}