package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.SurveyResponse;
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

public class SurveyResponseFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyResponseFormController form;
    private SurveyResponse surveyResponse;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/surveyResponseform");
        request.addParameter("id", "-1");

        surveyResponse = form.showForm(request);
        assertNotNull(surveyResponse);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/surveyResponseform");
        request.addParameter("id", "-1");

        surveyResponse = form.showForm(request);
        assertNotNull(surveyResponse);

        request = newPost("/surveyResponseform");

        surveyResponse = form.showForm(request);
        // update required fields
        surveyResponse.setCoopMemberId(-1L);
        surveyResponse.setSurveyId(-1L);

        BindingResult errors = new DataBinder(surveyResponse).getBindingResult();
        form.onSubmit(surveyResponse, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/surveyResponseform");
        request.addParameter("delete", "");
        surveyResponse = new SurveyResponse();
        surveyResponse.setId(-2L);

        BindingResult errors = new DataBinder(surveyResponse).getBindingResult();
        form.onSubmit(surveyResponse, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
