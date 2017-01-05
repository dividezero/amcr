package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.SurveyQuestion;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SurveyQuestionFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyQuestionFormController form;
    private SurveyQuestion surveyQuestion;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/surveyQuestionform");
        request.addParameter("id", "-1");

        surveyQuestion = form.showForm(request);
        assertNotNull(surveyQuestion);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/surveyQuestionform");
        request.addParameter("id", "-1");

        surveyQuestion = form.showForm(request);
        assertNotNull(surveyQuestion);

        request = newPost("/surveyQuestionform");

        surveyQuestion = form.showForm(request);
        // update required fields
        surveyQuestion.setDescription("desc");
        surveyQuestion.setQuestionType("A");
        surveyQuestion.setSurveyId(-1L);

        BindingResult errors = new DataBinder(surveyQuestion).getBindingResult();
        form.onSubmit(surveyQuestion, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/surveyQuestionform");
        request.addParameter("delete", "");
        surveyQuestion = new SurveyQuestion();
        surveyQuestion.setId(-2L);

        BindingResult errors = new DataBinder(surveyQuestion).getBindingResult();
        form.onSubmit(surveyQuestion, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
