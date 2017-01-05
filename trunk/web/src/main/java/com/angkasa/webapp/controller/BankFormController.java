package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.BankManager;
import com.angkasa.model.Bank;
import com.angkasa.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/bankform*")
public class BankFormController extends BaseFormController {
    private BankManager bankManager = null;

    @Autowired
    public void setBankManager(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    public BankFormController() {
        setCancelView("redirect:banks");
        setSuccessView("redirect:banks");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Bank showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return bankManager.get(new Long(id));
        }

        return new Bank();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Bank bank, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(bank, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "bankform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (bank.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            bankManager.remove(bank.getId());
            saveMessage(request, getText("bank.deleted", locale));
        } else {
            bankManager.save(bank);
            String key = (isNew) ? "bank.added" : "bank.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:bankform?id=" + bank.getId();
            }
        }

        return success;
    }
}
