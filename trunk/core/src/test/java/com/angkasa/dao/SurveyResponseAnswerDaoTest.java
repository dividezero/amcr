package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.SurveyResponseAnswer;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SurveyResponseAnswerDaoTest extends BaseDaoTestCase {
    @Autowired
    private SurveyResponseAnswerDao surveyResponseAnswerDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveSurveyResponseAnswer() {
        SurveyResponseAnswer surveyResponseAnswer = new SurveyResponseAnswer();

        // enter all required fields
        surveyResponseAnswer.setSurveyResponseId(-1L);

        log.debug("adding surveyResponseAnswer...");
        surveyResponseAnswer = surveyResponseAnswerDao.save(surveyResponseAnswer);

        surveyResponseAnswer = surveyResponseAnswerDao.get(surveyResponseAnswer.getId());

        assertNotNull(surveyResponseAnswer.getId());

        log.debug("removing surveyResponseAnswer...");

        surveyResponseAnswerDao.remove(surveyResponseAnswer.getId());

        // should throw DataAccessException 
        surveyResponseAnswerDao.get(surveyResponseAnswer.getId());
    }
}