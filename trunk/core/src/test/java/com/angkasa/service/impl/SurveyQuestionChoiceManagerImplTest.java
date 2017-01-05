package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.SurveyQuestionChoiceDao;
import com.angkasa.model.SurveyQuestionChoice;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class SurveyQuestionChoiceManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private SurveyQuestionChoiceManagerImpl manager;

    @Mock
    private SurveyQuestionChoiceDao dao;


    @Test
    public void testGetSurveyQuestionChoice() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final SurveyQuestionChoice surveyQuestionChoice = new SurveyQuestionChoice();
        given(dao.get(id)).willReturn(surveyQuestionChoice);

        //when
        SurveyQuestionChoice result = manager.get(id);

        //then
        assertSame(surveyQuestionChoice, result);
    }

    @Test
    public void testGetSurveyQuestionChoices() {
        log.debug("testing getAll...");
        //given
        final List surveyQuestionChoices = new ArrayList();
        given(dao.getAll()).willReturn(surveyQuestionChoices);

        //when
        List result = manager.getAll();

        //then
        assertSame(surveyQuestionChoices, result);
    }

    @Test
    public void testSaveSurveyQuestionChoice() {
        log.debug("testing save...");

        //given
        final SurveyQuestionChoice surveyQuestionChoice = new SurveyQuestionChoice();
        // enter all required fields
        surveyQuestionChoice.setDescription("YnFfAfOnIzWwWfAvYeZyDhWqQuUbFzDyLwXpBhGnReDmZfSrNlSdSyEnSoBfImWbAiUoZmAvIdElFdJmSfEdUtSsArYuWrKbPyAeAwHkZePeAxWwDtKdKkOkIpAtYaZaQcBhOzZrAtAcPfCeZjBnJmTrOjBdYzIjCoKySlQxGzRyWnAePrAmFdOxJjVaEdAxBxEiDgOw");
        surveyQuestionChoice.setSequence(1644931812);
        surveyQuestionChoice.setSurveyQuestionId(-1L);
        


        given(dao.save(surveyQuestionChoice)).willReturn(surveyQuestionChoice);

        //when
        manager.save(surveyQuestionChoice);

        //then
        verify(dao).save(surveyQuestionChoice);
    }

    @Test
    public void testRemoveSurveyQuestionChoice() {
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