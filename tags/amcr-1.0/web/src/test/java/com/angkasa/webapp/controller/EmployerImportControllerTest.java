package com.angkasa.webapp.controller;

import com.angkasa.service.EmployerImportManager;
import com.angkasa.model.EmployerImport;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmployerImportControllerTest extends BaseControllerTestCase {
    @Autowired
    private EmployerImportController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("employerImportList"));
        assertTrue(((List) m.get("employerImportList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        EmployerImportManager employerImportManager = (EmployerImportManager) applicationContext.getBean("employerImportManager");
        employerImportManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("employerImportList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}