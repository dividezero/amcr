package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.Survey;
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

public class SurveyFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyFormController form;
    private Survey survey;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/surveyform");
        request.addParameter("id", "-1");

        survey = form.showForm(request);
        assertNotNull(survey);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/surveyform");
        request.addParameter("id", "-1");

        survey = form.showForm(request);
        assertNotNull(survey);

        request = newPost("/surveyform");

        survey = form.showForm(request);
        // update required fields
        survey.setName("DeJyKgLqGgBmViBiFuWrCzLcTeUuEsCdIcDmImMbIbCqWbOrZd");

        BindingResult errors = new DataBinder(survey).getBindingResult();
        form.onSubmit(survey, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/surveyform");
        request.addParameter("delete", "");
        survey = new Survey();
        survey.setId(-2L);

        BindingResult errors = new DataBinder(survey).getBindingResult();
        form.onSubmit(survey, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
