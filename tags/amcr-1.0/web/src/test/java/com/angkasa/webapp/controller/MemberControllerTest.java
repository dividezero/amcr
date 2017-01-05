package com.angkasa.webapp.controller;

import com.angkasa.service.MemberManager;
import com.angkasa.model.Member;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class MemberControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberController controller;

    private MockHttpServletRequest request;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null, request);
        Map m = model.asMap();
        assertNotNull(m.get("memberList"));
        assertTrue(((List) m.get("memberList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        MemberManager memberManager = (MemberManager) applicationContext.getBean("memberManager");
        memberManager.reindex();

        Model model = controller.handleRequest("*", request);
        Map m = model.asMap();
        List results = (List) m.get("memberList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}