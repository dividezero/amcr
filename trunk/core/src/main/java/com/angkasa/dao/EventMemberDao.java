package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.EventMember;

import java.util.List;

/**
 * An interface that provides a data management interface to the EventMember table.
 */
public interface EventMemberDao extends GenericDao<EventMember, Long> {

    List<EventMember> getByEventId(Long eventId);
    List<EventMember> getRandomDrawByEventId(Long eventId, int numDraws);
}