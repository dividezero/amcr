package com.angkasa.webapp.controller;

import com.angkasa.service.EmployerManager;
import com.angkasa.model.Employer;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmployerControllerTest extends BaseControllerTestCase {
    @Autowired
    private EmployerController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("employerList"));
        assertTrue(((List) m.get("employerList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        EmployerManager employerManager = (EmployerManager) applicationContext.getBean("employerManager");
        employerManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("employerList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}