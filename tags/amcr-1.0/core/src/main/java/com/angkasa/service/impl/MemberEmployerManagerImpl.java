package com.angkasa.service.impl;

import com.angkasa.dao.MemberEmployerDao;
import com.angkasa.model.MemberEmployer;
import com.angkasa.service.MemberEmployerManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("memberEmployerManager")
@WebService(serviceName = "MemberEmployerService", endpointInterface = "com.angkasa.service.MemberEmployerManager")
public class MemberEmployerManagerImpl extends GenericManagerImpl<MemberEmployer, Long> implements MemberEmployerManager {
    MemberEmployerDao memberEmployerDao;

    @Autowired
    public MemberEmployerManagerImpl(MemberEmployerDao memberEmployerDao) {
        super(memberEmployerDao);
        this.memberEmployerDao = memberEmployerDao;
    }
}