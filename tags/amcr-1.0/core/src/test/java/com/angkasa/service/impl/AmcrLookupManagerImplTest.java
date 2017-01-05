package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.AmcrLookupDao;
import com.angkasa.model.AmcrLookup;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class AmcrLookupManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private AmcrLookupManagerImpl manager;

    @Mock
    private AmcrLookupDao dao;


    @Test
    public void testGetAmcrLookup() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final AmcrLookup amcrLookup = new AmcrLookup();
        given(dao.get(id)).willReturn(amcrLookup);

        //when
        AmcrLookup result = manager.get(id);

        //then
        assertSame(amcrLookup, result);
    }

    @Test
    public void testGetAmcrLookups() {
        log.debug("testing getAll...");
        //given
        final List amcrLookups = new ArrayList();
        given(dao.getAll()).willReturn(amcrLookups);

        //when
        List result = manager.getAll();

        //then
        assertSame(amcrLookups, result);
    }

    @Test
    public void testSaveAmcrLookup() {
        log.debug("testing save...");

        //given
        final AmcrLookup amcrLookup = new AmcrLookup();
        // enter all required fields
        


        given(dao.save(amcrLookup)).willReturn(amcrLookup);

        //when
        manager.save(amcrLookup);

        //then
        verify(dao).save(amcrLookup);
    }

    @Test
    public void testRemoveAmcrLookup() {
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