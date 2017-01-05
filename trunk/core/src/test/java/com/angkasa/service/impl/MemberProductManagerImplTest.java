package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.MemberProductDao;
import com.angkasa.model.MemberProduct;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class MemberProductManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private MemberProductManagerImpl manager;

    @Mock
    private MemberProductDao dao;


    @Test
    public void testGetMemberProduct() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final MemberProduct memberProduct = new MemberProduct();
        given(dao.get(id)).willReturn(memberProduct);

        //when
        MemberProduct result = manager.get(id);

        //then
        assertSame(memberProduct, result);
    }

    @Test
    public void testGetMemberProducts() {
        log.debug("testing getAll...");
        //given
        final List memberProducts = new ArrayList();
        given(dao.getAll()).willReturn(memberProducts);

        //when
        List result = manager.getAll();

        //then
        assertSame(memberProducts, result);
    }

    @Test
    public void testSaveMemberProduct() {
        log.debug("testing save...");

        //given
        final MemberProduct memberProduct = new MemberProduct();
        // enter all required fields
        memberProduct.setCommencementDate(new java.util.Date());
        


        given(dao.save(memberProduct)).willReturn(memberProduct);

        //when
        manager.save(memberProduct);

        //then
        verify(dao).save(memberProduct);
    }

    @Test
    public void testRemoveMemberProduct() {
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