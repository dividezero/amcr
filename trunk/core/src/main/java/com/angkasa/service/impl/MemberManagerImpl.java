package com.angkasa.service.impl;

import com.angkasa.Constants;
import com.angkasa.dao.CoopMemberDao;
import com.angkasa.dao.EmployerDao;
import com.angkasa.dao.MemberDao;
import com.angkasa.dto.MemberExportDto;
import com.angkasa.model.*;
import com.angkasa.service.*;
import com.angkasa.service.impl.GenericManagerImpl;

import com.angkasa.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

@Service("memberManager")
@WebService(serviceName = "MemberService", endpointInterface = "com.angkasa.service.MemberManager")
public class MemberManagerImpl extends GenericManagerImpl<Member, Long> implements MemberManager {
    MemberDao memberDao;

    @Autowired
    UserManager userManager;

    @Autowired
    RoleManager roleManager;

    @Autowired
    CoopManager coopManager;

    @Autowired
    CoopMemberDao coopMemberDao;

    @Autowired
    EmployerManager employerManager;

    @Autowired
    public MemberManagerImpl(MemberDao memberDao) {
        super(memberDao);
        this.memberDao = memberDao;
    }


    @Override
    public Member saveWithUser(Member member, boolean saveCoopMember) throws UserExistsException {
        User user = member.getUser();
        User savedUser = null;
        Long coopId = member.getCoopId();

        member.setEnabled(true);
        member = save(member);
        try {
            //shared fields
            user.setEmail(member.getEmail());

            if (user != null) {
                // new user
                if (user.getId() == null) {
                    user.setMemberId(member.getId());
                    user.addRole(roleManager.getRole(Constants.USER_ROLE));
                    user.addRole(roleManager.getRole(Constants.USER_ROLE_MEMBER));
                    user.setEnabled(true);

                    user.setUsername(member.getIcNumber());
                    user.setFirstName(member.getName());
                    user.setLastName("");
                    user.setPassword("12345");

                    log.debug("saveuser");
                    savedUser = userManager.saveUser(user);
                } else {
                    if(StringUtils.isBlank(user.getUsername())){
                        user.setUsername(member.getIcNumber());
                    }
                    log.debug("saveuser2");
                    savedUser = userManager.save(user);
                }
                member.setUserId(savedUser.getId());
                member.setUser(savedUser);
            }


        } catch (final Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
        if(savedUser!=null){
            member.setUserId(savedUser.getId());
        }
        member = save(member);

        if (saveCoopMember) {
            CoopMember coopMember = new CoopMember();
            coopMember.setMemberId(member.getId());
            coopMember.setIcNumber(member.getIcNumber());
            coopMember.setName(member.getName());
            coopMember.setCoopId(coopId);
            coopMemberDao.save(coopMember);
        }

        return member;
    }

    @Override
    public Member getByIcNumber(String ic) {
        return memberDao.getByIcNumber(ic);
    }

    @Override
    public Member getByUserId(Long userId){
        return memberDao.getByUserId(userId);
    }

    @Override
    public List<Member> searchByCoop(String searchTerm, Long coopId) {
        return memberDao.searchByCoop(searchTerm, coopId);
    }


    @Override
    public List<MemberExportDto> memberExportDtoList(List<Member> memberList) {
        List<MemberExportDto> results = new ArrayList<MemberExportDto>();

        for (Member member : memberList) {
            // Create the memberExportDto from the list of members
            Employer activeEmployer = employerManager.getActiveEmployerByMemberId(member.getId());

            List<Coop> coopList = coopManager.getByMemberId(member.getId());
            for (Coop coop : coopList) {
                MemberExportDto memberExportDto = new MemberExportDto();

                memberExportDto.setId(member.getId());
                memberExportDto.setIcNumber(member.getIcNumber());
                memberExportDto.setCoopCode(coop.getCoopCode());
                memberExportDto.setName(member.getName());
                memberExportDto.setEmployerCode(activeEmployer.getEmployerCode());
                memberExportDto.setStatus(member.getStatus());
                memberExportDto.setEmployerNo(activeEmployer.getEmployerNo());

                results.add(memberExportDto);
            }
        }
        return results;
    }

    @Override
    public List<Member> processMemberImport(List<Member> memberList, boolean useBpaCode) {
        return memberDao.processMemberImport(memberList, useBpaCode);
    }

    @Override
    public Member processMemberImport(Member member, boolean useBpaCode) {
        List<Member> memList = new ArrayList<Member>();
        memList.add(member);
        memList = memberDao.processMemberImport(memList, useBpaCode);
        if (!memList.isEmpty()) {
            return memList.get(0);
        }
        return null;
    }

    @Override
    public List<Member> validateMemberImportList(List<Member> memberList, boolean useBpaCode) {
        return memberDao.validateMemberImportList(memberList, useBpaCode);
    }

    @Override
    public Member validateMemberImport(Member member, boolean useBpaCode) {
        return memberDao.validateMemberImport(member, useBpaCode);
    }
}