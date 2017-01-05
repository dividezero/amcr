package com.angkasa.webapp.controller;

import com.angkasa.service.DmsMemberUpdateManager;
import com.angkasa.model.DmsMemberUpdate;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class DmsMemberUpdateControllerTest extends BaseControllerTestCase {
    @Autowired
    private DmsMemberUpdateController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("dmsMemberUpdateList"));
        assertTrue(((List) m.get("dmsMemberUpdateList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        DmsMemberUpdateManager dmsMemberUpdateManager = (DmsMemberUpdateManager) applicationContext.getBean("dmsMemberUpdateManager");
        dmsMemberUpdateManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("dmsMemberUpdateList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}