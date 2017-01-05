package com.angkasa.webapp.controller;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.angkasa.Constants;
import com.angkasa.model.Member;
import com.angkasa.model.User;
import com.angkasa.service.MemberManager;
import com.angkasa.service.MemberNotFoundException;
import com.angkasa.service.RoleManager;
import com.angkasa.service.UserExistsException;
import com.angkasa.util.PropsUtil;
import com.angkasa.util.StringUtil;
import com.angkasa.webapp.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

/**
 * Controller to signup new users.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/signup*")
public class SignupController extends BaseFormController {
    private RoleManager roleManager;

    @Autowired
    public void setRoleManager(final RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @Autowired
    private MemberManager memberManager;

    @Autowired
    private PropsUtil propsUtil;

    public SignupController() {
        setCancelView("redirect:login");
        setSuccessView("redirect:home");
    }


    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(HttpServletRequest request) {
        User user = new User();
        ModelAndView model = new ModelAndView("signup");
        model.addObject(user);
        return model;
//
//        String icNumber = request.getParameter("icNumber");
//        if (StringUtils.isBlank(icNumber)) {
//
//            ModelAndView model = new ModelAndView("signupIc");
//            log.debug("search ic page");
//            model.addObject(user);
//            return model;
//        }
//        if (!StringUtil.isNumeric(icNumber)) {
//            ModelAndView model = new ModelAndView("signupIc");
//            saveError(request, "Wrong ic number format");
//            user.setIcNumber(icNumber);
//            model.addObject(user);
//            return model;
//        }
//
//
//        ModelAndView model = new ModelAndView("signupMember");
//        Member member = memberManager.getByIcNumber(icNumber);
//        if(member!=null){
//            saveMessage(request, "Looks like we found a registered member according to your IC number!");
//            user.setMember(member);
//        }else{
//            user.setMember(new Member());
//        }
//
//        user.setIcNumber(icNumber);
//        model.addObject(user);
//
//        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(final User user, final BindingResult errors, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
//            validator.validate(user, errors);

//            if (StringUtils.isBlank(user.getPassword())) {
//                errors.rejectValue("password", "errors.required", new Object[]{getText("user.password", request.getLocale())},
//                        "Password is a required field.");
//            }

            if (errors.hasErrors()) {
                return "signup";
            }
        }

        final Locale locale = request.getLocale();

        user.setEnabled(true);
        user.setUsername(user.getIcNumber());

        if (getUserManager().getByUsername(user.getUsername()) != null) {
            errors.rejectValue("icNumber", "errors.existing.icNumber",
                    new Object[]{user.getUsername()}, "duplicate user");
        }
        if (getUserManager().getByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "errors.existing.email",
                    new Object[]{user.getEmail()}, "duplicate user");
        }

        // Set the default user role on this new user
        user.addRole(roleManager.getRole(Constants.USER_ROLE));
        user.addRole(roleManager.getRole(Constants.USER_ROLE_MEMBER));

        // unencrypted users password to log in user automatically
        user.setPassword(UUID.randomUUID().toString());
        final String password = user.getPassword();

        try {

            this.getUserManager().saveUser(user, false, false);
        } catch (final AccessDeniedException ade) {
            // thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
            log.warn(ade.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
//        catch (final MemberNotFoundException e) {
//            errors.rejectValue("icNumber", "errors.member.not.found",
//                    new Object[]{user.getIcNumber()}, "member not found");
//
//            return "signup";
//
//        }
        catch (final UserExistsException e) {
            errors.rejectValue("username", "errors.existing.user",
                    new Object[]{user.getUsername(), user.getEmail()}, "duplicate user");

            return "signup";
        }

        saveMessage(request, getText("user.registered", user.getUsername(), locale));
        request.getSession().setAttribute(Constants.REGISTERED, Boolean.TRUE);

        message.setSubject(getText("signup.email.subject", locale));

        String resetPasswordUrl = "";
        try {
            resetPasswordUrl = getUserManager().buildRecoveryPasswordUrl(user,
                    UpdatePasswordController.RECOVERY_PASSWORD_TEMPLATE);
            sendUserMessage(user, getText("newuser.email.signup.message", user.getFullName(), locale),
                    RequestUtil.getAppURL(request) + resetPasswordUrl);
            log.debug(resetPasswordUrl);
        } catch (final MailException me) {
            saveError(request, me.getCause().getLocalizedMessage());
        }

        // log user in automatically
//        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                user.getUsername(), password, user.getAuthorities());
//        auth.setDetails(user);
//        SecurityContextHolder.getContext().setAuthentication(auth);

        // Send user an e-mail
        if (log.isDebugEnabled()) {
            log.debug("Sending user '" + user.getUsername() + "' an account information e-mail");
        }

        // Send an account information e-mail
        message.setSubject(getText("signup.email.subject", locale));

        //url print
        if (org.apache.commons.lang.StringUtils.isNotBlank(resetPasswordUrl) && propsUtil.isShowPasswordUrl()) {
            String url = RequestUtil.getAppURL(request) + resetPasswordUrl;
            org.apache.commons.lang.StringUtils.replace(url, "&", "&amp;");
            saveMessage(request, "Please replace & escape string : " + url);
        }


        try {
            sendUserMessage(user, getText("signup.email.message", locale), RequestUtil.getAppURL(request));
        } catch (final MailException me) {
            saveError(request, me.getMostSpecificCause().getMessage());
        }

        return getSuccessView();
    }
}
