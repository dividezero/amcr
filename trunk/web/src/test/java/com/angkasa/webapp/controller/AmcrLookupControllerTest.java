package com.angkasa.webapp.controller;

import com.angkasa.service.AmcrLookupManager;
import com.angkasa.model.AmcrLookup;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class AmcrLookupControllerTest extends BaseControllerTestCase {
    @Autowired
    private AmcrLookupController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("amcrLookupList"));
        assertTrue(((List) m.get("amcrLookupList")).size() > 0);
    }

//    @Test
//    public void testSearch() throws Exception {
//        // regenerate indexes
//        AmcrLookupManager amcrLookupManager = (AmcrLookupManager) applicationContext.getBean("amcrLookupManager");
//        amcrLookupManager.reindex();
//
//        Model model = controller.handleRequest("*");
//        Map m = model.asMap();
//        List results = (List) m.get("amcrLookupList");
//        assertNotNull(results);
//        assertEquals(2, results.size());
//    }
}