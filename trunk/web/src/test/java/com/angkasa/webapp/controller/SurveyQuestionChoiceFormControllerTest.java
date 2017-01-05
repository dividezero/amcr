package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.SurveyQuestionChoice;
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

public class SurveyQuestionChoiceFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private SurveyQuestionChoiceFormController form;
    private SurveyQuestionChoice surveyQuestionChoice;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/surveyQuestionChoiceform");
        request.addParameter("id", "-1");

        surveyQuestionChoice = form.showForm(request);
        assertNotNull(surveyQuestionChoice);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/surveyQuestionChoiceform");
        request.addParameter("id", "-1");

        surveyQuestionChoice = form.showForm(request);
        assertNotNull(surveyQuestionChoice);

        request = newPost("/surveyQuestionChoiceform");

        surveyQuestionChoice = form.showForm(request);
        // update required fields
        surveyQuestionChoice.setDescription("IqMmGkEgWmVtOiZcUyYjLcQcAaUgAyLyNxPtRhOeRgRuRyYnJjAzWkFhRoHpDfSrTeLwSiGbTdAqDrWmOeGoFfSaXlPhXiFaIhLlSnDuVnCsMxRlYcFuOmWkQpJzJmQjNoIrKrKpSoLoTgJgImVgUbWaXwOvVuWtGwFmTiUtMcYzKhIiUxHhMkRpZdKuDiNhHgJfDiCn");
        surveyQuestionChoice.setSequence(964762776);
        surveyQuestionChoice.setValue("Test Val");
        surveyQuestionChoice.setSurveyQuestionId(-1L);

        BindingResult errors = new DataBinder(surveyQuestionChoice).getBindingResult();
        form.onSubmit(surveyQuestionChoice, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/surveyQuestionChoiceform");
        request.addParameter("delete", "");
        surveyQuestionChoice = new SurveyQuestionChoice();
        surveyQuestionChoice.setId(-2L);

        BindingResult errors = new DataBinder(surveyQuestionChoice).getBindingResult();
        form.onSubmit(surveyQuestionChoice, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
