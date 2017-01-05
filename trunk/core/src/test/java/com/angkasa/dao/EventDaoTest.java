package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.Event;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventDaoTest extends BaseDaoTestCase {
    @Autowired
    private EventDao eventDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveEvent() {
        Event event = new Event();

        // enter all required fields
        event.setEventCode("ZxEcCnQuUnZtEeJjHlIoVoVrRpBdBeBnPxImRwFcOgRyGxDaHl");
        event.setName("WxIvDlFsWlVkIcTcOrMiLvYmOgSqXqWzTgUdCoJcWgHfAbKqZtZiAeFwIsDoMwNpYkNeXgWpZhIsOvBwVaRdNbQzCwToXkExMpGdUgKbUhEmAfIiUdAhLwPmKkKvZqWaVaPnKuInOoTjZgFeOvBrRjPkHlCvMuEsTdCzFnDtNlClSmEpVbTlByVvUnRjYfKiAzQuLyZw");
        event.setStartTime(new java.util.Date());

        log.debug("adding event...");
        event = eventDao.save(event);

        event = eventDao.get(event.getId());

        assertNotNull(event.getId());

        log.debug("removing event...");

        eventDao.remove(event.getId());

        // should throw DataAccessException 
        eventDao.get(event.getId());
    }
}