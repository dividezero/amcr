package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.MemberProductTransactionDeductionManager;
import com.angkasa.model.MemberProductTransactionDeduction;
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
@RequestMapping("/memberProductTransactionDeductionform*")
public class MemberProductTransactionDeductionFormController extends BaseFormController {
    private MemberProductTransactionDeductionManager memberProductTransactionDeductionManager = null;

    @Autowired
    public void setMemberProductTransactionDeductionManager(MemberProductTransactionDeductionManager memberProductTransactionDeductionManager) {
        this.memberProductTransactionDeductionManager = memberProductTransactionDeductionManager;
    }

    public MemberProductTransactionDeductionFormController() {
        setCancelView("redirect:memberProductTransactionDeductions");
        setSuccessView("redirect:memberProductTransactionDeductions");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected MemberProductTransactionDeduction showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return memberProductTransactionDeductionManager.get(new Long(id));
        }

        return new MemberProductTransactionDeduction();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(MemberProductTransactionDeduction memberProductTransactionDeduction, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(memberProductTransactionDeduction, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "memberProductTransactionDeductionform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (memberProductTransactionDeduction.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            memberProductTransactionDeductionManager.remove(memberProductTransactionDeduction.getId());
            saveMessage(request, getText("memberProductTransactionDeduction.deleted", locale));
        } else {
            memberProductTransactionDeductionManager.save(memberProductTransactionDeduction);
            String key = (isNew) ? "memberProductTransactionDeduction.added" : "memberProductTransactionDeduction.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:memberProductTransactionDeductionform?id=" + memberProductTransactionDeduction.getId();
            }
        }

        return success;
    }
}
