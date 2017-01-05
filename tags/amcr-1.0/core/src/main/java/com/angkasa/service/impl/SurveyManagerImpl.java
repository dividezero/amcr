package com.angkasa.service.impl;

import com.angkasa.dao.SurveyDao;
import com.angkasa.model.Survey;
import com.angkasa.service.SurveyManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("surveyManager")
@WebService(serviceName = "SurveyService", endpointInterface = "com.angkasa.service.SurveyManager")
public class SurveyManagerImpl extends GenericManagerImpl<Survey, Long> implements SurveyManager {
    SurveyDao surveyDao;

    @Autowired
    public SurveyManagerImpl(SurveyDao surveyDao) {
        super(surveyDao);
        this.surveyDao = surveyDao;
    }
}