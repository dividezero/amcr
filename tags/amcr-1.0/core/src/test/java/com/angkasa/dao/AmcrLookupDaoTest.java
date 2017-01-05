package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.AmcrLookup;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AmcrLookupDaoTest extends BaseDaoTestCase {
    @Autowired
    private AmcrLookupDao amcrLookupDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveAmcrLookup() {
        AmcrLookup amcrLookup = new AmcrLookup();

        // enter all required fields

        log.debug("adding amcrLookup...");
        amcrLookup = amcrLookupDao.save(amcrLookup);

        amcrLookup = amcrLookupDao.get(amcrLookup.getId());

        assertNotNull(amcrLookup.getId());

        log.debug("removing amcrLookup...");

        amcrLookupDao.remove(amcrLookup.getId());

        // should throw DataAccessException 
        amcrLookupDao.get(amcrLookup.getId());
    }
}