package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.SystemSetting;
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

public class SystemSettingFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private SystemSettingFormController form;
    private SystemSetting systemSetting;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/systemSettingform");
        request.addParameter("id", "-1");

        systemSetting = form.showForm(request);
        assertNotNull(systemSetting);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/systemSettingform");
        request.addParameter("id", "-1");

        systemSetting = form.showForm(request);
        assertNotNull(systemSetting);

        request = newPost("/systemSettingform");

        systemSetting = form.showForm(request);
        // update required fields
        systemSetting.setPropertyName("SyZtPmRhQtLhUvAlQqRpUqWcKhNsOwCjWsVyEbWgNkFdFnRqEqWeEmXxJaNxTdPuIpKkDqAqQbJgDaOaUiVqPuNsHuMnJbCvRoIaTpBoMxTqHuXmDhCqJrPaGwZlUjLzEuKnOyZpAyAhHxTzEjGyDfQqXnJjBtZcEySaYfNlFhDoRaDcKgWyOsNiHlJbFbXwVyOvDpZqNyXbQlLuTfQtMrOpFrRqXoBbUpAhJkLlDrDqLuHoNzCbBnSxXjJnEmP");

        BindingResult errors = new DataBinder(systemSetting).getBindingResult();
        form.onSubmit(systemSetting, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/systemSettingform");
        request.addParameter("delete", "");
        systemSetting = new SystemSetting();
        systemSetting.setId(-2L);

        BindingResult errors = new DataBinder(systemSetting).getBindingResult();
        form.onSubmit(systemSetting, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
