package com.angkasa.service.impl;

import com.angkasa.dao.CoopDao;
import com.angkasa.dao.MemberDao;
import com.angkasa.model.Coop;
import com.angkasa.model.Member;
import com.angkasa.service.*;
import org.apache.commons.lang.StringUtils;
import com.angkasa.dao.UserDao;
import com.angkasa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("userManager")
@WebService(serviceName = "UserService", endpointInterface = "com.angkasa.service.UserService")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager, UserService {
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private CoopDao coopDao;

    private MailEngine mailEngine;
    private SimpleMailMessage message;
    private PasswordTokenManager passwordTokenManager;

    private String passwordRecoveryTemplate = "passwordRecovery.vm";
    private String passwordUpdatedTemplate = "passwordUpdated.vm";

    @Autowired
    @Qualifier("passwordEncoder")
    public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Autowired
    public void setUserDao(final UserDao userDao) {
        this.dao = userDao;
        this.userDao = userDao;
    }

    @Autowired(required = false)
    public void setMailEngine(final MailEngine mailEngine) {
        this.mailEngine = mailEngine;
    }

    @Autowired(required = false)
    public void setMailMessage(final SimpleMailMessage message) {
        this.message = message;
    }

    @Autowired(required = false)
    public void setPasswordTokenManager(final PasswordTokenManager passwordTokenManager) {
        this.passwordTokenManager = passwordTokenManager;
    }

    /**
     * Velocity template name to send users a password recovery mail (default
     * passwordRecovery.vm).
     *
     * @param passwordRecoveryTemplate the Velocity template to use (relative to classpath)
     * @see com.angkasa.service.MailEngine#sendMessage(org.springframework.mail.SimpleMailMessage, String, java.util.Map)
     */
    public void setPasswordRecoveryTemplate(final String passwordRecoveryTemplate) {
        this.passwordRecoveryTemplate = passwordRecoveryTemplate;
    }

    /**
     * Velocity template name to inform users their password was updated
     * (default passwordUpdated.vm).
     *
     * @param passwordUpdatedTemplate the Velocity template to use (relative to classpath)
     * @see com.angkasa.service.MailEngine#sendMessage(org.springframework.mail.SimpleMailMessage, String, java.util.Map)
     */
    public void setPasswordUpdatedTemplate(final String passwordUpdatedTemplate) {
        this.passwordUpdatedTemplate = passwordUpdatedTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(final String userId) {
        return userDao.get(new Long(userId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUsers() {
        return userDao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User saveUser(final User user) throws UserExistsException {

        if (user.getVersion() == null) {
            // if new user, lowercase userId
            user.setUsername(user.getUsername().toLowerCase());
        }

        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if (passwordEncoder != null) {
            // Check whether we have to encrypt (or re-encrypt) the password
            if (user.getVersion() == null) {
                // New user, always encrypt
                passwordChanged = true;
            } else {
                // Existing user, check password in DB
                final String currentPassword = userDao.getUserPassword(user.getId());
                if (currentPassword == null) {
                    passwordChanged = true;
                } else {
                    if (!currentPassword.equals(user.getPassword())) {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if (passwordChanged) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        } else {
            log.warn("PasswordEncoder not set, skipping password encryption...");
        }

        try {
            return userDao.saveUser(user);
        } catch (final Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    @Override
    public User saveUser(final User user, boolean updateMember, boolean updateCoop) throws UserExistsException, MemberNotFoundException, CoopNotFoundException {
        if(updateMember){
            if(StringUtils.isNotBlank(user.getIcNumber())){
                Member member = memberDao.getByIcNumber(user.getIcNumber());
                if(member == null){
                    log.warn("Member Not Found for IC number "+user.getIcNumber()+"!");
                    throw new MemberNotFoundException("Member Not Found for IC number "+user.getIcNumber()+"!");
                } else {
                    log.debug("Member found! id = "+member.getId());
                    user.setMemberId(member.getId());
                    user.setMember(member);
                }

            }else{
                log.warn("Member Not Found! IC number null!");
                throw new MemberNotFoundException("Member Not Found! IC number null!");
            }
        }
        if(updateCoop){
            if(StringUtils.isNotBlank(user.getCoopCode())){
                Coop coop = coopDao.getByCoopCode(user.getCoopCode());
                if(coop == null){
                    log.warn("Coop Not Found for CoopCode "+user.getCoopCode()+"!");
                    throw new CoopNotFoundException("Coop Not Found for CoopCode "+user.getCoopCode()+"!");
                } else {
                    log.debug("Coop found! id = "+coop.getId());
                    user.setCoopId(coop.getId());
                    user.setCoop(coop);
                }

            }else{
                log.warn("Coop Not Found! CoopCode null!");
                throw new CoopNotFoundException("Coop Not Found! CoopCode null!");
            }
        }
        return saveUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUser(final User user) {
        log.debug("removing user: " + user);
        userDao.remove(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUser(final String userId) {
        log.debug("removing user: " + userId);
        userDao.remove(new Long(userId));
    }

    /**
     * {@inheritDoc}
     *
     * @param username the login name of the human
     * @return User the populated user object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when username not found
     */
    @Override
    public User getUserByUsername(final String username) throws UsernameNotFoundException {
        return (User) userDao.loadUserByUsername(username);
    }

    @Override
    public User getByUsername(String username){
        return userDao.getByUsername(username);
    }

    @Override
    public User getByEmail(String email){
        return userDao.getByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> search(final String searchTerm) {
        return super.search(searchTerm, User.class);
    }

    @Override
    public String buildRecoveryPasswordUrl(final User user, final String urlTemplate) {
        final String token = generateRecoveryToken(user);
        final String username = user.getUsername();
        return StringUtils.replaceEach(urlTemplate,
                new String[]{"{username}", "{token}"},
                new String[]{username, token});
    }

    @Override
    public String generateRecoveryToken(final User user) {
        return passwordTokenManager.generateRecoveryToken(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRecoveryTokenValid(final String username, final String token) {
        return isRecoveryTokenValid(getUserByUsername(username), token);
    }

    @Override
    public boolean isRecoveryTokenValid(final User user, final String token) {
        return passwordTokenManager.isRecoveryTokenValid(user, token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPasswordRecoveryEmail(final String username, final String urlTemplate) {
        log.debug("Sending password recovery token to user: " + username);

        final User user = getUserByUsername(username);
        final String url = buildRecoveryPasswordUrl(user, urlTemplate);

        sendUserEmail(user, passwordRecoveryTemplate, url);
    }

    private void sendUserEmail(final User user, final String template, final String url) {
        message.setTo(user.getFullName() + "<" + user.getEmail() + ">");

        final Map<String, Serializable> model = new HashMap<String, Serializable>();
        model.put("user", user);
        model.put("applicationURL", url);

        mailEngine.sendMessage(message, template, model);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public User updatePassword(final String username, final String currentPassword, final String recoveryToken, final String newPassword, final String applicationUrl) throws UserExistsException {
        User user = getUserByUsername(username);
        if (isRecoveryTokenValid(user, recoveryToken)) {
            log.debug("Updating password from recovery token for user:" + username);
            user.setPassword(newPassword);
            user = saveUser(user);
            passwordTokenManager.invalidateRecoveryToken(user, recoveryToken);

            sendUserEmail(user, passwordUpdatedTemplate, applicationUrl);

            return user;
        } else if (StringUtils.isNotBlank(currentPassword)) {
            if (passwordEncoder.matches(currentPassword, user.getPassword())) {
                log.debug("Updating password (providing current password) for user:" + username);
                user.setPassword(newPassword);
                user = saveUser(user);
                return user;
            }
        }
        // or throw exception
        return null;
    }

    @Override
    public User getUsersByCoop(Long coopId){
        return userDao.getUsersByCoop(coopId);
    }

    @Override
    public User getUsersByMember(Long memberId){
        return userDao.getUsersByMember(memberId);
    }
}
