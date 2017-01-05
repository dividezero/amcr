package com.angkasa.webapp.controller;

import com.angkasa.service.SurveyManager;
import com.angkasa.model.Survey;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class SurveyControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("surveyList"));
        assertTrue(((List) m.get("surveyList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        SurveyManager surveyManager = (SurveyManager) applicationContext.getBean("surveyManager");
        surveyManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("surveyList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}