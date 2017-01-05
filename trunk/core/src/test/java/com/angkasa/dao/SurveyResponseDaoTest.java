package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.SurveyResponse;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SurveyResponseDaoTest extends BaseDaoTestCase {
    @Autowired
    private SurveyResponseDao surveyResponseDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveSurveyResponse() {
        SurveyResponse surveyResponse = new SurveyResponse();

        // enter all required fields
        surveyResponse.setCoopMemberId(-1L);
        surveyResponse.setSurveyId(-1L);

        log.debug("adding surveyResponse...");
        surveyResponse = surveyResponseDao.save(surveyResponse);

        surveyResponse = surveyResponseDao.get(surveyResponse.getId());

        assertNotNull(surveyResponse.getId());

        log.debug("removing surveyResponse...");

        surveyResponseDao.remove(surveyResponse.getId());

        // should throw DataAccessException 
        surveyResponseDao.get(surveyResponse.getId());
    }
}