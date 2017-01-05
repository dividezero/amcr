package com.angkasa.webapp.controller;

import com.angkasa.Constants;
import com.angkasa.model.Coop;
import com.angkasa.model.Member;
import com.angkasa.service.CoopManager;
import com.angkasa.service.MemberManager;
import com.angkasa.util.PropsUtil;
import org.apache.commons.lang.StringUtils;
import com.angkasa.service.MemberImportManager;
import com.angkasa.model.MemberImport;
import com.angkasa.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/memberImportform*")
public class MemberImportFormController extends BaseFormController {
    private MemberImportManager memberImportManager = null;

    @Autowired
    public void setMemberImportManager(MemberImportManager memberImportManager) {
        this.memberImportManager = memberImportManager;
    }

    @Autowired
    CoopManager coopManager;

    @Autowired
    MemberManager memberManager;

    @Autowired
    PropsUtil propsUtil;

    public MemberImportFormController() {
        setCancelView("redirect:memberImports");
        setSuccessView("redirect:memberImports");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request)
            throws Exception {

        ModelAndView model = new ModelAndView("memberImportform");
        String id = request.getParameter("id");
        String viewStatus = request.getParameter("viewStatus");
        model.addObject("viewStatus", viewStatus);

        if (!StringUtils.isBlank(id)) {
            MemberImport memberImport = memberImportManager.get(new Long(id));

            model.addObject("memberImport", memberImport);
            List<Member> memList = new ArrayList<>();
            if (StringUtils.isNotBlank(viewStatus)) {
                memList = memberImport.getMemberListByStatus(viewStatus);
            } else {
                memList = memberImport.getMemberList();
            }
            model.addObject("memList", memList);
            return model;
        }

        model.addObject("memberImport", new MemberImport());
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(MemberImport memberImport, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(memberImport, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "memberImports";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (memberImport.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            memberImportManager.remove(memberImport.getId());
            saveMessage(request, getText("memberImport.deleted", locale));
        } else {
            //TODO remove this and replace with property in memberImport
            boolean useCoopCode = propsUtil.isUseCoopCodeInMemberImport();

            if (memberImport.getId() == null) {
                if (getCurrentUser() != null && getCurrentUser().isCoopUser()) {
                    Coop coop = coopManager.getByUserId(getCurrentUser().getId());
                    memberImport.setCoopId(coop.getId());
                }
                memberImportManager.saveWithFile(memberImport, useCoopCode);

            } else {
                List<Member> memList = memberImport.getMemberList();
                if (request.getParameter("validateAll") != null) {
                    memList = memberManager.validateMemberImportList(memList, useCoopCode);
                }
                if (request.getParameter("saveAllToMembers") != null) {
                    memList = memberManager.processMemberImport(memList, useCoopCode);
                }
                memberImport.setMemberList(memList);
                memberImportManager.save(memberImport);
            }

            String key = (isNew) ? "memberImport.added" : "memberImport.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:memberImportform?id=" + memberImport.getId();
            }
        }

        return success;
    }

}
