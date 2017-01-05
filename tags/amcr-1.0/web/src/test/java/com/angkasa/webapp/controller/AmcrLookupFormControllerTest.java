package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.AmcrLookup;
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

public class AmcrLookupFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private AmcrLookupFormController form;
    private AmcrLookup amcrLookup;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/amcrLookupform");
        request.addParameter("id", "-1");

        amcrLookup = form.showForm(request);
        assertNotNull(amcrLookup);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/amcrLookupform");
        request.addParameter("id", "-1");

        amcrLookup = form.showForm(request);
        assertNotNull(amcrLookup);

        request = newPost("/amcrLookupform");

        amcrLookup = form.showForm(request);
        // update required fields
        amcrLookup.setTableName("ToLfDsEmJePbNzAuZfMjSdFmGoRsDwJgSrJfBaGhDgVgNyAlWbSqFsGpIoIpNyUjYmWaYqObIwOjRhRtIcDgVuZiGnSmUsOjSiNiBtQaKsBmKvOgCwXiHrFwSqYtAnZxXxVdPmVnUaHrXgLlSiDxDbIvIsAdVdKoZrUjAuIwKvWrFcVnNmLuAzPyGtBjHsJgBkSmJqXvMtLtYbHaCfWnHfUcFsOhAjPmLfYrYoJwLzCuTnMdReYgUwBmKtDvPpQ");

        BindingResult errors = new DataBinder(amcrLookup).getBindingResult();
        form.onSubmit(amcrLookup, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/amcrLookupform");
        request.addParameter("delete", "");
        amcrLookup = new AmcrLookup();
        amcrLookup.setId(-2L);

        BindingResult errors = new DataBinder(amcrLookup).getBindingResult();
        form.onSubmit(amcrLookup, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
