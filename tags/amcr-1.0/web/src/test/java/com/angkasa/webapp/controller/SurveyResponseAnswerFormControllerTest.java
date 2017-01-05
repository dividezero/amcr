package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.SurveyResponseAnswer;
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

public class SurveyResponseAnswerFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyResponseAnswerFormController form;
    private SurveyResponseAnswer surveyResponseAnswer;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/surveyResponseAnswerform");
        request.addParameter("id", "-1");

        surveyResponseAnswer = form.showForm(request);
        assertNotNull(surveyResponseAnswer);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/surveyResponseAnswerform");
        request.addParameter("id", "-1");

        surveyResponseAnswer = form.showForm(request);
        assertNotNull(surveyResponseAnswer);

        request = newPost("/surveyResponseAnswerform");

        surveyResponseAnswer = form.showForm(request);
        // update required fields
        surveyResponseAnswer.setSurveyResponseId(-1L);

        BindingResult errors = new DataBinder(surveyResponseAnswer).getBindingResult();
        form.onSubmit(surveyResponseAnswer, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/surveyResponseAnswerform");
        request.addParameter("delete", "");
        surveyResponseAnswer = new SurveyResponseAnswer();
        surveyResponseAnswer.setId(-2L);

        BindingResult errors = new DataBinder(surveyResponseAnswer).getBindingResult();
        form.onSubmit(surveyResponseAnswer, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
