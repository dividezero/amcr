package com.angkasa.webapp.controller;

import com.angkasa.service.SurveyResponseManager;
import com.angkasa.model.SurveyResponse;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class SurveyResponseControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyResponseController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("surveyResponseList"));
        assertTrue(((List) m.get("surveyResponseList")).size() > 0);
    }

//    @Test
//    public void testSearch() throws Exception {
//        // regenerate indexes
//        SurveyResponseManager surveyResponseManager = (SurveyResponseManager) applicationContext.getBean("surveyResponseManager");
//        surveyResponseManager.reindex();
//
//        Model model = controller.handleRequest("*");
//        Map m = model.asMap();
//        List results = (List) m.get("surveyResponseList");
//        assertNotNull(results);
//        assertEquals(3, results.size());
//    }
}