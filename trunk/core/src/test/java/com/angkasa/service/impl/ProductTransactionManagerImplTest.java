package com.angkasa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.ProductTransactionDao;
import com.angkasa.model.ProductTransaction;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class ProductTransactionManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private ProductTransactionManagerImpl manager;

    @Mock
    private ProductTransactionDao dao;


    @Test
    public void testGetProductTransaction() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final ProductTransaction productTransaction = new ProductTransaction();
        given(dao.get(id)).willReturn(productTransaction);

        //when
        ProductTransaction result = manager.get(id);

        //then
        assertSame(productTransaction, result);
    }

    @Test
    public void testGetProductTransactions() {
        log.debug("testing getAll...");
        //given
        final List productTransactions = new ArrayList();
        given(dao.getAll()).willReturn(productTransactions);

        //when
        List result = manager.getAll();

        //then
        assertSame(productTransactions, result);
    }

    @Test
    public void testSaveProductTransaction() {
        log.debug("testing save...");

        //given
        final ProductTransaction productTransaction = new ProductTransaction();
        // enter all required fields
        productTransaction.setAmount(new BigDecimal("0"));
        productTransaction.setTransNo(840251523);
        


        given(dao.save(productTransaction)).willReturn(productTransaction);

        //when
        manager.save(productTransaction);

        //then
        verify(dao).save(productTransaction);
    }

    @Test
    public void testRemoveProductTransaction() {
        log.debug("testing remove...");

        //given
        final Long id = -11L;
        willDoNothing().given(dao).remove(id);

        //when
        manager.remove(id);

        //then
        verify(dao).remove(id);
    }
}