package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.CoopImport;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CoopImportDaoTest extends BaseDaoTestCase {
    @Autowired
    private CoopImportDao coopImportDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveCoopImport() {
        CoopImport coopImport = new CoopImport();

        // enter all required fields
        coopImport.setName("PpUfYxJmIuVnWoAcWhNzWvQvWoQtEcEiFkJzCdKpFhLkQhQpIiEtMsOpEgYcAfPaXjQsEcZzJxXaGhMbUvEbAwBpHkDfZvGwCsNgLrBwYgUwKbKgDxNnKwSgInKuOfTgRbCbJzKaHbLxBqRpAuUlOtTpZjMzHsMwCrLiLtPlDhYkSsCkRsKrYxQjUrKbVoPxQdUiJnGeKoOjPrPrYeKrAcZhEjPhUpCaDzReFqLfHtIsKmEuKjHqGxPzDnZuVqI");

        log.debug("adding coopImport...");
        coopImport = coopImportDao.save(coopImport);

        coopImport = coopImportDao.get(coopImport.getId());

        assertNotNull(coopImport.getId());

        log.debug("removing coopImport...");

        coopImportDao.remove(coopImport.getId());

        // should throw DataAccessException 
        coopImportDao.get(coopImport.getId());
    }
}