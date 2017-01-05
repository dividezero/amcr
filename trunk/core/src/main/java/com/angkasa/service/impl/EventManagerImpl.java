package com.angkasa.service.impl;

import com.angkasa.dao.EventDao;
import com.angkasa.model.Event;
import com.angkasa.service.EventManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("eventManager")
@WebService(serviceName = "EventService", endpointInterface = "com.angkasa.service.EventManager")
public class EventManagerImpl extends GenericManagerImpl<Event, Long> implements EventManager {
    EventDao eventDao;

    @Autowired
    public EventManagerImpl(EventDao eventDao) {
        super(eventDao);
        this.eventDao = eventDao;
    }
}