package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.DocumentDataDao;
import com.angkasa.model.DocumentData;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class DocumentDataManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private DocumentDataManagerImpl manager;

    @Mock
    private DocumentDataDao dao;


    @Test
    public void testGetDocumentData() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final DocumentData documentData = new DocumentData();
        given(dao.get(id)).willReturn(documentData);

        //when
        DocumentData result = manager.get(id);

        //then
        assertSame(documentData, result);
    }

    @Test
    public void testGetDocumentDatas() {
        log.debug("testing getAll...");
        //given
        final List documentDatas = new ArrayList();
        given(dao.getAll()).willReturn(documentDatas);

        //when
        List result = manager.getAll();

        //then
        assertSame(documentDatas, result);
    }

    @Test
    public void testSaveDocumentData() {
        log.debug("testing save...");

        //given
        final DocumentData documentData = new DocumentData();
        // enter all required fields
        


        given(dao.save(documentData)).willReturn(documentData);

        //when
        manager.save(documentData);

        //then
        verify(dao).save(documentData);
    }

    @Test
    public void testRemoveDocumentData() {
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