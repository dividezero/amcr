package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.DocumentData;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DocumentDataDaoTest extends BaseDaoTestCase {
    @Autowired
    private DocumentDataDao documentDataDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveDocumentData() {
        DocumentData documentData = new DocumentData();

        // enter all required fields

        log.debug("adding documentData...");
        documentData = documentDataDao.save(documentData);

        documentData = documentDataDao.get(documentData.getId());

        assertNotNull(documentData.getId());

        log.debug("removing documentData...");

        documentDataDao.remove(documentData.getId());

        // should throw DataAccessException 
        documentDataDao.get(documentData.getId());
    }
}