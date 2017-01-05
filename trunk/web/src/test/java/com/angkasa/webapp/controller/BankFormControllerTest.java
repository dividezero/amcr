package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.Bank;
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

public class BankFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private BankFormController form;
    private Bank bank;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/bankform");
        request.addParameter("id", "-1");

        bank = form.showForm(request);
        assertNotNull(bank);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/bankform");
        request.addParameter("id", "-1");

        bank = form.showForm(request);
        assertNotNull(bank);

        request = newPost("/bankform");

        bank = form.showForm(request);
        // update required fields
        bank.setCode("WpSnBfNaLoMgNyWxPmDvSvUvOtQnUaGuLnWaOfLkEeUlMlSfWb");
        bank.setName("JuBqIeSuLnZoXzOnNpJqLrXeVgHvQiGbNeOyWiFqFnYnYiQvFbWnSjNyEtUeAsUzQaVbMcDlWuDdIiGdZgYeXeJeGdRwAcWgMaIoCuPyIpTqGnMuZbIpYkPyNdGpRsWmEqRyIhMoUsHqFxWpXhOoBbBtErAcVsVfSgRsVtKnSlNtJmCnVjVpEwFbDuUpSwReBvLpWcMrUjSkYdGyJpDnRuLwEvCvQrJfJlPdVkEzDzYvHrLvBlXzDnOuFz");

        BindingResult errors = new DataBinder(bank).getBindingResult();
        form.onSubmit(bank, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/bankform");
        request.addParameter("delete", "");
        bank = new Bank();
        bank.setId(-2L);

        BindingResult errors = new DataBinder(bank).getBindingResult();
        form.onSubmit(bank, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
