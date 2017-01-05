package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.MemberEmployerManager;
import com.angkasa.model.MemberEmployer;
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
@RequestMapping("/memberEmployerform*")
public class MemberEmployerFormController extends BaseFormController {
    private MemberEmployerManager memberEmployerManager = null;

    @Autowired
    public void setMemberEmployerManager(MemberEmployerManager memberEmployerManager) {
        this.memberEmployerManager = memberEmployerManager;
    }

    public MemberEmployerFormController() {
        setCancelView("redirect:memberEmployers");
        setSuccessView("redirect:memberEmployers");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected MemberEmployer showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return memberEmployerManager.get(new Long(id));
        }

        return new MemberEmployer();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(MemberEmployer memberEmployer, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(memberEmployer, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "memberEmployerform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (memberEmployer.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            memberEmployerManager.remove(memberEmployer.getId());
            saveMessage(request, getText("memberEmployer.deleted", locale));
        } else {
            memberEmployerManager.save(memberEmployer);
            String key = (isNew) ? "memberEmployer.added" : "memberEmployer.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:memberEmployerform?id=" + memberEmployer.getId();
            }
        }

        return success;
    }
}
