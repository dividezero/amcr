package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.Survey;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface SurveyManager extends GenericManager<Survey, Long> {
    
}