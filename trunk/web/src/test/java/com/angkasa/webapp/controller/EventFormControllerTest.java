package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.Event;
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

public class EventFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private EventFormController form;
    private Event event;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/eventform");
        request.addParameter("id", "-1");


        ModelAndView model = form.showForm(request);
        event = (Event) model.getModel().get("event");
        assertNotNull(event);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/eventform");
        request.addParameter("id", "-1");

        ModelAndView model = form.showForm(request);
        event = (Event) model.getModel().get("event");
        assertNotNull(event);

        request = newPost("/eventform");

        model = form.showForm(request);
        event = (Event) model.getModel().get("event");
        // update required fields
        event.setEventCode("TsNuThOnNfSaJyKyJwTiIkIiXqLqCpCfUcFpBuOqFfTpNvAyDz");
        event.setName("EsAjPeHiHzHxWzHbXvQnIgMoGzJpGpDrPrFnBuHlFoOkFkZfWiMyJkIeRrVkJmAtVkGxDmQpNnQcIyQcQxDtWpZhDfGtRtWjUkOhHrStOmMaIaUvVuUmSoSvGpXrMmAeRiDwGrNnUvOuPnYhMmBgOkZeLoOfLiIiDwAfHdVaGgVaYpQjIyPxKxStJdIxAlMdYaNpTlOz");
        event.setStartTime(new java.util.Date());

        BindingResult errors = new DataBinder(event).getBindingResult();
        form.onSubmit(event, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/eventform");
        request.addParameter("delete", "");
        event = new Event();
        event.setId(-2L);

        BindingResult errors = new DataBinder(event).getBindingResult();
        form.onSubmit(event, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
