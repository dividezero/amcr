package com.angkasa.webapp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.angkasa.Constants;
import com.angkasa.model.Coop;
import com.angkasa.model.Member;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class MemberFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberFormController form;
    private Member member;
    private MockHttpServletRequest request;
    private RedirectAttributes attributes;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/memberform");
        request.addParameter("id", "-1");

        ModelAndView model = form.showForm(request);
        member = (Member) model.getModel().get("member");
        assertNotNull(member);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/memberform");

        ModelAndView model = form.showForm(request);
        member = (Member) model.getModel().get("member");
        assertNotNull(member);

        request = newPost("/memberform");

        model = form.showForm(request);
        member = (Member) model.getModel().get("member");
        // update required fields
        member.setIcNumber("0294595003");
        member.setMembershipNo("90128390123");
        member.setEmail("test@printistechnologies.com");
        member.setGender(Constants.GENDER_FEMALE);
        member.setName("DxQqPcRkFwIeSiSrVrZvQcLlPoCeKbTjNoByZvIvQgCfHkMzYu");
        member.setPhoneNo("238947923");

        BindingResult errors = new DataBinder(member).getBindingResult();
        form.onSubmit(member, errors, request, new MockHttpServletResponse(), attributes);
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/memberform");
        request.addParameter("delete", "");
        member = new Member();
        member.setId(-2L);

        BindingResult errors = new DataBinder(member).getBindingResult();
        form.onSubmit(member, errors, request, new MockHttpServletResponse(), attributes);

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
