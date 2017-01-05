package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.AmcrLookup;

/**
 * An interface that provides a data management interface to the AmcrLookup table.
 */
public interface AmcrLookupDao extends GenericDao<AmcrLookup, Long> {

    String getNewLookup(String tableName);
    String getNewCoopLookup();
    String getNewMemberLookup();

}