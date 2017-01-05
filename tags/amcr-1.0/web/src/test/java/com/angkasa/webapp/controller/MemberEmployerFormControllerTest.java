package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.MemberEmployer;
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

public class MemberEmployerFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberEmployerFormController form;
    private MemberEmployer memberEmployer;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/memberEmployerform");
        request.addParameter("id", "-1");

        memberEmployer = form.showForm(request);
        assertNotNull(memberEmployer);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/memberEmployerform");
        request.addParameter("id", "-1");

        memberEmployer = form.showForm(request);
        assertNotNull(memberEmployer);

        request = newPost("/memberEmployerform");

        memberEmployer = form.showForm(request);
        // update required fields

        BindingResult errors = new DataBinder(memberEmployer).getBindingResult();
        form.onSubmit(memberEmployer, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/memberEmployerform");
        request.addParameter("delete", "");
        memberEmployer = new MemberEmployer();
        memberEmployer.setId(-2L);

        BindingResult errors = new DataBinder(memberEmployer).getBindingResult();
        form.onSubmit(memberEmployer, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
