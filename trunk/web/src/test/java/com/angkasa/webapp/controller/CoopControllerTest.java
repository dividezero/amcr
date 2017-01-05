package com.angkasa.webapp.controller;

import com.angkasa.service.CoopManager;
import com.angkasa.model.Coop;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoopControllerTest extends BaseControllerTestCase {
    @Autowired
    private CoopController controller;
    private MockHttpServletRequest request;

    @Test
    public void testHandleRequest() throws Exception {
        request = newGet("/coops");
        Model model = controller.handleRequest(null, request);
        Map m = model.asMap();
        assertNotNull(m.get("coopList"));
        assertTrue(((List) m.get("coopList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        request = newGet("/coops");
        CoopManager coopManager = (CoopManager) applicationContext.getBean("coopManager");
        coopManager.reindex();

        Model model = controller.handleRequest("*", request);
        Map m = model.asMap();
        List results = (List) m.get("coopList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}