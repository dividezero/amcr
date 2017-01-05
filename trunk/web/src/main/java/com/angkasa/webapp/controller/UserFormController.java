package com.angkasa.webapp.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.angkasa.model.*;
import com.angkasa.service.*;
import org.apache.commons.lang.StringUtils;
import com.angkasa.Constants;
import com.angkasa.webapp.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Implementation of <strong>SimpleFormController</strong> that interacts with
 * the {@link UserManager} to retrieve/persist values to the database.
 * <p/>
 * <p><a href="UserFormController.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/userform*")
public class UserFormController extends BaseFormController {

    @Autowired
    private CoopManager coopManager;

    @Autowired
    private MemberManager memberManager;

    private RoleManager roleManager;

    @Autowired
    public void setRoleManager(final RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public UserFormController() {
        setFailureView("userform");
        setCancelView("redirect:/home");
        setSuccessView("redirect:/admin/users");
    }

    @Override
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        super.initBinder(request, binder);
        binder.setDisallowedFields("password", "confirmPassword");
    }

    @ModelAttribute("rolesList")
    public List<LabelValue> getRolesList() {
        List<LabelValue> list = new ArrayList<LabelValue>();
        list.add(new LabelValue(Constants.USER_ROLE, Constants.USER_ROLE));
        list.add(new LabelValue(Constants.ADMIN_ROLE, Constants.ADMIN_ROLE));
        list.add(new LabelValue(Constants.USER_ROLE_DMS, Constants.USER_ROLE_DMS));
        list.add(new LabelValue(Constants.USER_ROLE_EVENT, Constants.USER_ROLE_EVENT));
        list.add(new LabelValue(Constants.USER_ROLE_ANGKASA, Constants.USER_ROLE_ANGKASA));
        return list;
    }

    @ModelAttribute("coopList")
    public Map<String, String> getCoopList() {
        List<Coop> coopList = coopManager.getAll();
        Map<String, String> coopMap = new HashMap();

        coopMap.put(null, "N/A");
        for (Coop coop : coopList) {
            coopMap.put(coop.getCoopCode(), coop.getName() + " (" + coop.getAmcrCode() + ")"+coop.getId());
        }

        return coopMap;
    }

    @ModelAttribute("memberList")
    public Map<String, String> getMemberList() {
        List<Member> list = memberManager.getAll();
        List<User> userlist = getUserManager().getAll();
        Map<String, String> memberMap = new HashMap();

        memberMap.put(null, "N/A");
        for (Member member : list) {
            boolean insert = true;
            for (User user : userlist) {
                if (user.getMemberId() == member.getId()) {
                    insert = false;
                    break;
                }
            }
            if (insert) {
                memberMap.put(member.getIcNumber(), member.getName() + " (" + member.getIcNumber() + ")"+member.getId());
            }
        }

        return memberMap;
    }

    /**
     * Load user object from db before web data binding in order to keep properties not populated from web post.
     *
     * @param request
     * @return
     */
    @ModelAttribute("user")
    protected User loadUser(final HttpServletRequest request) {
        final String userId = request.getParameter("id");
        if (isFormSubmission(request) && StringUtils.isNotBlank(userId)) {
            return getUserManager().getUser(userId);
        }
        return new User();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("user") final User user, final BindingResult errors, final HttpServletRequest request,
                           final HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            if (!StringUtils.equals(request.getParameter("from"), "list")) {
                return getCancelView();
            } else {
                return getSuccessView();
            }
        }

        if (validator != null) { // validator is null during testing
            validator.validate(user, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "userform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        final Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            getUserManager().removeUser(user.getId().toString());
            saveMessage(request, getText("user.deleted", user.getFullName(), locale));

            return getSuccessView();
        } else {

            boolean updateMember = false;
            boolean updateCoop = false;

            // only attempt to change roles if user is admin for other users,
            // showForm() method will handle populating
            if (request.isUserInRole(Constants.ADMIN_ROLE)) {
                final String[] userRoles = request.getParameterValues("userRoles");

                if (userRoles != null) {
                    user.getRoles().clear();

                    boolean error = false;
                    for (final String roleName : userRoles) {
                        user.addRole(roleManager.getRole(roleName));

                        // Attach member if member role is set
//                        if (StringUtils.equals(roleName, Constants.USER_ROLE_MEMBER)) {
//                            if (StringUtils.isNotBlank(user.getIcNumber())) {
//                                log.info("Updating member...");
//                                updateMember = true;
//                            } else {
//                                errors.rejectValue("icNumber", "errors.member.icNumber.empty", "member not found");
//                                error = true;
//                            }
//                        }
//                        if (StringUtils.equals(roleName, Constants.USER_ROLE_COOP)) {
//                            if (StringUtils.isNotBlank(user.getCoopCode())) {
//                                log.info("Updating coop...");
//                                updateCoop = true;
//                            } else {
//                                errors.rejectValue("coopCode", "errors.member.coopCode.empty", "coop code empty");
//                                error = true;
//                            }
//                        }
                    }
                    if (!user.isCoopUser()) {
                        user.setCoopCode(null);
                    }
                    if (!user.isMemberUser()) {
                        user.setIcNumber(null);
                    }

                    if (error) {
                        return getFailureView();
                    }


                }
            } else {
                // if user is not an admin then load roles from the database
                // (or any other user properties that should not be editable
                // by users without admin role)
                final User cleanUser = getUserManager().getUserByUsername(
                        request.getRemoteUser());
                user.setRoles(cleanUser.getRoles());
            }

            final Integer originalVersion = user.getVersion();

            // set a random password if user is added by admin
            if (originalVersion == null && StringUtils.isBlank(user.getPassword())) {
                user.setPassword(UUID.randomUUID().toString()); // XXX review if
                // UUID is a good choice here
            }

            try {
                // save user
                getUserManager().saveUser(user, updateMember, updateCoop);


            } catch (final AccessDeniedException ade) {
                // thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
                log.warn(ade.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return null;
            } catch (final UserExistsException e) {
                errors.rejectValue("username", "errors.existing.user",
                        new Object[]{user.getUsername(), user.getEmail()}, "duplicate user");

                // reset the version # to what was passed in
                user.setVersion(originalVersion);

                return "userform";
            } catch (final MemberNotFoundException e) {
                errors.rejectValue("memberId", "errors.member.not.found",
                        new Object[]{user.getIcNumber()}, "member not found");

                // reset the version # to what was passed in
                user.setVersion(originalVersion);

                return "userform";
            } catch (final CoopNotFoundException e) {
                errors.rejectValue("coopId", "errors.coop.not.found",
                        new Object[]{user.getCoopCode()}, "coop not found");

                // reset the version # to what was passed in
                user.setVersion(originalVersion);

                return "userform";
            } catch (final MemberAlreadyHasUserException e) {
                errors.rejectValue("memberId", "errors.member.user.exists",
                        new Object[]{user.getCoopCode()}, "member user already exists");

                // reset the version # to what was passed in
                user.setVersion(originalVersion);

                return "userform";
            } catch (final CoopAlreadyHasUserException e) {
                errors.rejectValue("coopId", "errors.coop.user.exists",
                        new Object[]{user.getCoopCode()}, "coop user already exists");

                // reset the version # to what was passed in
                user.setVersion(originalVersion);

                return "userform";
            }

            if (!StringUtils.equals(request.getParameter("from"), "list")) {
                saveMessage(request, getText("user.saved", user.getFullName(), locale));

                // return to main Menu
                return getCancelView();
            } else {
                if (StringUtils.isBlank(request.getParameter("version"))) {
                    saveMessage(request, getText("user.added", user.getFullName(), locale));

                    // Send an account information e-mail
                    message.setSubject(getText("signup.email.subject", locale));

                    try {
                        final String resetPasswordUrl = getUserManager().buildRecoveryPasswordUrl(user,
                                UpdatePasswordController.RECOVERY_PASSWORD_TEMPLATE);
                        sendUserMessage(user, getText("newuser.email.message", user.getFullName(), locale),
                                RequestUtil.getAppURL(request) + resetPasswordUrl);
                    } catch (final MailException me) {
                        saveError(request, me.getCause().getLocalizedMessage());
                    }

                    return getSuccessView();
                } else {
                    saveMessage(request, getText("user.updated.byAdmin", user.getFullName(), locale));
                }
            }
        }

        return "userform";
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected User showForm(final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        // If not an administrator, make sure user is not trying to add or edit another user
        if (!request.isUserInRole(Constants.ADMIN_ROLE) && !isFormSubmission(request)) {
            if (isAdd(request) || request.getParameter("id") != null) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                log.warn("User '" + request.getRemoteUser() + "' is trying to edit user with id '" +
                        request.getParameter("id") + "'");

                throw new AccessDeniedException("You do not have permission to modify other users.");
            }
        }

        if (!isFormSubmission(request)) {
            final String userId = request.getParameter("id");

            User user;
            if (userId == null && !isAdd(request)) {
                user = getUserManager().getUserByUsername(request.getRemoteUser());
            } else if (!StringUtils.isBlank(userId) && !"".equals(request.getParameter("version"))) {
                user = getUserManager().getUser(userId);
            } else {
                user = new User();
                user.addRole(new Role(Constants.USER_ROLE));
            }

            if (user.getId() != null && user.isMemberUser()) {
                Member member = memberManager.getByUserId(user.getId());
                user.setMemberId(member.getId());
            }

            if (user.getId() != null && user.isCoopUser()) {
                Coop coop = coopManager.getByUserId(user.getId());
                user.setCoopId(coop.getId());
            }

            return user;
        } else {
            // populate user object from database, so all fields don't need to be hidden fields in form
            return getUserManager().getUser(request.getParameter("id"));
        }
    }

    private boolean isFormSubmission(final HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("post");
    }

    protected boolean isAdd(final HttpServletRequest request) {
        final String method = request.getParameter("method");
        return (method != null && method.equalsIgnoreCase("add"));
    }
}
