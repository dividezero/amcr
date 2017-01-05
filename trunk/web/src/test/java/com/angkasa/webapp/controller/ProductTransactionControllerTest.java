package com.angkasa.webapp.controller;

import com.angkasa.service.ProductTransactionManager;
import com.angkasa.model.ProductTransaction;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTransactionControllerTest extends BaseControllerTestCase {
    @Autowired
    private ProductTransactionController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("productTransactionList"));
        assertTrue(((List) m.get("productTransactionList")).size() > 0);
    }

}