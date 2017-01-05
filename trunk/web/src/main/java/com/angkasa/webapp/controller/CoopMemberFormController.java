package com.angkasa.webapp.controller;

import com.angkasa.model.Coop;
import com.angkasa.model.Member;
import com.angkasa.service.CoopManager;
import com.angkasa.service.MemberManager;
import org.apache.commons.lang.StringUtils;
import com.angkasa.service.CoopMemberManager;
import com.angkasa.model.CoopMember;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/coopMemberform*")
public class CoopMemberFormController extends BaseFormController {
    private CoopMemberManager coopMemberManager = null;

    @Autowired
    private MemberManager memberManager;

    @Autowired
    private CoopManager coopManager;

    @Autowired
    public void setCoopMemberManager(CoopMemberManager coopMemberManager) {
        this.coopMemberManager = coopMemberManager;
    }

    public CoopMemberFormController() {
        setCancelView("redirect:coopMembers");
        setSuccessView("redirect:coopMembers");
    }

    @ModelAttribute("coopList")
    public Map<Long, String> getCoopList() {
        List<Coop> coopList = coopManager.getAll();
        Map<Long, String> coopMap = new HashMap();
        for (Coop coop : coopList) {
            coopMap.put(coop.getId(), coop.getName() + " (" + coop.getCoopCode() + ")");
        }

        return coopMap;
    }

    @ModelAttribute("memberList")
    public Map<Long, String> getMemberList() {
        List<Member> memberList = memberManager.getAll();
        Map<Long, String> memberMap = new HashMap();
        for (Member member : memberList) {
            memberMap.put(member.getId(), member.getName() + " (" + member.getIcNumber() + ")");
        }

        return memberMap;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected CoopMember showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");
        String memberId = request.getParameter("memberId");
        String coopId = request.getParameter("coopId");

        if (!StringUtils.isBlank(id)) {
            return coopMemberManager.get(new Long(id));
        }

        CoopMember coopMember = new CoopMember();
        if (!StringUtils.isBlank(memberId)) {
            coopMember.setMemberId(new Long(memberId));
        }
        if (!StringUtils.isBlank(coopId)) {
            coopMember.setCoopId(new Long(coopId));
        }
        if (getCurrentUser() != null && getCurrentUser().isCoopUser()) {
            Coop coop = coopManager.getByUserId(getCurrentUser().getId());
            coopMember.setCoopId(coop.getId());
        }

        return coopMember;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(CoopMember coopMember, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {

        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(coopMember, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "coopMemberform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (coopMember.getId() == null);
        String success = "coopMemberform";
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            coopMemberManager.remove(coopMember.getId());
            saveMessage(request, getText("coopMember.deleted", locale));
        } else {
            if (StringUtils.isBlank(coopMember.getMemberHexNo())) {
                coopMemberManager.saveWithHexNo(coopMember);
            } else {
                coopMemberManager.save(coopMember);
            }

            String key = (isNew) ? "coopMember.added" : "coopMember.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "coopMemberform";
            } else {
                success = "redirect:/memberform?id=" + coopMember.getMemberId();
            }
        }

        log.debug(success);
        return success;
    }
}
