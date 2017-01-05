package com.angkasa.dao.hibernate;

import com.angkasa.model.DmsMemberUpdate;
import com.angkasa.dao.DmsMemberUpdateDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("dmsMemberUpdateDao")
public class DmsMemberUpdateDaoHibernate extends GenericDaoHibernate<DmsMemberUpdate, Long> implements DmsMemberUpdateDao {

    public DmsMemberUpdateDaoHibernate() {
        super(DmsMemberUpdate.class);
    }
}
