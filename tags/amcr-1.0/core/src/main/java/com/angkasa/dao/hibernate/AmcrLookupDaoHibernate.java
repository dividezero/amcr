package com.angkasa.dao.hibernate;

import com.angkasa.model.AmcrLookup;
import com.angkasa.dao.AmcrLookupDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import com.angkasa.model.Coop;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("amcrLookupDao")
public class AmcrLookupDaoHibernate extends GenericDaoHibernate<AmcrLookup, Long> implements AmcrLookupDao {

    public AmcrLookupDaoHibernate() {
        super(AmcrLookup.class);
    }

    @Override
    public String getNewLookup(String tableName){
        Criteria criteria = getSession().createCriteria(AmcrLookup.class);
        criteria.add(Restrictions.eq("tableName", tableName));

        List<AmcrLookup> result = criteria.list();

        if(!result.isEmpty()){
            AmcrLookup amcrLookup =result.get(0);
            String code = amcrLookup.getCode();
            amcrLookup.increaseTail();
            save(amcrLookup);
            return code;
        }
        return null;
    }

    @Override
    public String getNewCoopLookup(){
        return getNewLookup(AmcrLookup.TABLE_COOP);
    }

    @Override
    public String getNewMemberLookup(){
        return getNewLookup(AmcrLookup.TABLE_MEMBER);
    }
}
