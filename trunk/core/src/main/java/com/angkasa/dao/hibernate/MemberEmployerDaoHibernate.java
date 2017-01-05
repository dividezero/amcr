package com.angkasa.dao.hibernate;

import com.angkasa.model.MemberEmployer;
import com.angkasa.dao.MemberEmployerDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import com.angkasa.util.DateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("memberEmployerDao")
public class MemberEmployerDaoHibernate extends GenericDaoHibernate<MemberEmployer, Long> implements MemberEmployerDao {

    public MemberEmployerDaoHibernate() {
        super(MemberEmployer.class);
    }

    @Override
    public MemberEmployer getActiveMemberEmployerByMemberId(Long memberId){
        return getActiveMemberEmployerByMemberId(memberId, DateUtil.getDateToday());
    }

    @Override
    public MemberEmployer getActiveMemberEmployerByMemberId(Long memberId, Date date){
        Criteria criteria = getSession().createCriteria(MemberEmployer.class);
        criteria.add(Restrictions.eq("memberId", memberId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<MemberEmployer> result = criteria.list();

        for(MemberEmployer memberEmployer : result){
            if(memberEmployer.isActive(date)){
                return memberEmployer;
            }
        }
        return null;
    }

    @Override
    public List<MemberEmployer> getByMemberId(Long memberId){
        Criteria criteria = getSession().createCriteria(MemberEmployer.class);
        criteria.add(Restrictions.eq("memberId", memberId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }
}
