package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.Bank;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BankDaoTest extends BaseDaoTestCase {
    @Autowired
    private BankDao bankDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveBank() {
        Bank bank = new Bank();

        // enter all required fields
        bank.setCode("BnMdNcTfWyWmIdEzYdMeZyPrSbIgUxKzAkFqPlNaXhJlLpZyMt");
        bank.setName("EvTsMtXoCsYfFwHlTnXjZsMhGoMnGnZfMeHgYlXpRwIaVuMoKyCfKbVyQhGeAnIrDgImIbEiNgQoEuDgLwIhZkGgEcQnAvTfHqSbVvJtTuDoCcAuXxFwQmIfAtIjEcRxHeWfFnLwMaUjXkBvBuWmIdOjTyPaTjCxLuIkLpOjQaAxXcAcVsEgJtXeUcNzNaExFrZjWmOgSaEfDxWpNeKuEkZeUlIbEdZtNpCrKaNvCcUdQuAwWzExBuBtRh");

        log.debug("adding bank...");
        bank = bankDao.save(bank);

        bank = bankDao.get(bank.getId());

        assertNotNull(bank.getId());

        log.debug("removing bank...");

        bankDao.remove(bank.getId());

        // should throw DataAccessException 
        bankDao.get(bank.getId());
    }
}