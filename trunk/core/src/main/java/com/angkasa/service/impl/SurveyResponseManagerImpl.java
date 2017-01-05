package com.angkasa.service.impl;

import com.angkasa.dao.SurveyResponseDao;
import com.angkasa.model.SurveyResponse;
import com.angkasa.service.SurveyResponseManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("surveyResponseManager")
@WebService(serviceName = "SurveyResponseService", endpointInterface = "com.angkasa.service.SurveyResponseManager")
public class SurveyResponseManagerImpl extends GenericManagerImpl<SurveyResponse, Long> implements SurveyResponseManager {
    SurveyResponseDao surveyResponseDao;

    @Autowired
    public SurveyResponseManagerImpl(SurveyResponseDao surveyResponseDao) {
        super(surveyResponseDao);
        this.surveyResponseDao = surveyResponseDao;
    }
}