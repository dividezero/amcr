package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.SurveyQuestionDao;
import com.angkasa.model.SurveyQuestion;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class SurveyQuestionManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private SurveyQuestionManagerImpl manager;

    @Mock
    private SurveyQuestionDao dao;


    @Test
    public void testGetSurveyQuestion() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final SurveyQuestion surveyQuestion = new SurveyQuestion();
        given(dao.get(id)).willReturn(surveyQuestion);

        //when
        SurveyQuestion result = manager.get(id);

        //then
        assertSame(surveyQuestion, result);
    }

    @Test
    public void testGetSurveyQuestions() {
        log.debug("testing getAll...");
        //given
        final List surveyQuestions = new ArrayList();
        given(dao.getAll()).willReturn(surveyQuestions);

        //when
        List result = manager.getAll();

        //then
        assertSame(surveyQuestions, result);
    }

    @Test
    public void testSaveSurveyQuestion() {
        log.debug("testing save...");

        //given
        final SurveyQuestion surveyQuestion = new SurveyQuestion();
        // enter all required fields
        surveyQuestion.setDescription("SqYlAqDgAwHsSjRiLkJsXfXwDwKjVsKnQbEcTzZlOyWcBaAmSwGeWxNaGfDgMwLhOnEpTmPlRgHbOgWzRnRkWaEjScCjCaPvVjJjOiPtLcYeQgZxQxBvMzQtGkQnPjHrIlPpWjOcEiKaNfDuXcHjVaBnYgHrMxEeFsKbDtSdEhZnOlIuDaSxYkEpEqFoZfCfPsBhMwQg");
        surveyQuestion.setQuestionType("HuFjZkNqUmUhCcMdEbTbCdFsGvGdSkYsEkYwXiEaZdJhOlYzNjTiMwJyQjXkXtWwLcEuJvJmUcDxSbTyGzLqBjSoVbErXtKvGyQuXuEbJcIyJjNsVkGaBwUiNtTpDkGjCdGrEsZpRyNtYwMoTrHrOsJtEnLkXeOxTjFkZcKaIzZmQzBxZlOaVlBdMuZiRfJwSjSzGbGe");
        surveyQuestion.setSurveyId(-1L);
        


        given(dao.save(surveyQuestion)).willReturn(surveyQuestion);

        //when
        manager.save(surveyQuestion);

        //then
        verify(dao).save(surveyQuestion);
    }

    @Test
    public void testRemoveSurveyQuestion() {
        log.debug("testing remove...");

        //given
        final Long id = -11L;
        willDoNothing().given(dao).remove(id);

        //when
        manager.remove(id);

        //then
        verify(dao).remove(id);
    }
}