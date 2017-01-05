package com.angkasa.webapp.controller;

import com.angkasa.service.SurveyQuestionChoiceManager;
import com.angkasa.model.SurveyQuestionChoice;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class SurveyQuestionChoiceControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyQuestionChoiceController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("surveyQuestionChoiceList"));
        assertTrue(((List) m.get("surveyQuestionChoiceList")).size() > 0);
    }

//    @Test
//    public void testSearch() throws Exception {
//        // regenerate indexes
//        SurveyQuestionChoiceManager surveyQuestionChoiceManager = (SurveyQuestionChoiceManager) applicationContext.getBean("surveyQuestionChoiceManager");
//        surveyQuestionChoiceManager.reindex();
//
//        Model model = controller.handleRequest("*");
//        Map m = model.asMap();
//        List results = (List) m.get("surveyQuestionChoiceList");
//        assertNotNull(results);
//        assertEquals(3, results.size());
//    }
}