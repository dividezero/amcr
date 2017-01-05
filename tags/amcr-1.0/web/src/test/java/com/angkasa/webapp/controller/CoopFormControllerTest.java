package com.angkasa.webapp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.angkasa.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.angkasa.model.Coop;

public class CoopFormControllerTest extends BaseControllerTestCase {
	@Autowired
	private CoopFormController form;
	private Coop coop;
	private MockHttpServletRequest request;

	@Test
	public void testEdit() throws Exception {
		log.debug("testing edit...");
		request = newGet("/coopform");
		request.addParameter("id", "-1");

		ModelAndView model = form.showForm(request);
		coop = (Coop) model.getModel().get("coop");
		assertNotNull(coop);
	}

	@Test
	public void testSave() throws Exception {
		request = newGet("/coopform");
		request.addParameter("id", "-1");

		ModelAndView model = form.showForm(request);
		coop = (Coop) model.getModel().get("coop");
		assertNotNull(coop);

		request = newPost("/coopform");

		model = form.showForm(request);
		coop = (Coop) model.getModel().get("coop");
		// update required fields
		coop.setCoopCode("DrX");
		coop.setName("testname");
		coop.setPhoneNo("109831902");
		coop.setEmail("test@printistechnologies.com");
        coop.setPreloaded(true);
		coop.setCoopBusinessTypeId(new Long(-1));

        User user = new User();
        user.setUsername("testuser2");
        user.setEmail("test2@printistechnologies.com");
        user.setFirstName("firstname");
        user.setLastName("lastName");
        user.setPasswordHint("passhint");
        user.setPassword("testuser2");
        coop.setUser(user);


		BindingResult errors = new DataBinder(coop).getBindingResult();
		form.onSubmit(coop, errors, request, new MockHttpServletResponse());
		assertFalse(errors.hasErrors());
		assertNotNull(request.getSession().getAttribute("successMessages"));
	}

	@Test
	public void testRemove() throws Exception {
		request = newPost("/coopform");
		request.addParameter("delete", "");
		coop = new Coop();
		coop.setId(-2L);

		BindingResult errors = new DataBinder(coop).getBindingResult();
		form.onSubmit(coop, errors, request, new MockHttpServletResponse());

		assertNotNull(request.getSession().getAttribute("successMessages"));
	}
}
