package com.angkasa.dao.hibernate;

import com.angkasa.model.Event;
import com.angkasa.dao.EventDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("eventDao")
public class EventDaoHibernate extends GenericDaoHibernate<Event, Long> implements EventDao {

    public EventDaoHibernate() {
        super(Event.class);
    }
}
