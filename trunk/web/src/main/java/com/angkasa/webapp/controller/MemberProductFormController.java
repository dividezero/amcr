package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.MemberProductManager;
import com.angkasa.model.MemberProduct;
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
@RequestMapping("/memberProductform*")
public class MemberProductFormController extends BaseFormController {
    private MemberProductManager memberProductManager = null;

    @Autowired
    public void setMemberProductManager(MemberProductManager memberProductManager) {
        this.memberProductManager = memberProductManager;
    }

    public MemberProductFormController() {
        setCancelView("redirect:memberProducts");
        setSuccessView("redirect:memberProducts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected MemberProduct showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return memberProductManager.get(new Long(id));
        }

        return new MemberProduct();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(MemberProduct memberProduct, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(memberProduct, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "memberProductform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (memberProduct.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            memberProductManager.remove(memberProduct.getId());
            saveMessage(request, getText("memberProduct.deleted", locale));
        } else {
            memberProductManager.save(memberProduct);
            String key = (isNew) ? "memberProduct.added" : "memberProduct.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:memberProductform?id=" + memberProduct.getId();
            }
        }

        return success;
    }
}
