package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.DocumentDao;
import com.angkasa.model.Document;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class DocumentManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private DocumentManagerImpl manager;

    @Mock
    private DocumentDao dao;


    @Test
    public void testGetDocument() {
        log.debug("testing get...");
        //given
        final String id = "-1";
        final Document document = new Document();
        given(dao.get(id)).willReturn(document);

        //when
        Document result = manager.get(id);

        //then
        assertSame(document, result);
    }

    @Test
    public void testGetDocuments() {
        log.debug("testing getAll...");
        //given
        final List documents = new ArrayList();
        given(dao.getAll()).willReturn(documents);

        //when
        List result = manager.getAll();

        //then
        assertSame(documents, result);
    }

    @Test
    public void testSaveDocument() {
        log.debug("testing save...");

        //given
        final Document document = new Document();
        // enter all required fields
        document.setContentType("NuPjGyZaZmEsKaEtGfXgJdKxOkAkDqPhCtDcRrEoDxNfEzVhKkNjCjEwIpEwIzCgQwHqNqCcLtPkIyQlZhQuYuKqWvLoSjWjIgOw");
        document.setFileRelativePath("XbCzObSsOlVdYgQcWxSxBsQuEzStIzKfArAvJsDhKhVlWmRmSuPhMkRnEmJyQwSdOfQbTpBeJkRpGfXvDjIrVqIrHmNqIqRyHgKaOrMdGuMdNqSgAnKnKoQfOgUjYaPoZaEyWcUaYxBmBpMmSvYyFaEpKmFvEpIdIrOmLoZjFbXpJaJcWlOqTdWjKrQaUkSiIsGkOxWiUjNzSbMhHzLcRuVnYfTbVcFcYwQkWaRoZpUaBcJpFdMhGaSzIi");
        document.setModuleName("DvAcFuXvUjRyZrDnUeUlJfWaYhGwXxWuNgKkZiSqPrLhPsNmQdJpUeZdEpCeMoRfNoIfGvBcFaRsYePeVjQtXiUnTaDfZzDhKySa");
        document.setModulePrimKey("DfJkQhObTuIpSrTjGrJwQsKpPpEqFjEaWhShRpPcRcXjDmEcKeYkTnIsUfLoDqCmHcSdCwTjLyMgLrAeVoUmVgAaTbShZzEtFgBi");
        


        given(dao.save(document)).willReturn(document);

        //when
        manager.save(document);

        //then
        verify(dao).save(document);
    }

    @Test
    public void testRemoveDocument() {
        log.debug("testing remove...");

        //given
        final String id = "-1";
        willDoNothing().given(dao).remove(id);

        //when
        manager.remove(id);

        //then
        verify(dao).remove(id);
    }
}