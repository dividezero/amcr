package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.CoopDao;
import com.angkasa.model.Coop;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class CoopManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private CoopManagerImpl manager;

    @Mock
    private CoopDao dao;


    @Test
    public void testGetCoop() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Coop coop = new Coop();
        given(dao.get(id)).willReturn(coop);

        //when
        Coop result = manager.get(id);

        //then
        assertSame(coop, result);
    }

    @Test
    public void testGetCoops() {
        log.debug("testing getAll...");
        //given
        final List coops = new ArrayList();
        given(dao.getAll()).willReturn(coops);

        //when
        List result = manager.getAll();

        //then
        assertSame(coops, result);
    }

    @Test
    public void testSaveCoop() {
        log.debug("testing save...");

        //given
        final Coop coop = new Coop();
        // enter all required fields
        coop.setCoopCode("PoE");
        coop.setName("RkMvXgSqDeRsKqOpRaItPjTqVgOrZsPaRdIxPoDtYfTmLsPuMv");
        coop.setPhoneNo("WgNzGdYiIsOz");
        


        given(dao.save(coop)).willReturn(coop);

        //when
        manager.save(coop);

        //then
        verify(dao).save(coop);
    }

    @Test
    public void testRemoveCoop() {
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