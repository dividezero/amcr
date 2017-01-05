package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.SurveyDao;
import com.angkasa.model.Survey;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class SurveyManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private SurveyManagerImpl manager;

    @Mock
    private SurveyDao dao;


    @Test
    public void testGetSurvey() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Survey survey = new Survey();
        given(dao.get(id)).willReturn(survey);

        //when
        Survey result = manager.get(id);

        //then
        assertSame(survey, result);
    }

    @Test
    public void testGetSurveys() {
        log.debug("testing getAll...");
        //given
        final List surveys = new ArrayList();
        given(dao.getAll()).willReturn(surveys);

        //when
        List result = manager.getAll();

        //then
        assertSame(surveys, result);
    }

    @Test
    public void testSaveSurvey() {
        log.debug("testing save...");

        //given
        final Survey survey = new Survey();
        // enter all required fields
        survey.setName("BxKkBzBiFcDsFxGnEqXjPkQxNgXwWnAoTxCiQgBcKiRjByOhQr");
        


        given(dao.save(survey)).willReturn(survey);

        //when
        manager.save(survey);

        //then
        verify(dao).save(survey);
    }

    @Test
    public void testRemoveSurvey() {
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