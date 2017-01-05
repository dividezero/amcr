package com.angkasa.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.angkasa.dao.CoopMemberDao;
import com.angkasa.model.CoopMember;

@Repository("coopMemberDao")
public class CoopMemberDaoHibernate extends GenericDaoHibernate<CoopMember, Long> implements CoopMemberDao {

    public CoopMemberDaoHibernate() {
        super(CoopMember.class);
    }

    @Override
    public List<CoopMember> getByMemberId(Long memberId){
    	try {
			Criteria criteria = getSession().createCriteria(CoopMember.class);
			criteria.add(Restrictions.eq("memberId", memberId));
			List<CoopMember> results	=	(List<CoopMember>)criteria.list();
			for (CoopMember coopMember : results) {
				Hibernate.initialize(coopMember.getCoop());
				Hibernate.initialize(coopMember.getMember());
			}
			return results;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
    }

    @Override
    public List<CoopMember> getByCoopId(Long coopId){
        try {
            Criteria criteria = getSession().createCriteria(CoopMember.class);
            criteria.add(Restrictions.eq("coopId", coopId));
            List<CoopMember> results	=	(List<CoopMember>)criteria.list();
            for (CoopMember coopMember : results) {
                Hibernate.initialize(coopMember.getCoop());
                Hibernate.initialize(coopMember.getMember());
            }
            return results;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
