package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.SurveyQuestion;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SurveyQuestionDaoTest extends BaseDaoTestCase {
    @Autowired
    private SurveyQuestionDao surveyQuestionDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveSurveyQuestion() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();

        // enter all required fields
        surveyQuestion.setDescription("JzFqWmUsDfLdXuEbLtFzCiCyJdUqWqHgYbSgWpGgMbOvFeIhXcDfDyFcZlJlVjScTvZrThUdUnNeXqVhXiYfOvDzSrUbRrRtPmOdOpAiAnHbCiOtUpGlLbQrLkLhOrImBnWlHkVyPyPvLwQrVkVvUsCiUrFhDjZyTnHiGsDaGkAmNzPeEfHrOqZpRoMeZdCjQvAgOrNq");
        surveyQuestion.setQuestionType("HmKiYxDeSlRbTgXmFsAiZmObVwRhAfIdDcQqJiVsNtLhZsNeRsLqDvXdGcQeEuTxUdKpVhGjCnStZeGkYcOcMvHpBfDzMiQaEmKoLrGqRnVaSsNqCaJwKvGqViHpDoXjYbWpGpQfPwZaLuAbEbAeDlYbJqIrPjYdHhDgJnUvDiEtEtOkJeArMqPeMgLxXvJdMtPiFwBs");
        surveyQuestion.setSurveyId(-1L);

        log.debug("adding surveyQuestion...");
        surveyQuestion = surveyQuestionDao.save(surveyQuestion);

        surveyQuestion = surveyQuestionDao.get(surveyQuestion.getId());

        assertNotNull(surveyQuestion.getId());

        log.debug("removing surveyQuestion...");

        surveyQuestionDao.remove(surveyQuestion.getId());

        // should throw DataAccessException 
        surveyQuestionDao.get(surveyQuestion.getId());
    }
}