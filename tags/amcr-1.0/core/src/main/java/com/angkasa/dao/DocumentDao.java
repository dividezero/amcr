package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.Document;

import java.util.Date;
import java.util.List;

/**
 * An interface that provides a data management interface to the Document table.
 */
public interface DocumentDao extends GenericDao<Document, String> {


    List<Document> findByModule(String moduleName, String... modulePrimKeys);

    List<Document> findByDateRangeModule(Date startDate, Date endDate, int start, int max, String moduleName, String... modulePrimKeys);
}