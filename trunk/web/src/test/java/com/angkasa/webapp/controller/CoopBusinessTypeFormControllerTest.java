package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.CoopBusinessType;
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

public class CoopBusinessTypeFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private CoopBusinessTypeFormController form;
    private CoopBusinessType coopBusinessType;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/coopBusinessTypeform");
        request.addParameter("id", "-1");

        coopBusinessType = form.showForm(request);
        assertNotNull(coopBusinessType);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/coopBusinessTypeform");
        request.addParameter("id", "-1");

        coopBusinessType = form.showForm(request);
        assertNotNull(coopBusinessType);

        request = newPost("/coopBusinessTypeform");

        coopBusinessType = form.showForm(request);
        // update required fields
        coopBusinessType.setCode("Gd");
        coopBusinessType.setDescription("AxKsLiJfMkXbRfWxZmCsHiCsKeMyVqRkOjFxEpUbEoFtKgPyZfKsJaZyPhBpHvOwYdEgDnPsBnRsMcDaRtQuKxUdGtWlWeAwQhSaTzRlGiMlIlXqXgGpYmBbDlGvWqJxIoXhKzZyBcMeRzXnMtSgLdCkUjYvNzKqBwPeRjJlRlWdFfFlLpOjRfZzHiRlZeCxPvRxFcYa");
        coopBusinessType.setName("DcEpEwRvTkZmQjWeJuKgVhMlDqYnQmJaMyQtOhZdVuVuNwQoTv");

        BindingResult errors = new DataBinder(coopBusinessType).getBindingResult();
        form.onSubmit(coopBusinessType, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/coopBusinessTypeform");
        request.addParameter("delete", "");
        coopBusinessType = new CoopBusinessType();
        coopBusinessType.setId(-2L);

        BindingResult errors = new DataBinder(coopBusinessType).getBindingResult();
        form.onSubmit(coopBusinessType, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
