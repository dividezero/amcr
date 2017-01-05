package com.angkasa.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.angkasa.model.*;
import com.angkasa.service.*;
import com.angkasa.util.PropsUtil;
import com.angkasa.webapp.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/memberform*")
public class MemberFormController extends BaseFormController {
    private MemberManager memberManager = null;
    @Autowired
    private CoopMemberManager coopMemberManager;

    @Autowired
    private BankManager bankManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private PropsUtil propsUtil;

    @Autowired
    private CoopManager coopManager;

    @Autowired
    public void setMemberManager(MemberManager memberManager) {
        this.memberManager = memberManager;
    }

    public MemberFormController() {
        setCancelView("redirect:members");
        setSuccessView("redirect:members");
    }

    @ModelAttribute("genderList")
    public Map<String, String> getGenderList() {
        Map<String, String> map = new HashMap<>();
        map.put("", null);
        map.putAll(propsUtil.getGenderList());
        return map;
    }

    @ModelAttribute("maritalStatusList")
    public Map<String, String> getMaritalStatusList() {
        Map<String, String> map = new HashMap<>();
        map.put("", null);
        map.putAll(propsUtil.getMaritalStatusList());
        return map;
    }

    @ModelAttribute("raceList")
    public Map<String, String> getRaceList() {
        Map<String, String> map = new HashMap<>();
        map.put("", null);
        map.putAll(propsUtil.getRaceList());
        return map;
    }

    @ModelAttribute("languageList")
    public Map<String, String> getLanguageList() {
        Map<String, String> map = new HashMap<>();
        map.put("", null);
        map.putAll(propsUtil.getLanguageList());
        return map;
    }

    @ModelAttribute("designationList")
    public Map<String, String> getDesignationList() {
        Map<String, String> map = new HashMap<>();
        map.put("", null);
        map.putAll(propsUtil.getDesignationList());
        return map;
    }

    @ModelAttribute("banksList")
    public Map<Long, String> getBankList() {
        List<Bank> bankList = bankManager.getAll();
        Map<Long, String> bankMap = new HashMap<>();
        bankMap.put(null, "N/A");
        for (Bank bank : bankList) {
            bankMap.put(bank.getId(), bank.getName() + " (" + bank.getCode() + ")");
        }
        return bankMap;
    }

    @ModelAttribute("typeFlagList")
    public Map<String, String> getTypeFlagList() {
        return propsUtil.getMemberTypeFlagList();
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("memberform");

        String id = request.getParameter("id");

        if (getCurrentUser() != null && getCurrentUser().isMemberUser()) {
            Member member = memberManager.getByUserId(getCurrentUser().getId());
            if (member == null || member.getId() == null) {
                log.debug("secondary");
                member = memberManager.get(getCurrentUser().getMemberId());
            }
            id = member.getId().toString();
        }


        if (!StringUtils.isBlank(id)) {

            Member member = memberManager.get(new Long(id));
            if (member.getUserId() != null) {
                member.setUser(userManager.get(member.getUserId()));
            }
            model.addObject("member", member);
            List<CoopMember> coopMemberList = coopMemberManager.getByMemberId(new Long(id));
            model.addObject("coopMemberList", coopMemberList);

            return model;

        }
//        else if (getCurrentUser() != null && getCurrentUser().isMemberUser()) {
//
//            Member member = memberManager.getByUserId(getCurrentUser().getId());
//            if (member == null || member.getId() == null) {
//                log.debug("secondary");
//                member = memberManager.get(getCurrentUser().getMemberId());
//            }
//            log.debug("member = " + member.toString());
//
//            if (member.getUserId() != null) {
//                member.setUser(userManager.get(member.getUserId()));
//            }
//            List<CoopMember> coopMemberList = coopMemberManager.getByMemberId(member.getId());
//            model.addObject("coopMemberList", coopMemberList);
//            model.addObject("member", member);
//
//            return model;
//        }

        // new member
        Member newMember = new Member();
        if (getCurrentUser() != null && getCurrentUser().isAdminUser()) {
            if (StringUtils.isNotBlank(request.getParameter("coopId"))) {
                newMember.setCoopId(new Long(request.getParameter("coopId")));
            }
        } else if (getCurrentUser() != null && getCurrentUser().isCoopUser()) {
            Coop coop = coopManager.getByUserId(getCurrentUser().getId());
            newMember.setCoopId(coop.getId());
        }
        model.addObject("member", newMember);
        return model;
    }

    @ModelAttribute
    @RequestMapping(value = "/deleteCoop", method = RequestMethod.GET)
    protected ModelAndView deleteCoopMember(HttpServletRequest request) throws Exception {
        String coopMemberId = request.getParameter("coopMemberId");
        coopMemberManager.remove(new Long(coopMemberId));
        Locale locale = request.getLocale();
        saveMessage(request, getText("coopMember.deleted", locale));
        return showForm(request);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Member member, BindingResult errors, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttrs) throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(member, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "memberform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (member.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            memberManager.remove(member.getId());
            saveMessage(request, getText("member.deleted", locale));
        } else {
            boolean createUser = true;
            User user = member.getUser();
            if (user != null) {

//                if (StringUtils.isBlank(user.getUsername())) {
//                    createUser = false;
//                    saveError(request, "Please check login username.");
//                }
//                if (StringUtils.isBlank(user.getEmail())) {
//                    createUser = false;
//                    saveError(request, "Please check login email.");
//                }
                if (user.getId() == null) {
//                    if (userManager.getByUsername(user.getUsername()) != null) {
//                        createUser = false;
//                        saveError(request, "Username already exists.");
//                    }
                    if (userManager.getByEmail(member.getEmail()) != null) {
                        createUser = false;
                        saveError(request, "Email already exists in the system.");
                    }
                }

                if (createUser == false) {
                    return "memberform";
                }
            }
            if (createUser && user != null) {

                final Integer originalVersion = user.getVersion();
                try {
                    if (member.getCoopId() != null) {
                        memberManager.saveWithUser(member, true);
                    } else {
                        memberManager.saveWithUser(member, false);
                    }

                } catch (final AccessDeniedException ade) {
                    // thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
                    log.warn(ade.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return null;
                } catch (final UserExistsException e) {
                    errors.rejectValue("user.username", "errors.existing.user",
                            new Object[]{user.getUsername(), user.getEmail()}, "duplicate user");

                    // reset the version # to what was passed in
                    user.setVersion(originalVersion);

                    return "memberform";
                }
            }

            String resetPasswordUrl = "";
            try {
                if (isNew) {
//                    user = userManager.getUsersByMember(member.getId());
                    if (user != null) {
                        resetPasswordUrl = getUserManager().buildRecoveryPasswordUrl(user,
                                UpdatePasswordController.RECOVERY_PASSWORD_TEMPLATE);
                        sendUserMessage(user, getText("newuser.member.email.message", member.getName(), locale),
                                RequestUtil.getAppURL(request) + resetPasswordUrl);
                    } else {
                        saveError(request, "Save error. User login fetch failed.");
                    }
                }

            } catch (final MailException me) {
                saveError(request, me.getCause().getLocalizedMessage());
            }

            //url print
            if (isNew && propsUtil.isShowPasswordUrl()) {
                String url = RequestUtil.getAppURL(request) + resetPasswordUrl;
                StringUtils.replace(url, "&", "&amp;");
                saveMessage(request, url);
            }

            String key = (isNew) ? "member.added" : "member.updated";
            saveMessage(request, getText(key, locale) + " (" + member.getAmcrCode() + ")");

            if (!isNew) {
                success = "memberform";
            }
        }

        return success;
    }

}
