package com.angkasa.dao.hibernate;

import java.util.List;

import com.angkasa.dao.CoopDao;
import com.angkasa.dao.MemberDao;
import com.angkasa.model.Coop;
import com.angkasa.model.Member;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angkasa.dao.CoopMemberDao;
import com.angkasa.model.CoopMember;

@Repository("coopMemberDao")
public class CoopMemberDaoHibernate extends GenericDaoHibernate<CoopMember, Long> implements CoopMemberDao {

    public CoopMemberDaoHibernate() {
        super(CoopMember.class);
    }

    @Autowired
    private CoopDao coopDao;

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
            return rer6sults;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public CoopMember saveWithHexNo(CoopMember coopMember) {
        Coop coop = coopDao.get(coopMember.getCoopId());
        String hexNo = coop.getAndIncreaseMemberHexRunningNo();
        coopMember.setMemberHexNo(hexNo);
        coopDao.save(coop);

        // Add name and IC for search/indexing purposes
        if(coopMember.getMemberId()!=null){
            Member member = coopMember.getMember();
            //leaving NPE risk here to catch stuff giving off null
            coopMember.setIcNumber(member.getIcNumber());
            coopMember.setName(member.getName());
        }

        return this.save(coopMember);
    }

}
