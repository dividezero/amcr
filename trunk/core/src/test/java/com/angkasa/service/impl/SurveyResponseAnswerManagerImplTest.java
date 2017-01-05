package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.SurveyResponseAnswerDao;
import com.angkasa.model.SurveyResponseAnswer;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class SurveyResponseAnswerManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private SurveyResponseAnswerManagerImpl manager;

    @Mock
    private SurveyResponseAnswerDao dao;


    @Test
    public void testGetSurveyResponseAnswer() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final SurveyResponseAnswer surveyResponseAnswer = new SurveyResponseAnswer();
        given(dao.get(id)).willReturn(surveyResponseAnswer);

        //when
        SurveyResponseAnswer result = manager.get(id);

        //then
        assertSame(surveyResponseAnswer, result);
    }

    @Test
    public void testGetSurveyResponseAnswers() {
        log.debug("testing getAll...");
        //given
        final List surveyResponseAnswers = new ArrayList();
        given(dao.getAll()).willReturn(surveyResponseAnswers);

        //when
        List result = manager.getAll();

        //then
        assertSame(surveyResponseAnswers, result);
    }

    @Test
    public void testSaveSurveyResponseAnswer() {
        log.debug("testing save...");

        //given
        final SurveyResponseAnswer surveyResponseAnswer = new SurveyResponseAnswer();
        // enter all required fields
        surveyResponseAnswer.setSurveyResponseId(-1L);
        


        given(dao.save(surveyResponseAnswer)).willReturn(surveyResponseAnswer);

        //when
        manager.save(surveyResponseAnswer);

        //then
        verify(dao).save(surveyResponseAnswer);
    }

    @Test
    public void testRemoveSurveyResponseAnswer() {
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