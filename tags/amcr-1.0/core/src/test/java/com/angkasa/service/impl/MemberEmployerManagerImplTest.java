package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.MemberEmployerDao;
import com.angkasa.model.MemberEmployer;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class MemberEmployerManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private MemberEmployerManagerImpl manager;

    @Mock
    private MemberEmployerDao dao;


    @Test
    public void testGetMemberEmployer() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final MemberEmployer memberEmployer = new MemberEmployer();
        given(dao.get(id)).willReturn(memberEmployer);

        //when
        MemberEmployer result = manager.get(id);

        //then
        assertSame(memberEmployer, result);
    }

    @Test
    public void testGetMemberEmployers() {
        log.debug("testing getAll...");
        //given
        final List memberEmployers = new ArrayList();
        given(dao.getAll()).willReturn(memberEmployers);

        //when
        List result = manager.getAll();

        //then
        assertSame(memberEmployers, result);
    }

    @Test
    public void testSaveMemberEmployer() {
        log.debug("testing save...");

        //given
        final MemberEmployer memberEmployer = new MemberEmployer();
        // enter all required fields
        


        given(dao.save(memberEmployer)).willReturn(memberEmployer);

        //when
        manager.save(memberEmployer);

        //then
        verify(dao).save(memberEmployer);
    }

    @Test
    public void testRemoveMemberEmployer() {
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