package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.Employer;
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

public class EmployerFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private EmployerFormController form;
    private Employer employer;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/employerform");
        request.addParameter("id", "-1");

        employer = form.showForm(request);
        assertNotNull(employer);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/employerform");
        request.addParameter("id", "-1");

        employer = form.showForm(request);
        assertNotNull(employer);

        request = newPost("/employerform");

        employer = form.showForm(request);
        // update required fields
        employer.setEmployerCode("JzWvQfKjTf");
        employer.setName("ToXcBuEpYuFgHaXwGlUtPzUgZeGpKwOsKcWpBcJwNpRkLaTlZlSzDqWsQbFqJvFfIfWoAwKrPzLoGmPfGnZgOiQuIaXbNxLzYxToOlIxTaIkQnDtClEnLmWjBpKfQkNjHyHzCvYtZzIoTaIlHlHqDrIzPnYmVmUwTvDgErPePeRrJlNuRoUnPhFbVbIdRkRzPxPlQgIa");

        BindingResult errors = new DataBinder(employer).getBindingResult();
        form.onSubmit(employer, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/employerform");
        request.addParameter("delete", "");
        employer = new Employer();
        employer.setId(-2L);

        BindingResult errors = new DataBinder(employer).getBindingResult();
        form.onSubmit(employer, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
