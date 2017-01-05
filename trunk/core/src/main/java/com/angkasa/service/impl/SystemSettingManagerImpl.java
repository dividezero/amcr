package com.angkasa.service.impl;

import com.angkasa.dao.SystemSettingDao;
import com.angkasa.model.SystemSetting;
import com.angkasa.service.SystemSettingManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("systemSettingManager")
@WebService(serviceName = "SystemSettingService", endpointInterface = "com.angkasa.service.SystemSettingManager")
public class SystemSettingManagerImpl extends GenericManagerImpl<SystemSetting, Long> implements SystemSettingManager {
    SystemSettingDao systemSettingDao;

    @Autowired
    public SystemSettingManagerImpl(SystemSettingDao systemSettingDao) {
        super(systemSettingDao);
        this.systemSettingDao = systemSettingDao;
    }

    public String valueByPropertyName(String propertyName){
        return systemSettingDao.valueByPropertyName(propertyName);
    }
    public SystemSetting getByPropertyName(String propertyName){
        return systemSettingDao.getByPropertyName(propertyName);
    }
}