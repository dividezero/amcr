package com.angkasa.dao.hibernate;

import com.angkasa.model.Event;
import com.angkasa.model.EventMember;
import com.angkasa.dao.EventMemberDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("eventMemberDao")
public class EventMemberDaoHibernate extends GenericDaoHibernate<EventMember, Long> implements EventMemberDao {

    public EventMemberDaoHibernate() {
        super(EventMember.class);
    }

    public List<EventMember> getByEventId(Long eventId){
        try {
            Criteria criteria = getSession().createCriteria(EventMember.class);
            criteria.add(Restrictions.eq("eventId", eventId));
            List<EventMember> results	=	(List<EventMember>)criteria.list();
            return results;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<EventMember> getRandomDrawByEventId(Long eventId, int numDraws){
        try {
            Criteria criteria = getSession().createCriteria(EventMember.class);
            criteria.add(Restrictions.eq("eventId", eventId));
            criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
            criteria.setMaxResults(numDraws);
            List<EventMember> results	=	(List<EventMember>)criteria.list();
            return results;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
