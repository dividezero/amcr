package com.angkasa.service.impl;

import com.angkasa.dao.DmsMemberUpdateDao;
import com.angkasa.model.DmsMemberUpdate;
import com.angkasa.service.DmsMemberUpdateManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("dmsMemberUpdateManager")
@WebService(serviceName = "DmsMemberUpdateService", endpointInterface = "com.angkasa.service.DmsMemberUpdateManager")
public class DmsMemberUpdateManagerImpl extends GenericManagerImpl<DmsMemberUpdate, Long> implements DmsMemberUpdateManager {
    DmsMemberUpdateDao dmsMemberUpdateDao;

    @Autowired
    public DmsMemberUpdateManagerImpl(DmsMemberUpdateDao dmsMemberUpdateDao) {
        super(dmsMemberUpdateDao);
        this.dmsMemberUpdateDao = dmsMemberUpdateDao;
    }

    @Override
    public DmsMemberUpdate saveNewDmsMemberUpdate(String icNumber, String phoneNum, String status){
        DmsMemberUpdate dmsMemberUpdate = new DmsMemberUpdate();
        dmsMemberUpdate.setIcNumber(icNumber);
        dmsMemberUpdate.setPhoneNum(phoneNum);
        dmsMemberUpdate.setStatus(status);

        return dmsMemberUpdateDao.save(dmsMemberUpdate);
    }
}