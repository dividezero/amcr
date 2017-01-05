package com.angkasa.service.impl;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.angkasa.dao.MemberDao;
import com.angkasa.model.Member;

public class MemberManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private MemberManagerImpl manager;

    @Mock
    private MemberDao dao;


    @Test
    public void testGetMember() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Member member = new Member();
        given(dao.get(id)).willReturn(member);

        //when
        Member result = manager.get(id);

        //then
        assertSame(member, result);
    }

    @Test
    public void testGetMembers() {
        log.debug("testing getAll...");
        //given
        final List members = new ArrayList();
        given(dao.getAll()).willReturn(members);

        //when
        List result = manager.getAll();

        //then
        assertSame(members, result);
    }

    @Test
    public void testSaveMember() {
        log.debug("testing save...");

        //given
        final Member member = new Member();
        // enter all required fields
        member.setGender("E");
        member.setIcNumber("CsNyFmZmNcIu");
        member.setMembershipNo("NzHdSsHqYg");
        member.setName("MdCfZeJuIhIkJoGeKyYmKfGhZoVzJaGrThExFlBwAbZoTqLaUc");
        member.setPhoneNo("IqAkAxEgFeVr");
        


        given(dao.save(member)).willReturn(member);

        //when
        manager.save(member);

        //then
        verify(dao).save(member);
    }

    @Test
    public void testRemoveMember() {
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