package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.MemberImportDao;
import com.angkasa.model.MemberImport;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class MemberImportManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private MemberImportManagerImpl manager;

    @Mock
    private MemberImportDao dao;


    @Test
    public void testGetMemberImport() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final MemberImport memberImport = new MemberImport();
        given(dao.get(id)).willReturn(memberImport);

        //when
        MemberImport result = manager.get(id);

        //then
        assertSame(memberImport, result);
    }

    @Test
    public void testGetMemberImports() {
        log.debug("testing getAll...");
        //given
        final List memberImports = new ArrayList();
        given(dao.getAll()).willReturn(memberImports);

        //when
        List result = manager.getAll();

        //then
        assertSame(memberImports, result);
    }

    @Test
    public void testSaveMemberImport() {
        log.debug("testing save...");

        //given
        final MemberImport memberImport = new MemberImport();
        // enter all required fields
        memberImport.setName("MgYmAjVhNjOnHfJvNqTcHnGoSfWpNvMqCySgTwZyLpTgVxRoNjQjUiChEmTbPjAiPzIbLaEnXhXaYmFcQgLhJwNtGgTzMdZpLuYnKtUaSaXjUiXzQsQzLmIvCnCdObEnBaFmRmGxJnTvHaPdPuIoXdVsZwGvLgWqEfBiTkIoBjNsOiWbStWlSxDfGeJfLlAmUvKuVzQcCaZpOeFvAoYiNmYwWxOfKwHoBmBsYwQbOjViYeKyOyNaQcKrApRrNyT");
        


        given(dao.save(memberImport)).willReturn(memberImport);

        //when
        manager.save(memberImport);

        //then
        verify(dao).save(memberImport);
    }

    @Test
    public void testRemoveMemberImport() {
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