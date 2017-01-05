package com.angkasa.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.angkasa.Constants;
import com.angkasa.model.*;
import com.angkasa.service.*;
import com.angkasa.util.PropsUtil;
import com.angkasa.util.StringUtil;
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

@Controller
@RequestMapping("/coopform*")
public class CoopFormController extends BaseFormController {
    private CoopManager coopManager = null;
    @Autowired
    private ContactPersonManager contactPersonManager;

    @Autowired
    private CoopMemberManager coopMemberManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private PropsUtil propsUtil;

    @Autowired
    private CoopBusinessTypeManager coopBusinessTypeManager;

    @ModelAttribute("typeFlagList")
    public Map<String, String> getTypeFlagList() {
        return propsUtil.getCoopTypeFlagList();
    }

    @ModelAttribute("coopBusinessTypeList")
    public Map<String, String> getCoopBusinessTypeList() {
        List<CoopBusinessType> busTypeList = coopBusinessTypeManager.getAll();
        Map<String, String> result = new HashMap<>();
        result.put(null, "None");
        for (CoopBusinessType cbt : busTypeList) {
            result.put(cbt.getId().toString(), cbt.getName() + " (" + cbt.getCode() + ")");
        }
        return result;
    }

    @Autowired
    public void setCoopManager(CoopManager coopManager) {
        this.coopManager = coopManager;
    }

    public CoopFormController() {
        setCancelView("redirect:coops");
        setSuccessView("redirect:coops");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("coopform");
        String id = request.getParameter("id");
        Coop coop = new Coop();

        if (getCurrentUser() != null && getCurrentUser().getCoopId() != null) {
            coop = coopManager.get(getCurrentUser().getCoopId());

        } else if (getCurrentUser() != null && getCurrentUser().isCoopUser()) {
            coop = coopManager.getByUserId(getCurrentUser().getId());

        } else if (!StringUtils.isBlank(id)) {
            coop = coopManager.get(new Long(id));
        }

        if (coop.getId() != null) {
            if (coop.getUserId() != null) {
                coop.setUser(userManager.get(coop.getUserId()));
            } else {
                coop.setUser(new User());
            }

            model.addObject(coop);

            List<CoopMember> memberList = coopMemberManager.getByCoopId(coop.getId());
            model.addObject("memberList", memberList);

//            List<ContactPerson> contactPersonsList = contactPersonManager.getByCoopId(coop.getId());
//            model.addObject("contactPersonsList", contactPersonsList);

        } else {
            Coop newCoop = new Coop();
            newCoop.setMemberHexRunningNo(Coop.MEMBER_HEX_RUNNING_NO_MIN);
            newCoop.setUser(new User());
            model.addObject(newCoop);
        }

        return model;
    }

    @ModelAttribute
    @RequestMapping(value = "/deleteCoopMember", method = RequestMethod.GET)
    protected ModelAndView deleteCoopMember(HttpServletRequest request) throws Exception {
        String coopMemberId = request.getParameter("coopMemberId");
        coopMemberManager.remove(new Long(coopMemberId));
        Locale locale = request.getLocale();
        saveMessage(request, getText("coopMember.deleted", locale));
        return showForm(request);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Coop coop, BindingResult errors, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(coop, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "coopform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (coop.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            coopManager.remove(coop.getId());
            saveMessage(request, getText("coop.deleted", locale));
        } else {

            boolean createUser = true;
            User user = coop.getUser();
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
                    if (userManager.getByEmail(coop.getEmail()) != null) {
                        createUser = false;
                        saveError(request, "Email already exists in the system.");
                    }
                }


                if (createUser == false) {
                    return "coopform";
                }
            }


            if (createUser && user != null) {


                final Integer originalVersion = user.getVersion();
                try {
                    coop = coopManager.saveWithUser(coop);
                    log.debug("coop saved");
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

                    return "coopform";
                }
            }

            String resetPasswordUrl = "";
            try {
                if (isNew) {
                    user = userManager.getUsersByCoop(coop.getId());
                    resetPasswordUrl = getUserManager().buildRecoveryPasswordUrl(user,
                            UpdatePasswordController.RECOVERY_PASSWORD_TEMPLATE);
                    sendUserMessage(user, getText("newuser.coop.email.message", coop.getName(), locale),
                            RequestUtil.getAppURL(request) + resetPasswordUrl);
                }

            } catch (final MailException me) {
                saveError(request, me.getCause().getLocalizedMessage());
            }

            //url print
            if (isNew && propsUtil.isShowPasswordUrl()) {
                String url = RequestUtil.getAppURL(request) + resetPasswordUrl;
                StringUtils.replace(url, "&", "&amp;");
                saveMessage(request, "Please replace & escape string : " + url);
            }

            String key = (isNew) ? "coop.added" : "coop.updated";
            saveMessage(request, getText(key, locale) + " (" + coop.getAmcrCode() + ")");

            if (!isNew) {
                success = "coopform";
            }
        }

        return success;
    }
}
