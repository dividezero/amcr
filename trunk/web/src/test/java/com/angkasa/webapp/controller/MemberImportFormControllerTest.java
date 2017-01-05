package com.angkasa.webapp.controller;

import com.angkasa.model.EmployerImport;
import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.MemberImport;
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

public class MemberImportFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private MemberImportFormController form;
    private MemberImport memberImport;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/memberImportform");
        request.addParameter("id", "-1");

        ModelAndView model = form.showForm(request);
        memberImport = (MemberImport) model.getModel().get("memberImport");
        assertNotNull(memberImport);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/memberImportform");
        request.addParameter("id", "-1");

        ModelAndView model = form.showForm(request);
        memberImport = (MemberImport) model.getModel().get("memberImport");
        assertNotNull(memberImport);

        request = newPost("/memberImportform");

        model = form.showForm(request);
        memberImport = (MemberImport) model.getModel().get("memberImport");
        // update required fields
        memberImport.setName("ReAfRpFoLiWnMbVqIvElEjJxYhKwSxTyPzZySsUdJrWcKnWjNkVgBxTiRyUpSoYjCrSlDmSiEpGvHiCkTeMeJpKzGtUuXfWcIfYtRvIkYyFcXlVfHvTgJhQmMyOaElJlAtIpJrPrAeAwTbPeTeNrSrLjMvKtJoMdMmAmOyUeYoXlHtLvHaTaQsKoSdYnBcRaAvDtYwMhZkFwVwIvCxBhFuPiVjNgIxJaTcVzElSqVoCjNdBxIiVpZkDgGmRwYgE");

        BindingResult errors = new DataBinder(memberImport).getBindingResult();
        form.onSubmit(memberImport, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/memberImportform");
        request.addParameter("delete", "");
        memberImport = new MemberImport();
        memberImport.setId(-2L);

        BindingResult errors = new DataBinder(memberImport).getBindingResult();
        form.onSubmit(memberImport, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
