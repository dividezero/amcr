package com.angkasa.service.impl;

import com.angkasa.dao.MemberProductDao;
import com.angkasa.model.MemberProduct;
import com.angkasa.service.MemberProductManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("memberProductManager")
@WebService(serviceName = "MemberProductService", endpointInterface = "com.angkasa.service.MemberProductManager")
public class MemberProductManagerImpl extends GenericManagerImpl<MemberProduct, Long> implements MemberProductManager {
    MemberProductDao memberProductDao;

    @Autowired
    public MemberProductManagerImpl(MemberProductDao memberProductDao) {
        super(memberProductDao);
        this.memberProductDao = memberProductDao;
    }
}