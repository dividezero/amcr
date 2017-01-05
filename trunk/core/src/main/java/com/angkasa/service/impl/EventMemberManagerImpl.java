package com.angkasa.service.impl;

import com.angkasa.dao.EventMemberDao;
import com.angkasa.model.EventMember;
import com.angkasa.service.EventMemberManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("eventMemberManager")
@WebService(serviceName = "EventMemberService", endpointInterface = "com.angkasa.service.EventMemberManager")
public class EventMemberManagerImpl extends GenericManagerImpl<EventMember, Long> implements EventMemberManager {
    EventMemberDao eventMemberDao;

    @Autowired
    public EventMemberManagerImpl(EventMemberDao eventMemberDao) {
        super(eventMemberDao);
        this.eventMemberDao = eventMemberDao;
    }

    public List<EventMember> getByEventId(Long eventId){
        return eventMemberDao.getByEventId(eventId);
    }

    public List<EventMember> getRandomDrawByEventId(Long eventId, int numDraws){
        return eventMemberDao.getRandomDrawByEventId(eventId,numDraws);
    }
}