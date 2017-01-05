package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.ContactPerson;
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

public class ContactPersonFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private ContactPersonFormController form;
    private ContactPerson contactPerson;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/contactPersonform");
        request.addParameter("id", "-1");

        contactPerson = form.showForm(request);
        assertNotNull(contactPerson);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/contactPersonform");
        request.addParameter("id", "-1");

        contactPerson = form.showForm(request);
        assertNotNull(contactPerson);

        request = newPost("/contactPersonform");

        contactPerson = form.showForm(request);
        // update required fields
        contactPerson.setDescription("GsHjSlRwUmTuRqElPwKgWvChDjIkYcOfAmBlKvJcGoQvEsEeScZiZzBuVsBbVkOjQaEuKrQlPkCrIqVfVvXlPfJuOjFsXuHwEpJiDfEbVrHxSnVqXwDiJrOnKwOrMmZxGlQvGdDvGhFlEcUlTtDpPfOuRaNfJoCqDkUmCbTfCnGjWtTjObIfBiUgMoFeQsKfFiOsLnZj");
        contactPerson.setEmail("WsScArVjUkRtRvOfHkYx");
        contactPerson.setName("SyFqEkMmIbQfPrVvNeXgOnFjVlToBzNgYnVwFyJrKjRhMbBkYi");
        contactPerson.setWorkPhoneNo("VjVmEsGxTuLw");

        BindingResult errors = new DataBinder(contactPerson).getBindingResult();
        form.onSubmit(contactPerson, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/contactPersonform");
        request.addParameter("delete", "");
        contactPerson = new ContactPerson();
        contactPerson.setId(-2L);

        BindingResult errors = new DataBinder(contactPerson).getBindingResult();
        form.onSubmit(contactPerson, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
