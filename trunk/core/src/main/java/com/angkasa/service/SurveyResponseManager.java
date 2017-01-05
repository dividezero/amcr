package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.SurveyResponse;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface SurveyResponseManager extends GenericManager<SurveyResponse, Long> {
    
}