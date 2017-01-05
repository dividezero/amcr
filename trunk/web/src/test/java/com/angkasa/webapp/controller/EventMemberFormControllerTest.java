package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.EventMember;
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

public class EventMemberFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private EventMemberFormController form;
    private EventMember eventMember;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/eventMemberform");
        request.addParameter("id", "-1");

        eventMember = form.showForm(request);
        assertNotNull(eventMember);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/eventMemberform");
        request.addParameter("id", "-1");

        eventMember = form.showForm(request);
        assertNotNull(eventMember);

        request = newPost("/eventMemberform");

        eventMember = form.showForm(request);
        // update required fields
        eventMember.setIcNumber("EcDxPlNzIdUdOjQhItMq");
        eventMember.setName("EsDlVpLyLgCiEbRyKsEeKnOnPnYtAfEnAfXuAtHxUeTaZhEnCdGxWgPjFoPyFdIoNeIqEdTcXrQwKcDpHbLhCtVjKlAxHlYeTtWpGjExDtRaEuVjGtSpKaBwZnIkZqAjTtIoSlRtVtJjXzArKkSeCrXeDfCqRwLrFxTxOpDkHkQfOeTdJnBqYaVxJaIsUaEzKhUoBtJl");
        eventMember.setEventId(new Long(-1));

        BindingResult errors = new DataBinder(eventMember).getBindingResult();
        form.onSubmit(eventMember, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/eventMemberform");
        request.addParameter("delete", "");
        eventMember = new EventMember();
        eventMember.setId(-2L);

        BindingResult errors = new DataBinder(eventMember).getBindingResult();
        form.onSubmit(eventMember, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
