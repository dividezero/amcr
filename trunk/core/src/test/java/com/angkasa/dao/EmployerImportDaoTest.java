package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.EmployerImport;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployerImportDaoTest extends BaseDaoTestCase {
    @Autowired
    private EmployerImportDao employerImportDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveEmployerImport() {
        EmployerImport employerImport = new EmployerImport();

        // enter all required fields
        employerImport.setDocumentId("IfDyTeHxVwEbXfBlBeYkYkHpJrRhXnZuRlRpEpUkAyZvUmPoLgGhKnIyOnNrDdCwKeVrBaGuXcPvIiErVgLeGpSvVxNsFdKvJvYxWmEfJiUiVcYlQzLlOtSsIsQbLsMjWvEdFxLiJzQmCbKkPcXaLsCmJvSpRgVnRbRdTkTvImEcQaCvJiEeKyIdMxZaXlMlNzIzEbMwXzJpXqQhWzPeXaRiRvMjIlPpRmOkPcRvZaIjCiDkIrZgNeDrEhXxLgL");

        log.debug("adding employerImport...");
        employerImport = employerImportDao.save(employerImport);

        employerImport = employerImportDao.get(employerImport.getId());

        assertNotNull(employerImport.getId());

        log.debug("removing employerImport...");

        employerImportDao.remove(employerImport.getId());

        // should throw DataAccessException 
        employerImportDao.get(employerImport.getId());
    }
}