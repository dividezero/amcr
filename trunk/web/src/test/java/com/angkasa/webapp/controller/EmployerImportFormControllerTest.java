package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.EmployerImport;
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

public class EmployerImportFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private EmployerImportFormController form;
    private EmployerImport employerImport;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/employerImportform");
        request.addParameter("id", "-1");

        ModelAndView model = form.showForm(request);
        employerImport = (EmployerImport) model.getModel().get("employerImport");
        assertNotNull(employerImport);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/employerImportform");
        request.addParameter("id", "-1");

        ModelAndView model = form.showForm(request);
        employerImport = (EmployerImport) model.getModel().get("employerImport");
        assertNotNull(employerImport);

        request = newPost("/employerImportform");

        model = form.showForm(request);
        employerImport = (EmployerImport) model.getModel().get("employerImport");
        // update required fields
        employerImport.setDocumentId("-1");
        employerImport.setName("Test");

        BindingResult errors = new DataBinder(employerImport).getBindingResult();
        form.onSubmit(employerImport, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/employerImportform");
        request.addParameter("delete", "");
        employerImport = new EmployerImport();
        employerImport.setId(-2L);

        BindingResult errors = new DataBinder(employerImport).getBindingResult();
        form.onSubmit(employerImport, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
