package com.angkasa.webapp.controller;

import com.angkasa.service.BankManager;
import com.angkasa.model.Bank;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class BankControllerTest extends BaseControllerTestCase {
    @Autowired
    private BankController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("bankList"));
        assertTrue(((List) m.get("bankList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        BankManager bankManager = (BankManager) applicationContext.getBean("bankManager");
        bankManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("bankList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}