package com.angkasa.webapp.controller;

import com.angkasa.service.EventMemberManager;
import com.angkasa.model.EventMember;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class EventMemberControllerTest extends BaseControllerTestCase {
    @Autowired
    private EventMemberController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("eventMemberList"));
        assertTrue(((List) m.get("eventMemberList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        EventMemberManager eventMemberManager = (EventMemberManager) applicationContext.getBean("eventMemberManager");
        eventMemberManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("eventMemberList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}