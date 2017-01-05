package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.MemberProductTransactionDeductionDao;
import com.angkasa.model.MemberProductTransactionDeduction;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class MemberProductTransactionDeductionManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private MemberProductTransactionDeductionManagerImpl manager;

    @Mock
    private MemberProductTransactionDeductionDao dao;


    @Test
    public void testGetMemberProductTransactionDeduction() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final MemberProductTransactionDeduction memberProductTransactionDeduction = new MemberProductTransactionDeduction();
        given(dao.get(id)).willReturn(memberProductTransactionDeduction);

        //when
        MemberProductTransactionDeduction result = manager.get(id);

        //then
        assertSame(memberProductTransactionDeduction, result);
    }

    @Test
    public void testGetMemberProductTransactionDeductions() {
        log.debug("testing getAll...");
        //given
        final List memberProductTransactionDeductions = new ArrayList();
        given(dao.getAll()).willReturn(memberProductTransactionDeductions);

        //when
        List result = manager.getAll();

        //then
        assertSame(memberProductTransactionDeductions, result);
    }

    @Test
    public void testSaveMemberProductTransactionDeduction() {
        log.debug("testing save...");

        //given
        final MemberProductTransactionDeduction memberProductTransactionDeduction = new MemberProductTransactionDeduction();
        // enter all required fields
        memberProductTransactionDeduction.setDeductionDate(new java.util.Date());
        


        given(dao.save(memberProductTransactionDeduction)).willReturn(memberProductTransactionDeduction);

        //when
        manager.save(memberProductTransactionDeduction);

        //then
        verify(dao).save(memberProductTransactionDeduction);
    }

    @Test
    public void testRemoveMemberProductTransactionDeduction() {
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