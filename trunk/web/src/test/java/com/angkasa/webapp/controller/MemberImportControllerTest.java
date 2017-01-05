package com.angkasa.webapp.controller;

import com.angkasa.service.MemberImportManager;
import com.angkasa.model.MemberImport;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class MemberImportControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberImportController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("memberImportList"));
        assertTrue(((List) m.get("memberImportList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        MemberImportManager memberImportManager = (MemberImportManager) applicationContext.getBean("memberImportManager");
        memberImportManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("memberImportList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}