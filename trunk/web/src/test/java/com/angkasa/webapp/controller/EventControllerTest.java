package com.angkasa.webapp.controller;

import com.angkasa.service.EventManager;
import com.angkasa.model.Event;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class EventControllerTest extends BaseControllerTestCase {
    @Autowired
    private EventController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("eventList"));
        assertTrue(((List) m.get("eventList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        EventManager eventManager = (EventManager) applicationContext.getBean("eventManager");
        eventManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("eventList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}