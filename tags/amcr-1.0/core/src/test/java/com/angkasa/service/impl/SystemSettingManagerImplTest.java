package com.angkasa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.angkasa.dao.SystemSettingDao;
import com.angkasa.model.SystemSetting;
import com.angkasa.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class SystemSettingManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private SystemSettingManagerImpl manager;

    @Mock
    private SystemSettingDao dao;


    @Test
    public void testGetSystemSetting() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final SystemSetting systemSetting = new SystemSetting();
        given(dao.get(id)).willReturn(systemSetting);

        //when
        SystemSetting result = manager.get(id);

        //then
        assertSame(systemSetting, result);
    }

    @Test
    public void testGetSystemSettings() {
        log.debug("testing getAll...");
        //given
        final List systemSettings = new ArrayList();
        given(dao.getAll()).willReturn(systemSettings);

        //when
        List result = manager.getAll();

        //then
        assertSame(systemSettings, result);
    }

    @Test
    public void testSaveSystemSetting() {
        log.debug("testing save...");

        //given
        final SystemSetting systemSetting = new SystemSetting();
        // enter all required fields
        systemSetting.setPropertyName("TrZbEjRfFtSdUpMqMbMmQmQkYiRmZlHwTwEpXnKrHrKuIcIeKkNyFtEhFaZnLrCtClLrIgNfOlYcThWnJmPfYdWeOzFsKnQxPpXoKfIsZrExJfVpHhKqRcVjGyKeDgLyJxYfVsVwItTdZqRfUgUjMkYdWzAgZwMnFmOuScKnZbAiZhLkXnIgGnOyXbBlUsKnRnBpZeTaSrErJjPsEuBuOiScAmLkZwIzDaXbOzBtFaYeJlMeWwKjQuJlBbKiFjK");
        


        given(dao.save(systemSetting)).willReturn(systemSetting);

        //when
        manager.save(systemSetting);

        //then
        verify(dao).save(systemSetting);
    }

    @Test
    public void testRemoveSystemSetting() {
        log.debug("testing remove...");

        //given
        final Long id = -11L;
        willDoNothing().given(dao).remove(id);

        //when
        manager.remove(id);

        //then
        verify(dao).remove(id);
    }
}