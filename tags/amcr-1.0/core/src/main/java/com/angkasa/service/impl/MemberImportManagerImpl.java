package com.angkasa.service.impl;

import com.angkasa.dao.MemberImportDao;
import com.angkasa.model.Member;
import com.angkasa.model.MemberImport;
import com.angkasa.service.DocumentManager;
import com.angkasa.service.MemberImportManager;
import com.angkasa.service.impl.GenericManagerImpl;

import com.angkasa.util.PropsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("memberImportManager")
@WebService(serviceName = "MemberImportService", endpointInterface = "com.angkasa.service.MemberImportManager")
public class MemberImportManagerImpl extends GenericManagerImpl<MemberImport, Long> implements MemberImportManager {
    MemberImportDao memberImportDao;

    @Autowired
    DocumentManager documentManager;

    @Autowired
    PropsUtil propsUtil;

    @Autowired
    public MemberImportManagerImpl(MemberImportDao memberImportDao) {
        super(memberImportDao);
        this.memberImportDao = memberImportDao;
    }

    @Override
    public MemberImport saveWithFile(MemberImport memberImport) throws Exception{
        return memberImportDao.saveWithFile(memberImport);
    }

}