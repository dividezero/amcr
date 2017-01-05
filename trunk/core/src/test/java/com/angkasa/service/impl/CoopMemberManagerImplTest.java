package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.CoopMemberDao;
import com.angkasa.model.CoopMember;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class CoopMemberManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private CoopMemberManagerImpl manager;

    @Mock
    private CoopMemberDao dao;


    @Test
    public void testGetCoopMember() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final CoopMember coopMember = new CoopMember();
        given(dao.get(id)).willReturn(coopMember);

        //when
        CoopMember result = manager.get(id);

        //then
        assertSame(coopMember, result);
    }

    @Test
    public void testGetCoopMembers() {
        log.debug("testing getAll...");
        //given
        final List coopMembers = new ArrayList();
        given(dao.getAll()).willReturn(coopMembers);

        //when
        List result = manager.getAll();

        //then
        assertSame(coopMembers, result);
    }

    @Test
    public void testSaveCoopMember() {
        log.debug("testing save...");

        //given
        final CoopMember coopMember = new CoopMember();
        // enter all required fields
        


        given(dao.save(coopMember)).willReturn(coopMember);

        //when
        manager.save(coopMember);

        //then
        verify(dao).save(coopMember);
    }

    @Test
    public void testRemoveCoopMember() {
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