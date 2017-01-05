package com.angkasa.webapp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.angkasa.service.ContactPersonManager;

public class ContactPersonControllerTest extends BaseControllerTestCase {
    @Autowired
    private ContactPersonController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("contactPersonList"));
        assertTrue(((List) m.get("contactPersonList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        ContactPersonManager contactPersonManager = (ContactPersonManager) applicationContext.getBean("contactPersonManager");
        contactPersonManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("contactPersonList");
        assertNotNull(results);
		assertEquals(3, results.size());
	}
}