package com.angkasa.webapp.controller;

import com.angkasa.service.CoopImportManager;
import com.angkasa.model.CoopImport;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoopImportControllerTest extends BaseControllerTestCase {
    @Autowired
    private CoopImportController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("coopImportList"));
        assertTrue(((List) m.get("coopImportList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        CoopImportManager coopImportManager = (CoopImportManager) applicationContext.getBean("coopImportManager");
        coopImportManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("coopImportList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}