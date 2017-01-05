package com.angkasa.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.angkasa.model.ContactPerson;
import com.angkasa.service.ContactPersonManager;
import com.angkasa.service.CoopManager;

@Controller
@RequestMapping("/contactPersonform*")
public class ContactPersonFormController extends BaseFormController {
	private ContactPersonManager contactPersonManager = null;
	@Autowired
	private CoopManager coopManager;

	@Autowired
	public void setContactPersonManager(ContactPersonManager contactPersonManager) {
		this.contactPersonManager = contactPersonManager;
	}

	public ContactPersonFormController() {
		setCancelView("redirect:contactPersons");
		setSuccessView("redirect:contactPersons");
	}

	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET)
	protected ContactPerson showForm(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");

		if (StringUtils.isNotBlank(id)) {
			return contactPersonManager.get(new Long(id));
		}

		ContactPerson newContactPerson = new ContactPerson();
		String coopId = request.getParameter("coopId");
		if (StringUtils.isNotBlank(coopId)) {
			newContactPerson.setCoopId(new Long(coopId));
		}

		return newContactPerson;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(ContactPerson contactPerson, BindingResult errors, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (contactPerson.getCoopId() != null) {
			setSuccessView("redirect:coopform?id=" + contactPerson.getCoopId());
			setCancelView("redirect:coopform?id=" + contactPerson.getCoopId());
		}

		if (request.getParameter("cancel") != null) {
			if (contactPerson.getId() != null && contactPerson.getCoopId() != null) {
				return "redirect:/coopform?id=" + contactPerson.getCoopId();
			}
			return getCancelView();
		}

		if (validator != null) { // validator is null during testing
			validator.validate(contactPerson, errors);

			if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
				return "contactPersonform";
			}
		}

		log.debug("entering 'onSubmit' method...");

		boolean isNew = (contactPerson.getId() == null);
		String success = getSuccessView();
		Locale locale = request.getLocale();

		if (request.getParameter("delete") != null) {
			contactPersonManager.remove(contactPerson.getId());
			saveMessage(request, getText("contactPerson.deleted", locale));
		} else {
			contactPersonManager.save(contactPerson);
			String key = (isNew) ? "contactPerson.added" : "contactPerson.updated";
			saveMessage(request, getText(key, locale));

			if (!isNew) {
				success = "redirect:/coopform?id=" + contactPerson.getCoopId();
			}
		}

		return success;
	}
}
