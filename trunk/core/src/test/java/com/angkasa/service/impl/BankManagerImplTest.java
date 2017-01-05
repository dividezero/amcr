package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.BankDao;
import com.angkasa.model.Bank;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class BankManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private BankManagerImpl manager;

    @Mock
    private BankDao dao;


    @Test
    public void testGetBank() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Bank bank = new Bank();
        given(dao.get(id)).willReturn(bank);

        //when
        Bank result = manager.get(id);

        //then
        assertSame(bank, result);
    }

    @Test
    public void testGetBanks() {
        log.debug("testing getAll...");
        //given
        final List banks = new ArrayList();
        given(dao.getAll()).willReturn(banks);

        //when
        List result = manager.getAll();

        //then
        assertSame(banks, result);
    }

    @Test
    public void testSaveBank() {
        log.debug("testing save...");

        //given
        final Bank bank = new Bank();
        // enter all required fields
        bank.setCode("DmCvPoMsScCmRbNzScSzGoHnHbLvUzRrSqKaHrFfOpWiMxQkTi");
        bank.setName("MkJeJeGtIfVtLfTtBnGhXtWjRrYeBpHfWgNePgWdWkTbUzEvJdHzKnPbRxJoPtLbPuShMxWeKuMsPdVcTdWuPdYrThDsOfHvMpGdQkHrKaQgOgJkSfMhMkZbLcUzNnInFuWyJoNwCwHxWgIdZcDdFkQfWxKxHaIhAsVoNsDdBxYcNmTbCdDoLcZnZdIkCiHkXsIxHmNvBvYaHxMtBnImBtOmDxWzYoVxWiGmQgBqIwQoQyJaJfBsYvFjAy");
        


        given(dao.save(bank)).willReturn(bank);

        //when
        manager.save(bank);

        //then
        verify(dao).save(bank);
    }

    @Test
    public void testRemoveBank() {
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