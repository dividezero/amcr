package com.angkasa.webapp.controller;

import com.angkasa.service.SystemSettingManager;
import com.angkasa.model.SystemSetting;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class SystemSettingControllerTest extends BaseControllerTestCase {
    @Autowired
    private SystemSettingController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("systemSettingList"));
        assertTrue(((List) m.get("systemSettingList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        SystemSettingManager systemSettingManager = (SystemSettingManager) applicationContext.getBean("systemSettingManager");
        systemSettingManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("systemSettingList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}