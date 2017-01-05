package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.DmsMemberUpdateDao;
import com.angkasa.model.DmsMemberUpdate;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class DmsMemberUpdateManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private DmsMemberUpdateManagerImpl manager;

    @Mock
    private DmsMemberUpdateDao dao;


    @Test
    public void testGetDmsMemberUpdate() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final DmsMemberUpdate dmsMemberUpdate = new DmsMemberUpdate();
        given(dao.get(id)).willReturn(dmsMemberUpdate);

        //when
        DmsMemberUpdate result = manager.get(id);

        //then
        assertSame(dmsMemberUpdate, result);
    }

    @Test
    public void testGetDmsMemberUpdates() {
        log.debug("testing getAll...");
        //given
        final List dmsMemberUpdates = new ArrayList();
        given(dao.getAll()).willReturn(dmsMemberUpdates);

        //when
        List result = manager.getAll();

        //then
        assertSame(dmsMemberUpdates, result);
    }

    @Test
    public void testSaveDmsMemberUpdate() {
        log.debug("testing save...");

        //given
        final DmsMemberUpdate dmsMemberUpdate = new DmsMemberUpdate();
        // enter all required fields
        dmsMemberUpdate.setIcNumber("BuCvUaAyHvHoShGvMfOh");
        dmsMemberUpdate.setPhoneNum("SjHvWyYbEiWuFpXqOoMg");
        


        given(dao.save(dmsMemberUpdate)).willReturn(dmsMemberUpdate);

        //when
        manager.save(dmsMemberUpdate);

        //then
        verify(dao).save(dmsMemberUpdate);
    }

    @Test
    public void testRemoveDmsMemberUpdate() {
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