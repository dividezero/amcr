package com.angkasa.webapp.controller;

import com.angkasa.service.MemberProductManager;
import com.angkasa.model.MemberProduct;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class MemberProductControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberProductController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("memberProductList"));
        assertTrue(((List) m.get("memberProductList")).size() > 0);
    }

}