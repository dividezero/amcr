package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.EmployerImportDao;
import com.angkasa.model.EmployerImport;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class EmployerImportManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private EmployerImportManagerImpl manager;

    @Mock
    private EmployerImportDao dao;


    @Test
    public void testGetEmployerImport() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final EmployerImport employerImport = new EmployerImport();
        given(dao.get(id)).willReturn(employerImport);

        //when
        EmployerImport result = manager.get(id);

        //then
        assertSame(employerImport, result);
    }

    @Test
    public void testGetEmployerImports() {
        log.debug("testing getAll...");
        //given
        final List employerImports = new ArrayList();
        given(dao.getAll()).willReturn(employerImports);

        //when
        List result = manager.getAll();

        //then
        assertSame(employerImports, result);
    }

    @Test
    public void testSaveEmployerImport() {
        log.debug("testing save...");

        //given
        final EmployerImport employerImport = new EmployerImport();
        // enter all required fields
        employerImport.setDocumentId("VzIlJzVfRtYoCvMwKuFgExJfJdJoEwOnAuTaEcMmYjFuPlLuReXnOkEaJuUuNtMfRpIaMnSyPnBwTgLxWxDhRqIuHvBlNxXfHnZqUxUiUnLuVfEnOnVxSmKhFxAqXeWsEiLnEoRwVhDiXtYsKbQhXdNxEnUwUsHmJmVkDjRqReFnPzZgYuAbAvOuDcPhWwWhFrLmVqEpKiPtEaArFkXnJePcFkCeDcItIiWjJpMiCxFtOoCoJcEhTiJtGyCxSiU");
        


        given(dao.save(employerImport)).willReturn(employerImport);

        //when
        manager.save(employerImport);

        //then
        verify(dao).save(employerImport);
    }

    @Test
    public void testRemoveEmployerImport() {
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