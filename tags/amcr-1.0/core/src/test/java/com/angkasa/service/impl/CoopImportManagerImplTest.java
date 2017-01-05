package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.CoopImportDao;
import com.angkasa.model.CoopImport;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class CoopImportManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private CoopImportManagerImpl manager;

    @Mock
    private CoopImportDao dao;


    @Test
    public void testGetCoopImport() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final CoopImport coopImport = new CoopImport();
        given(dao.get(id)).willReturn(coopImport);

        //when
        CoopImport result = manager.get(id);

        //then
        assertSame(coopImport, result);
    }

    @Test
    public void testGetCoopImports() {
        log.debug("testing getAll...");
        //given
        final List coopImports = new ArrayList();
        given(dao.getAll()).willReturn(coopImports);

        //when
        List result = manager.getAll();

        //then
        assertSame(coopImports, result);
    }

    @Test
    public void testSaveCoopImport() {
        log.debug("testing save...");

        //given
        final CoopImport coopImport = new CoopImport();
        // enter all required fields
        coopImport.setName("CwYrOwCoYcXcGzBrPyVjCgIvVcCrEiSpSoDeAyTdQaHkMqTjBnLdReNhYwPrXyVeIwRmIvXfJqXcUeFgLeOaDiYoXzDuFrViOyOaCvWsTwYxAuGaQdNeRgVkGjOcFbPvJjQbQvAiRfWhCsKzXoIxHoPzKzCgCoLqEeAoExDfMdJoAiEkKaLzUqVtXzAiZkTtQiMzGqPqBvUiArDcVrHiQdIyWySxJlXmXfNzIaJzOzWdCuLdEmKaEzKyOnXePkL");
        


        given(dao.save(coopImport)).willReturn(coopImport);

        //when
        manager.save(coopImport);

        //then
        verify(dao).save(coopImport);
    }

    @Test
    public void testRemoveCoopImport() {
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