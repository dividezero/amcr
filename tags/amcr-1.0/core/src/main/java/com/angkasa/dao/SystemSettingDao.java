package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.SystemSetting;

/**
 * An interface that provides a data management interface to the SystemSetting table.
 */
public interface SystemSettingDao extends GenericDao<SystemSetting, Long> {

    String valueByPropertyName(String propertyName);
    SystemSetting getByPropertyName(String propertyName);

}