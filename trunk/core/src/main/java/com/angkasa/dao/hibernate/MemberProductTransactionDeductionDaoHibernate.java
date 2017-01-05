package com.angkasa.dao.hibernate;

import com.angkasa.model.MemberProductTransactionDeduction;
import com.angkasa.dao.MemberProductTransactionDeductionDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("memberProductTransactionDeductionDao")
public class MemberProductTransactionDeductionDaoHibernate extends GenericDaoHibernate<MemberProductTransactionDeduction, Long> implements MemberProductTransactionDeductionDao {

    public MemberProductTransactionDeductionDaoHibernate() {
        super(MemberProductTransactionDeduction.class);
    }
}
