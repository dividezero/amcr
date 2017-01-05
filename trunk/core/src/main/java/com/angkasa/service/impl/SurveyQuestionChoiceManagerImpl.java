package com.angkasa.service.impl;

import com.angkasa.dao.SurveyQuestionChoiceDao;
import com.angkasa.model.SurveyQuestionChoice;
import com.angkasa.service.SurveyQuestionChoiceManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("surveyQuestionChoiceManager")
@WebService(serviceName = "SurveyQuestionChoiceService", endpointInterface = "com.angkasa.service.SurveyQuestionChoiceManager")
public class SurveyQuestionChoiceManagerImpl extends GenericManagerImpl<SurveyQuestionChoice, Long> implements SurveyQuestionChoiceManager {
    SurveyQuestionChoiceDao surveyQuestionChoiceDao;

    @Autowired
    public SurveyQuestionChoiceManagerImpl(SurveyQuestionChoiceDao surveyQuestionChoiceDao) {
        super(surveyQuestionChoiceDao);
        this.surveyQuestionChoiceDao = surveyQuestionChoiceDao;
    }
}