package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.CoopImport;
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

public class CoopImportFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private CoopImportFormController form;
    private CoopImport coopImport;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/coopImportform");
        request.addParameter("id", "-1");

        ModelAndView model = form.showForm(request);
        coopImport = (CoopImport) model.getModel().get("coopImport");
        assertNotNull(coopImport);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/coopImportform");
        request.addParameter("id", "-1");

        ModelAndView model = form.showForm(request);
        coopImport = (CoopImport) model.getModel().get("coopImport");
        assertNotNull(coopImport);

        request = newPost("/coopImportform");

        model = form.showForm(request);
        coopImport = (CoopImport) model.getModel().get("coopImport");
        // update required fields
        coopImport.setName("RgDeKxOyNwAwTyGzBwMlMaRyUaSxYoLgWyNyTrKkDtFyGnTmXnEzDeLoJrJiEnUkXmWwKrZzTcGsKyJnXrYiVqQrMbMdElUtAeAeRdBqWvDfQmCbBrUtLbSrSrAkQxMoUcUjZwRlPyZfBmDzImJyUmYfDbIbBnBfRoQbSmGoKeAwSrZyQeDcZnJsFwLoFnYfAiRjWjPbJaRrDiJlWoKgEqDaAyHwSuBgIpIiWoHmBeZjGaUjGaZcGaXfLrGnYcM");

        BindingResult errors = new DataBinder(coopImport).getBindingResult();
        form.onSubmit(coopImport, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/coopImportform");
        request.addParameter("delete", "");
        coopImport = new CoopImport();
        coopImport.setId(-2L);

        BindingResult errors = new DataBinder(coopImport).getBindingResult();
        form.onSubmit(coopImport, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
