package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.EventMember;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface EventMemberManager extends GenericManager<EventMember, Long> {

    List<EventMember> getByEventId(Long eventId);
    List<EventMember> getRandomDrawByEventId(Long eventId, int numDraws);
}