package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.Bank;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface BankManager extends GenericManager<Bank, Long> {
    
}