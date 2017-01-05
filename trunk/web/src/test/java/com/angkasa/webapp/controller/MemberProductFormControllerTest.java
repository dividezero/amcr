package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.MemberProduct;
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

public class MemberProductFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberProductFormController form;
    private MemberProduct memberProduct;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/memberProductform");
        request.addParameter("id", "-1");

        memberProduct = form.showForm(request);
        assertNotNull(memberProduct);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/memberProductform");
        request.addParameter("id", "-1");

        memberProduct = form.showForm(request);
        assertNotNull(memberProduct);

        request = newPost("/memberProductform");

        memberProduct = form.showForm(request);
        // update required fields
        memberProduct.setCommencementDate(new java.util.Date());

        BindingResult errors = new DataBinder(memberProduct).getBindingResult();
        form.onSubmit(memberProduct, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/memberProductform");
        request.addParameter("delete", "");
        memberProduct = new MemberProduct();
        memberProduct.setId(-2L);

        BindingResult errors = new DataBinder(memberProduct).getBindingResult();
        form.onSubmit(memberProduct, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
