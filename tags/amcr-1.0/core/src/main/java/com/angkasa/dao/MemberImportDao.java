package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.MemberImport;

/**
 * An interface that provides a data management interface to the MemberImport table.
 */
public interface MemberImportDao extends GenericDao<MemberImport, Long> {

    MemberImport saveWithFile(MemberImport memberImport) throws Exception;
}