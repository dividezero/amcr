package com.angkasa.service.impl;

import com.angkasa.dao.SurveyResponseAnswerDao;
import com.angkasa.model.SurveyResponseAnswer;
import com.angkasa.service.SurveyResponseAnswerManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("surveyResponseAnswerManager")
@WebService(serviceName = "SurveyResponseAnswerService", endpointInterface = "com.angkasa.service.SurveyResponseAnswerManager")
public class SurveyResponseAnswerManagerImpl extends GenericManagerImpl<SurveyResponseAnswer, Long> implements SurveyResponseAnswerManager {
    SurveyResponseAnswerDao surveyResponseAnswerDao;

    @Autowired
    public SurveyResponseAnswerManagerImpl(SurveyResponseAnswerDao surveyResponseAnswerDao) {
        super(surveyResponseAnswerDao);
        this.surveyResponseAnswerDao = surveyResponseAnswerDao;
    }
}