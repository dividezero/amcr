package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.ContactPersonDao;
import com.angkasa.model.ContactPerson;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class ContactPersonManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private ContactPersonManagerImpl manager;

    @Mock
    private ContactPersonDao dao;


    @Test
    public void testGetContactPerson() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final ContactPerson contactPerson = new ContactPerson();
        given(dao.get(id)).willReturn(contactPerson);

        //when
        ContactPerson result = manager.get(id);

        //then
        assertSame(contactPerson, result);
    }

    @Test
    public void testGetContactPersons() {
        log.debug("testing getAll...");
        //given
        final List contactPersons = new ArrayList();
        given(dao.getAll()).willReturn(contactPersons);

        //when
        List result = manager.getAll();

        //then
        assertSame(contactPersons, result);
    }

    @Test
    public void testSaveContactPerson() {
        log.debug("testing save...");

        //given
        final ContactPerson contactPerson = new ContactPerson();
        // enter all required fields
        contactPerson.setDescription("OnOaPgLxKyUqGdRhPiWtMvNaExUcRuBnVlQcVrMvEbEpRdKzHnSdXvQcEyThSnPbChQwMyZoZhFjUlKaLfKzZiTuBaFdLlWbByHsYcCwCuKqTdRfPkDaMvDbFuVpUgBeJeSqMhTwKdQtKjGiGwLoUjBhZbPrZzRuSsIpChDlNdAxIdVnDbFbLbJlFhZoNnKhDjCoZzOo");
        contactPerson.setEmail("AnKjEfRvLpBbGqKtPrIb");
        contactPerson.setName("AhVbNqZdEyFhIyUeOtNgTuYgGjCuIhJiUhQcOjPeFrOpImZuWs");
        contactPerson.setWorkPhoneNo("ZjVnZwRiJkJk");
        


        given(dao.save(contactPerson)).willReturn(contactPerson);

        //when
        manager.save(contactPerson);

        //then
        verify(dao).save(contactPerson);
    }

    @Test
    public void testRemoveContactPerson() {
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