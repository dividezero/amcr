package com.angkasa.service.impl;

import com.angkasa.dao.MemberProductTransactionDeductionDao;
import com.angkasa.model.MemberProductTransactionDeduction;
import com.angkasa.service.MemberProductTransactionDeductionManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("memberProductTransactionDeductionManager")
@WebService(serviceName = "MemberProductTransactionDeductionService", endpointInterface = "com.angkasa.service.MemberProductTransactionDeductionManager")
public class MemberProductTransactionDeductionManagerImpl extends GenericManagerImpl<MemberProductTransactionDeduction, Long> implements MemberProductTransactionDeductionManager {
    MemberProductTransactionDeductionDao memberProductTransactionDeductionDao;

    @Autowired
    public MemberProductTransactionDeductionManagerImpl(MemberProductTransactionDeductionDao memberProductTransactionDeductionDao) {
        super(memberProductTransactionDeductionDao);
        this.memberProductTransactionDeductionDao = memberProductTransactionDeductionDao;
    }
}