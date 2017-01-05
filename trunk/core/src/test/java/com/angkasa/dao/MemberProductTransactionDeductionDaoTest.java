package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.MemberProductTransactionDeduction;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberProductTransactionDeductionDaoTest extends BaseDaoTestCase {
    @Autowired
    private MemberProductTransactionDeductionDao memberProductTransactionDeductionDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveMemberProductTransactionDeduction() {
        MemberProductTransactionDeduction memberProductTransactionDeduction = new MemberProductTransactionDeduction();

        // enter all required fields
        memberProductTransactionDeduction.setDeductionDate(new java.util.Date());

        log.debug("adding memberProductTransactionDeduction...");
        memberProductTransactionDeduction = memberProductTransactionDeductionDao.save(memberProductTransactionDeduction);

        memberProductTransactionDeduction = memberProductTransactionDeductionDao.get(memberProductTransactionDeduction.getId());

        assertNotNull(memberProductTransactionDeduction.getId());

        log.debug("removing memberProductTransactionDeduction...");

        memberProductTransactionDeductionDao.remove(memberProductTransactionDeduction.getId());

        // should throw DataAccessException 
        memberProductTransactionDeductionDao.get(memberProductTransactionDeduction.getId());
    }
}