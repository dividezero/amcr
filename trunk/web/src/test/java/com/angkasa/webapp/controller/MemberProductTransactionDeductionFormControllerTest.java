package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.MemberProductTransactionDeduction;
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

public class MemberProductTransactionDeductionFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberProductTransactionDeductionFormController form;
    private MemberProductTransactionDeduction memberProductTransactionDeduction;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/memberProductTransactionDeductionform");
        request.addParameter("id", "-1");

        memberProductTransactionDeduction = form.showForm(request);
        assertNotNull(memberProductTransactionDeduction);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/memberProductTransactionDeductionform");
        request.addParameter("id", "-1");

        memberProductTransactionDeduction = form.showForm(request);
        assertNotNull(memberProductTransactionDeduction);

        request = newPost("/memberProductTransactionDeductionform");

        memberProductTransactionDeduction = form.showForm(request);
        // update required fields
        memberProductTransactionDeduction.setDeductionDate(new java.util.Date());

        BindingResult errors = new DataBinder(memberProductTransactionDeduction).getBindingResult();
        form.onSubmit(memberProductTransactionDeduction, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/memberProductTransactionDeductionform");
        request.addParameter("delete", "");
        memberProductTransactionDeduction = new MemberProductTransactionDeduction();
        memberProductTransactionDeduction.setId(-2L);

        BindingResult errors = new DataBinder(memberProductTransactionDeduction).getBindingResult();
        form.onSubmit(memberProductTransactionDeduction, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
