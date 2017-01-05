package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.CoopMember;
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

public class CoopMemberFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private CoopMemberFormController form;
    private CoopMember coopMember;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/coopMemberform");
        request.addParameter("id", "-1");

        coopMember = form.showForm(request);
        assertNotNull(coopMember);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/coopMemberform");
        request.addParameter("id", "-1");

        coopMember = form.showForm(request);
        assertNotNull(coopMember);

        request = newPost("/coopMemberform");

        coopMember = form.showForm(request);
        // update required fields
        coopMember.setMemberId(new Long(-1));
        coopMember.setIcNumber("23453");
        coopMember.setName("Name Name");
        coopMember.setCoopId(new Long(-1));

        BindingResult errors = new DataBinder(coopMember).getBindingResult();
        form.onSubmit(coopMember, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/coopMemberform");
        request.addParameter("delete", "");
        coopMember = new CoopMember();
        coopMember.setId(-2L);

        BindingResult errors = new DataBinder(coopMember).getBindingResult();
        form.onSubmit(coopMember, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
