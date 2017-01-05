package com.angkasa.webapp.controller;

import com.angkasa.service.CoopBusinessTypeManager;
import com.angkasa.model.CoopBusinessType;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoopBusinessTypeControllerTest extends BaseControllerTestCase {
    @Autowired
    private CoopBusinessTypeController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("coopBusinessTypeList"));
        assertTrue(((List) m.get("coopBusinessTypeList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        CoopBusinessTypeManager coopBusinessTypeManager = (CoopBusinessTypeManager) applicationContext.getBean("coopBusinessTypeManager");
        coopBusinessTypeManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("coopBusinessTypeList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}