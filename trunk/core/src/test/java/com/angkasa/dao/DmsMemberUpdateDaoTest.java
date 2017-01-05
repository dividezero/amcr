package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.DmsMemberUpdate;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DmsMemberUpdateDaoTest extends BaseDaoTestCase {
    @Autowired
    private DmsMemberUpdateDao dmsMemberUpdateDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveDmsMemberUpdate() {
        DmsMemberUpdate dmsMemberUpdate = new DmsMemberUpdate();

        // enter all required fields
        dmsMemberUpdate.setIcNumber("HkEnWvTpCjNzBeKhKvOt");
        dmsMemberUpdate.setPhoneNum("WpCiVzLqWrOsZeJiYaPs");

        log.debug("adding dmsMemberUpdate...");
        dmsMemberUpdate = dmsMemberUpdateDao.save(dmsMemberUpdate);

        dmsMemberUpdate = dmsMemberUpdateDao.get(dmsMemberUpdate.getId());

        assertNotNull(dmsMemberUpdate.getId());

        log.debug("removing dmsMemberUpdate...");

        dmsMemberUpdateDao.remove(dmsMemberUpdate.getId());

        // should throw DataAccessException 
        dmsMemberUpdateDao.get(dmsMemberUpdate.getId());
    }
}