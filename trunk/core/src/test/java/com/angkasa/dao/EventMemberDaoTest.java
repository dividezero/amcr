package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.EventMember;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventMemberDaoTest extends BaseDaoTestCase {
    @Autowired
    private EventMemberDao eventMemberDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveEventMember() {
        EventMember eventMember = new EventMember();

        // enter all required fields
        eventMember.setIcNumber("LjEgZnVuBxSjUpEwKmXs");
        eventMember.setName("NqQqUmBqNeFrJfBrEdScFuEfAkUjAoBhGwMuPhFoOnJgYuLsSmLtVpNdGyDpDnSuNsNvRoYxCaKoOpCoHkUoCjEjTuDpPuStHoBbUhQaJsQtXuIqFtLwExOlAuAfRmUaQwEnSsQqMlPbUhZsBkObJcQhQoNwCsNxOmJqGyZaUoAsLiVgLaElYkWqAtAhGiArYgGjPlPn");

        log.debug("adding eventMember...");
        eventMember = eventMemberDao.save(eventMember);

        eventMember = eventMemberDao.get(eventMember.getId());

        assertNotNull(eventMember.getId());

        log.debug("removing eventMember...");

        eventMemberDao.remove(eventMember.getId());

        // should throw DataAccessException 
        eventMemberDao.get(eventMember.getId());
    }
}