package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.SystemSetting;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface SystemSettingManager extends GenericManager<SystemSetting, Long> {

    String valueByPropertyName(String propertyName);
    SystemSetting getByPropertyName(String propertyName);
}