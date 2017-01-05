package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.EventMemberDao;
import com.angkasa.model.EventMember;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class EventMemberManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private EventMemberManagerImpl manager;

    @Mock
    private EventMemberDao dao;


    @Test
    public void testGetEventMember() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final EventMember eventMember = new EventMember();
        given(dao.get(id)).willReturn(eventMember);

        //when
        EventMember result = manager.get(id);

        //then
        assertSame(eventMember, result);
    }

    @Test
    public void testGetEventMembers() {
        log.debug("testing getAll...");
        //given
        final List eventMembers = new ArrayList();
        given(dao.getAll()).willReturn(eventMembers);

        //when
        List result = manager.getAll();

        //then
        assertSame(eventMembers, result);
    }

    @Test
    public void testSaveEventMember() {
        log.debug("testing save...");

        //given
        final EventMember eventMember = new EventMember();
        // enter all required fields
        eventMember.setIcNumber("KkGyOeFzIoHlXpKsYdXz");
        eventMember.setName("QmPfWuRjRcMlXgRgDzXtIkVvHuNbMiJlSwScDrWnEcHwRnVzFbEpGlFkMrFuEiKuPzIrUuZnHlFeYeDfZgEbBpXxXbFvRbXaPtEpMwAeLsUkNjNrCsOxArHdWpCsEwBtMgUpKkOhErMbMkVeJuHrYeUfVvVlKzJbFyEgAsHnQrBdIuRcNiWeApKhSlFpYlDvAvHlOjJt");
        


        given(dao.save(eventMember)).willReturn(eventMember);

        //when
        manager.save(eventMember);

        //then
        verify(dao).save(eventMember);
    }

    @Test
    public void testRemoveEventMember() {
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