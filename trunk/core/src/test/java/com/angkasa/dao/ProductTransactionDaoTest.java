package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.ProductTransaction;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class ProductTransactionDaoTest extends BaseDaoTestCase {
    @Autowired
    private ProductTransactionDao productTransactionDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveProductTransaction() {
        ProductTransaction productTransaction = new ProductTransaction();

        // enter all required fields
        productTransaction.setAmount(new BigDecimal("0"));
        productTransaction.setTransNo(973166051);

        log.debug("adding productTransaction...");
        productTransaction = productTransactionDao.save(productTransaction);

        productTransaction = productTransactionDao.get(productTransaction.getId());

        assertNotNull(productTransaction.getId());

        log.debug("removing productTransaction...");

        productTransactionDao.remove(productTransaction.getId());

        // should throw DataAccessException 
        productTransactionDao.get(productTransaction.getId());
    }
}