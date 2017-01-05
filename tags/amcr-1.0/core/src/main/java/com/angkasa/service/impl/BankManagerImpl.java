package com.angkasa.service.impl;

import com.angkasa.dao.BankDao;
import com.angkasa.model.Bank;
import com.angkasa.service.BankManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("bankManager")
@WebService(serviceName = "BankService", endpointInterface = "com.angkasa.service.BankManager")
public class BankManagerImpl extends GenericManagerImpl<Bank, Long> implements BankManager {
    BankDao bankDao;

    @Autowired
    public BankManagerImpl(BankDao bankDao) {
        super(bankDao);
        this.bankDao = bankDao;
    }
}