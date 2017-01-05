package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.EmployerDao;
import com.angkasa.model.Employer;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class EmployerManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private EmployerManagerImpl manager;

    @Mock
    private EmployerDao dao;


    @Test
    public void testGetEmployer() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Employer employer = new Employer();
        given(dao.get(id)).willReturn(employer);

        //when
        Employer result = manager.get(id);

        //then
        assertSame(employer, result);
    }

    @Test
    public void testGetEmployers() {
        log.debug("testing getAll...");
        //given
        final List employers = new ArrayList();
        given(dao.getAll()).willReturn(employers);

        //when
        List result = manager.getAll();

        //then
        assertSame(employers, result);
    }

    @Test
    public void testSaveEmployer() {
        log.debug("testing save...");

        //given
        final Employer employer = new Employer();
        // enter all required fields
        employer.setEmployerCode("ClRbFjEhSy");
        employer.setName("JwOaGbBfUpEfJtNtWpIsUjOePjMiBbZfNsBmDlFyPtOuBjLkQyVbPmBhAoCaTpApTuVqIyOdOgHdOwFrFpUiZgHxHbWeEzVhQeNxVhUzPeTyPiTyQkUhVvDkScYfJwMiDmMtXyMvHsKwBcDdCbDnIzRtBtBzLiEiXbPwCzPpFuNqPcCcYcGeNkQlJxQmUnHqZgIcYfUs");
        


        given(dao.save(employer)).willReturn(employer);

        //when
        manager.save(employer);

        //then
        verify(dao).save(employer);
    }

    @Test
    public void testRemoveEmployer() {
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