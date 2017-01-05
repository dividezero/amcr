package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.Survey;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SurveyDaoTest extends BaseDaoTestCase {
    @Autowired
    private SurveyDao surveyDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveSurvey() {
        Survey survey = new Survey();

        // enter all required fields
        survey.setName("CkMkVlKvFyLiPgIlDiRdCsWnLuQlLvMbUvLgTqWbKbJmQeDpDp");

        log.debug("adding survey...");
        survey = surveyDao.save(survey);

        survey = surveyDao.get(survey.getId());

        assertNotNull(survey.getId());

        log.debug("removing survey...");

        surveyDao.remove(survey.getId());

        // should throw DataAccessException 
        surveyDao.get(survey.getId());
    }
}