package com.angkasa.service.impl;

import com.angkasa.dao.EmployerDao;
import com.angkasa.dao.MemberEmployerDao;
import com.angkasa.model.Employer;
import com.angkasa.service.EmployerManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("employerManager")
@WebService(serviceName = "EmployerService", endpointInterface = "com.angkasa.service.EmployerManager")
public class EmployerManagerImpl extends GenericManagerImpl<Employer, Long> implements EmployerManager {
    EmployerDao employerDao;

    @Autowired
    MemberEmployerDao memberEmployerDao;

    @Autowired
    public EmployerManagerImpl(EmployerDao employerDao) {
        super(employerDao);
        this.employerDao = employerDao;
    }

    public List<Employer> processEmployerImport(List<Employer> employerList) {
        return employerDao.processEmployerImport(employerList);
    }

    @Override
    public boolean employerCodeExists(String empCode) {
        return employerDao.employerCodeExists(empCode);
    }

    @Override
    public Employer getActiveEmployerByMemberId(Long memberId) {
        return memberEmployerDao.getActiveMemberEmployerByMemberId(memberId).getEmployer();
    }
}