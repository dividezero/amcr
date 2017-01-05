package com.angkasa.webapp.controller;

import com.angkasa.service.SurveyQuestionManager;
import com.angkasa.model.SurveyQuestion;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class SurveyQuestionControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyQuestionController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("surveyQuestionList"));
        assertTrue(((List) m.get("surveyQuestionList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        SurveyQuestionManager surveyQuestionManager = (SurveyQuestionManager) applicationContext.getBean("surveyQuestionManager");
        surveyQuestionManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("surveyQuestionList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}