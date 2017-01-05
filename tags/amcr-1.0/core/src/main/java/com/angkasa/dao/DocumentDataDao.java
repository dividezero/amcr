package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.DocumentData;

/**
 * An interface that provides a data management interface to the DocumentData table.
 */
public interface DocumentDataDao extends GenericDao<DocumentData, Long> {

    public DocumentData getByDocumentId(String docId);
}