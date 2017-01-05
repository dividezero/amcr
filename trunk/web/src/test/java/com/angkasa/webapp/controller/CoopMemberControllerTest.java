package com.angkasa.webapp.controller;

import com.angkasa.service.CoopMemberManager;
import com.angkasa.model.CoopMember;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoopMemberControllerTest extends BaseControllerTestCase {
    @Autowired
    private CoopMemberController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("coopMemberList"));
        assertTrue(((List) m.get("coopMemberList")).size() > 0);
    }

//    @Test
//    public void testSearch() throws Exception {
//        // regenerate indexes
//        CoopMemberManager coopMemberManager = (CoopMemberManager) applicationContext.getBean("coopMemberManager");
//        coopMemberManager.reindex();
//
//        Model model = controller.handleRequest("*");
//        Map m = model.asMap();
//        List results = (List) m.get("coopMemberList");
//        assertNotNull(results);
//        assertEquals(3, results.size());
//    }
}