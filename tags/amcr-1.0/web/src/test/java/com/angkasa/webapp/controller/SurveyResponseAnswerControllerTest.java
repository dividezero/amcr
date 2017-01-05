package com.angkasa.webapp.controller;

import com.angkasa.service.SurveyResponseAnswerManager;
import com.angkasa.model.SurveyResponseAnswer;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class SurveyResponseAnswerControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyResponseAnswerController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("surveyResponseAnswerList"));
        assertTrue(((List) m.get("surveyResponseAnswerList")).size() > 0);
    }

//    @Test
//    public void testSearch() throws Exception {
//        // regenerate indexes
//        SurveyResponseAnswerManager surveyResponseAnswerManager = (SurveyResponseAnswerManager) applicationContext.getBean("surveyResponseAnswerManager");
//        surveyResponseAnswerManager.reindex();
//
//        Model model = controller.handleRequest("*");
//        Map m = model.asMap();
//        List results = (List) m.get("surveyResponseAnswerList");
//        assertNotNull(results);
//        assertEquals(3, results.size());
//    }
}