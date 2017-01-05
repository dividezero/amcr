package com.angkasa.dao.hibernate;

import com.angkasa.model.Bank;
import com.angkasa.dao.BankDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("bankDao")
public class BankDaoHibernate extends GenericDaoHibernate<Bank, Long> implements BankDao {

    public BankDaoHibernate() {
        super(Bank.class);
    }
}
