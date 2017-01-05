package com.angkasa.webapp.controller;

import com.angkasa.service.MemberEmployerManager;
import com.angkasa.model.MemberEmployer;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class MemberEmployerControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberEmployerController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("memberEmployerList"));
        assertTrue(((List) m.get("memberEmployerList")).size() > 0);
    }

//    @Test
//    public void testSearch() throws Exception {
//        // regenerate indexes
//        MemberEmployerManager memberEmployerManager = (MemberEmployerManager) applicationContext.getBean("memberEmployerManager");
//        memberEmployerManager.reindex();
//
//        Model model = controller.handleRequest("*");
//        Map m = model.asMap();
//        List results = (List) m.get("memberEmployerList");
//        assertNotNull(results);
//        assertEquals(3, results.size());
//    }
}