package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.MemberProduct;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberProductDaoTest extends BaseDaoTestCase {
    @Autowired
    private MemberProductDao memberProductDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveMemberProduct() {
        MemberProduct memberProduct = new MemberProduct();

        // enter all required fields
        memberProduct.setCommencementDate(new java.util.Date());

        log.debug("adding memberProduct...");
        memberProduct = memberProductDao.save(memberProduct);

        memberProduct = memberProductDao.get(memberProduct.getId());

        assertNotNull(memberProduct.getId());

        log.debug("removing memberProduct...");

        memberProductDao.remove(memberProduct.getId());

        // should throw DataAccessException 
        memberProductDao.get(memberProduct.getId());
    }
}