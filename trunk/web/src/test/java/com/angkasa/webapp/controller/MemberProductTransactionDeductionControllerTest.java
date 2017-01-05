package com.angkasa.webapp.controller;

import com.angkasa.service.MemberProductTransactionDeductionManager;
import com.angkasa.model.MemberProductTransactionDeduction;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class MemberProductTransactionDeductionControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberProductTransactionDeductionController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("memberProductTransactionDeductionList"));
        assertTrue(((List) m.get("memberProductTransactionDeductionList")).size() > 0);
    }


}