package com.angkasa.dao.hibernate;

import com.angkasa.model.MemberProduct;
import com.angkasa.dao.MemberProductDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("memberProductDao")
public class MemberProductDaoHibernate extends GenericDaoHibernate<MemberProduct, Long> implements MemberProductDao {

    public MemberProductDaoHibernate() {
        super(MemberProduct.class);
    }
}
