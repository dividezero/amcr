package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.MemberImport;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberImportDaoTest extends BaseDaoTestCase {
    @Autowired
    private MemberImportDao memberImportDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveMemberImport() {
        MemberImport memberImport = new MemberImport();

        // enter all required fields
        memberImport.setName("MnIbYeHxVzTsNeHyLuCmEcKeJqZcDwOnVpYoTmKkDwKmWdIvMjRvJfGnBsMyPxUrCgPfGcOwQlAgByRoIuAjDqJiPwTeCtAjZlYaBgFmDkRgCuEaNaMxHsGrVmGoRrHjJpQnDyDkCsBqMuYjBnGeArKyMmGiSqHoGaYgCxPyKwWzHwDpOqVxKeRzKpKmSbYbMrIbPgHyMzPrYvMnAvVuYwHrMwNpTcHxFkGeEiCgPqMbAqHvNoWdMhPoEhRzPjP");

        log.debug("adding memberImport...");
        memberImport = memberImportDao.save(memberImport);

        memberImport = memberImportDao.get(memberImport.getId());

        assertNotNull(memberImport.getId());

        log.debug("removing memberImport...");

        memberImportDao.remove(memberImport.getId());

        // should throw DataAccessException 
        memberImportDao.get(memberImport.getId());
    }
}