package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.EventDao;
import com.angkasa.model.Event;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class EventManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private EventManagerImpl manager;

    @Mock
    private EventDao dao;


    @Test
    public void testGetEvent() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Event event = new Event();
        given(dao.get(id)).willReturn(event);

        //when
        Event result = manager.get(id);

        //then
        assertSame(event, result);
    }

    @Test
    public void testGetEvents() {
        log.debug("testing getAll...");
        //given
        final List events = new ArrayList();
        given(dao.getAll()).willReturn(events);

        //when
        List result = manager.getAll();

        //then
        assertSame(events, result);
    }

    @Test
    public void testSaveEvent() {
        log.debug("testing save...");

        //given
        final Event event = new Event();
        // enter all required fields
        event.setEventCode("LjGiKeDtEqClHdNtPfSbLxTqYmPaUaIlIuHuPfVtPvYsBhBfXy");
        event.setName("OxFiOwOqYkZzAvOvQiIwIyZfTkJoEpMyAgKiGkOiXfAnQjBnOlVzWpAcSvFfCiKkWyMpHhDsLqDaUmAfAqCmAmGsDtNyJpGfSrPeDeGxQbOgEjKfSeIvYeKcYcAuRuLmKsFbZmDoJvCfIkEnHuUfYfCyVsZyCmDtDqDnIaYfLfGeBrNeAoVmJpNxZjNdIgSbKsQeWiSk");
        event.setStartTime(new java.util.Date());
        


        given(dao.save(event)).willReturn(event);

        //when
        manager.save(event);

        //then
        verify(dao).save(event);
    }

    @Test
    public void testRemoveEvent() {
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