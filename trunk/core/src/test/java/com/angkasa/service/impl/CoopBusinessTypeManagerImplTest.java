package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.CoopBusinessTypeDao;
import com.angkasa.model.CoopBusinessType;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class CoopBusinessTypeManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private CoopBusinessTypeManagerImpl manager;

    @Mock
    private CoopBusinessTypeDao dao;


    @Test
    public void testGetCoopBusinessType() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final CoopBusinessType coopBusinessType = new CoopBusinessType();
        given(dao.get(id)).willReturn(coopBusinessType);

        //when
        CoopBusinessType result = manager.get(id);

        //then
        assertSame(coopBusinessType, result);
    }

    @Test
    public void testGetCoopBusinessTypes() {
        log.debug("testing getAll...");
        //given
        final List coopBusinessTypes = new ArrayList();
        given(dao.getAll()).willReturn(coopBusinessTypes);

        //when
        List result = manager.getAll();

        //then
        assertSame(coopBusinessTypes, result);
    }

    @Test
    public void testSaveCoopBusinessType() {
        log.debug("testing save...");

        //given
        final CoopBusinessType coopBusinessType = new CoopBusinessType();
        // enter all required fields
        coopBusinessType.setCode("Xf");
        coopBusinessType.setDescription("JdJlLvEwTqKlNxRcGlTlTpZmNdDxCrOeIlZvOeLlMxIgEvDbSvSoYoAvTfFeJjFtRyGaJkMeRuNyYkOrUyMxOwKgPyFnHsZuDnKqJrGdMpDrEsZgYyEqDlRlMgOyNxZwYdGvZaZxNkBqHqIlCqUeSfHcJbPgNgLgMsEjOgOgYqKdBwYiDoSnUyDpIjZuQiDvChQcUpWo");
        coopBusinessType.setName("WoUeUoAmTfFsCfYpSmHpSpBpBdYlFaRvUiIuGuJpYmHpArDfGq");
        


        given(dao.save(coopBusinessType)).willReturn(coopBusinessType);

        //when
        manager.save(coopBusinessType);

        //then
        verify(dao).save(coopBusinessType);
    }

    @Test
    public void testRemoveCoopBusinessType() {
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