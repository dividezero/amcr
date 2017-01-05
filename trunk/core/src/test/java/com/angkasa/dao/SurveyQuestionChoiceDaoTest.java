package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.SurveyQuestionChoice;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SurveyQuestionChoiceDaoTest extends BaseDaoTestCase {
    @Autowired
    private SurveyQuestionChoiceDao surveyQuestionChoiceDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveSurveyQuestionChoice() {
        SurveyQuestionChoice surveyQuestionChoice = new SurveyQuestionChoice();

        // enter all required fields
        surveyQuestionChoice.setDescription("TaQiWfKwUsFyPbDdBoWvRiYuCzSkKtCfHqZcCjNkHxOlCzXtNlGhRmLwAvLtEgJhSzKxViEdIsYvAfFiHeRtEvKfHzLvLqJnGaJsVuJwKzGvHoYdZuCaErQgHhJqFhNdTiUaAqDfSqNnEjJjQcHqLvPfZaYsKcSwHuVpQlZcVyCwPpEvGjZvLmSmNhKkDfIyUqKzKdXa");
        surveyQuestionChoice.setSequence(918095662);
        surveyQuestionChoice.setValue("Test Val");
        surveyQuestionChoice.setSurveyQuestionId(-1L);

        log.debug("adding surveyQuestionChoice...");
        surveyQuestionChoice = surveyQuestionChoiceDao.save(surveyQuestionChoice);

        surveyQuestionChoice = surveyQuestionChoiceDao.get(surveyQuestionChoice.getId());

        assertNotNull(surveyQuestionChoice.getId());

        log.debug("removing surveyQuestionChoice...");

        surveyQuestionChoiceDao.remove(surveyQuestionChoice.getId());

        // should throw DataAccessException 
        surveyQuestionChoiceDao.get(surveyQuestionChoice.getId());
    }
}