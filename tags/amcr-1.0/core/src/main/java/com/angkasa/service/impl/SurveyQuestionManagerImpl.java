package com.angkasa.service.impl;

import com.angkasa.dao.SurveyQuestionDao;
import com.angkasa.model.SurveyQuestion;
import com.angkasa.service.SurveyQuestionManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("surveyQuestionManager")
@WebService(serviceName = "SurveyQuestionService", endpointInterface = "com.angkasa.service.SurveyQuestionManager")
public class SurveyQuestionManagerImpl extends GenericManagerImpl<SurveyQuestion, Long> implements SurveyQuestionManager {
    SurveyQuestionDao surveyQuestionDao;

    @Autowired
    public SurveyQuestionManagerImpl(SurveyQuestionDao surveyQuestionDao) {
        super(surveyQuestionDao);
        this.surveyQuestionDao = surveyQuestionDao;
    }
}