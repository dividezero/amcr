package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.SystemSetting;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SystemSettingDaoTest extends BaseDaoTestCase {
    @Autowired
    private SystemSettingDao systemSettingDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveSystemSetting() {
        SystemSetting systemSetting = new SystemSetting();

        // enter all required fields
        systemSetting.setPropertyName("PxRqIjKlWmQsImLvNvJnWrYfYxQoDsOqRvAaDxRoGsYwYmWpAzCgPnAdIhRbRjPdXkZkYrRsBjOiHgCdQxTlPdPxTbFoEyXrVwEiYoDtEoXdWvDpOpGoQeBmTiYwUyBkLoXvXrOsWmXcJvDbKcKeQsMyAdHjAlYiMhTdOgHuHoTtUsUxZyTmGaCjSlJuViDaBbKbDkYbUhVqDlRzObOyJpQvBjBzQxJzDwZzXhCyTcUpFzAyVqYmAlGxKxHeDvP");

        log.debug("adding systemSetting...");
        systemSetting = systemSettingDao.save(systemSetting);

        systemSetting = systemSettingDao.get(systemSetting.getId());

        assertNotNull(systemSetting.getId());

        log.debug("removing systemSetting...");

        systemSettingDao.remove(systemSetting.getId());

        // should throw DataAccessException 
        systemSettingDao.get(systemSetting.getId());
    }
}