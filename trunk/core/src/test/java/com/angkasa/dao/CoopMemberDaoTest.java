package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.CoopMember;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CoopMemberDaoTest extends BaseDaoTestCase {
    @Autowired
    private CoopMemberDao coopMemberDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveCoopMember() {
        CoopMember coopMember = new CoopMember();

        // enter all required fields

        log.debug("adding coopMember...");
        coopMember = coopMemberDao.save(coopMember);

        coopMember = coopMemberDao.get(coopMember.getId());

        assertNotNull(coopMember.getId());

        log.debug("removing coopMember...");

        coopMemberDao.remove(coopMember.getId());

        // should throw DataAccessException 
        coopMemberDao.get(coopMember.getId());
    }
}