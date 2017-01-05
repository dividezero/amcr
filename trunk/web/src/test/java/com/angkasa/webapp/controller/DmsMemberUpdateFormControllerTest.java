package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.DmsMemberUpdate;
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

public class DmsMemberUpdateFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private DmsMemberUpdateFormController form;
    private DmsMemberUpdate dmsMemberUpdate;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/dmsMemberUpdateform");
        request.addParameter("id", "-1");

        dmsMemberUpdate = form.showForm(request);
        assertNotNull(dmsMemberUpdate);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/dmsMemberUpdateform");
        request.addParameter("id", "-1");

        dmsMemberUpdate = form.showForm(request);
        assertNotNull(dmsMemberUpdate);

        request = newPost("/dmsMemberUpdateform");

        dmsMemberUpdate = form.showForm(request);
        // update required fields
        dmsMemberUpdate.setIcNumber("UyFwZwKbRfVtVtZrJmOx");
        dmsMemberUpdate.setPhoneNum("IeEbIlUwLeOyQlUnJoNj");

        BindingResult errors = new DataBinder(dmsMemberUpdate).getBindingResult();
        form.onSubmit(dmsMemberUpdate, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/dmsMemberUpdateform");
        request.addParameter("delete", "");
        dmsMemberUpdate = new DmsMemberUpdate();
        dmsMemberUpdate.setId(-2L);

        BindingResult errors = new DataBinder(dmsMemberUpdate).getBindingResult();
        form.onSubmit(dmsMemberUpdate, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
