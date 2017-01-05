package com.angkasa.dao.hibernate;

import com.angkasa.model.CoopBusinessType;
import com.angkasa.dao.CoopBusinessTypeDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("coopBusinessTypeDao")
public class CoopBusinessTypeDaoHibernate extends GenericDaoHibernate<CoopBusinessType, Long> implements CoopBusinessTypeDao {

    public CoopBusinessTypeDaoHibernate() {
        super(CoopBusinessType.class);
    }
}
