package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.MemberProductTransactionDeduction;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface MemberProductTransactionDeductionManager extends GenericManager<MemberProductTransactionDeduction, Long> {
    
}