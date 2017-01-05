package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.MemberEmployer;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberEmployerDaoTest extends BaseDaoTestCase {
    @Autowired
    private MemberEmployerDao memberEmployerDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveMemberEmployer() {
        MemberEmployer memberEmployer = new MemberEmployer();

        // enter all required fields

        log.debug("adding memberEmployer...");
        memberEmployer = memberEmployerDao.save(memberEmployer);

        memberEmployer = memberEmployerDao.get(memberEmployer.getId());

        assertNotNull(memberEmployer.getId());

        log.debug("removing memberEmployer...");

        memberEmployerDao.remove(memberEmployer.getId());

        // should throw DataAccessException 
        memberEmployerDao.get(memberEmployer.getId());
    }
}