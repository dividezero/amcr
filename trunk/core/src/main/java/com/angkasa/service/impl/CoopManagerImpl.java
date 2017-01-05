package com.angkasa.service.impl;

import com.angkasa.Constants;
import com.angkasa.dao.CoopDao;
import com.angkasa.dao.CoopMemberDao;
import com.angkasa.dao.UserDao;
import com.angkasa.model.Coop;
import com.angkasa.model.CoopMember;
import com.angkasa.model.User;
import com.angkasa.service.CoopManager;
import com.angkasa.service.RoleManager;
import com.angkasa.service.UserExistsException;
import com.angkasa.service.UserManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

@Service("coopManager")
@WebService(serviceName = "CoopService", endpointInterface = "com.angkasa.service.CoopManager")
public class CoopManagerImpl extends GenericManagerImpl<Coop, Long> implements CoopManager {
    CoopDao coopDao;

    @Autowired
    UserManager userManager;

    @Autowired
    RoleManager roleManager;

    @Autowired
    CoopMemberDao coopMemberDao;

    @Autowired
    public CoopManagerImpl(CoopDao coopDao) {
        super(coopDao);
        this.coopDao = coopDao;
    }

    @Override
    public Coop getByUserId(Long userId) {
        return coopDao.getByUserId(userId);
    }

    @Override
    public List<Coop> getByMemberId(Long memberId) {
        List<Coop> coopList = new ArrayList<Coop>();
        List<CoopMember> coopMemberList = coopMemberDao.getByMemberId(memberId);

        for (CoopMember coopMember : coopMemberList) {
            coopList.add(coopMember.getCoop());
        }

        return coopList;
    }

    @Override
    public Coop getByAmcrCode(String amcrCode){
        return coopDao.getByAmcrCode(amcrCode);
    }

    @Override
    public Coop saveWithUser(Coop coop) throws UserExistsException {
        User user = coop.getUser();
        User savedUser = null;

        coop.setEnabled(true);
        coop = save(coop);
        try {
            if (user != null) {
                //setup shared fields
                user.setEmail(coop.getEmail());

                //new user
                if (user.getId() == null) {
                    user.setCoopId(coop.getId());
                    user.addRole(roleManager.getRole(Constants.USER_ROLE));
                    user.addRole(roleManager.getRole(Constants.USER_ROLE_COOP));
                    user.setEnabled(true);

                    user.setUsername(coop.getAmcrCode());
                    user.setPassword("12345");

                    savedUser = userManager.saveUser(user);


                }
                //old user. these fields are no longer required
                else {

                    savedUser = userManager.get(user.getId());
//                    savedUser.setPasswordHint(user.getPasswordHint());
//                    savedUser.setFirstName(user.getFirstName());
//                    savedUser.setLastName(user.getLastName());
//                    savedUser.setPhoneNumber(user.getPhoneNumber());
//                    savedUser.setWebsite(user.getWebsite());

                    savedUser = userManager.save(savedUser);
                }
            }

            if (savedUser != null) {
                coop.setUserId(savedUser.getId());
                coop.setUser(savedUser);
            }

            return save(coop);
        } catch (final Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }

    }

    @Override
    public List<Coop> validateCoopImportList(List<Coop> coopList){
        return coopDao.validateCoopImportList(coopList);
    }

    @Override
    public Coop validateCoopImport(Coop coop){
        return coopDao.validateCoopImport(coop);
    }

    @Override
    public List<Coop> processCoopImportList(List<Coop> coopList){
        return coopDao.processCoopImportList(coopList);
    }
}