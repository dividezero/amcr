package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.SurveyResponseDao;
import com.angkasa.model.SurveyResponse;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class SurveyResponseManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private SurveyResponseManagerImpl manager;

    @Mock
    private SurveyResponseDao dao;


    @Test
    public void testGetSurveyResponse() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final SurveyResponse surveyResponse = new SurveyResponse();
        given(dao.get(id)).willReturn(surveyResponse);

        //when
        SurveyResponse result = manager.get(id);

        //then
        assertSame(surveyResponse, result);
    }

    @Test
    public void testGetSurveyResponses() {
        log.debug("testing getAll...");
        //given
        final List surveyResponses = new ArrayList();
        given(dao.getAll()).willReturn(surveyResponses);

        //when
        List result = manager.getAll();

        //then
        assertSame(surveyResponses, result);
    }

    @Test
    public void testSaveSurveyResponse() {
        log.debug("testing save...");

        //given
        final SurveyResponse surveyResponse = new SurveyResponse();
        // enter all required fields
        surveyResponse.setCoopMemberId(-1L);
        surveyResponse.setSurveyId(-1L);
        


        given(dao.save(surveyResponse)).willReturn(surveyResponse);

        //when
        manager.save(surveyResponse);

        //then
        verify(dao).save(surveyResponse);
    }

    @Test
    public void testRemoveSurveyResponse() {
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