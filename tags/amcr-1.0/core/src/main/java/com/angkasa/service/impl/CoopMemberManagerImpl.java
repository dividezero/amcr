package com.angkasa.service.impl;

import com.angkasa.dao.CoopDao;
import com.angkasa.dao.CoopMemberDao;
import com.angkasa.dao.MemberDao;
import com.angkasa.model.Coop;
import com.angkasa.model.CoopMember;
import com.angkasa.model.Member;
import com.angkasa.service.CoopMemberManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.jws.WebService;

@Service("coopMemberManager")
@WebService(serviceName = "CoopMemberService", endpointInterface = "com.angkasa.service.CoopMemberManager")
public class CoopMemberManagerImpl extends GenericManagerImpl<CoopMember, Long> implements CoopMemberManager {
    CoopMemberDao coopMemberDao;

    @Autowired
    MemberDao memberDao;

    @Autowired
    CoopDao coopDao;

    @Autowired
    public CoopMemberManagerImpl(CoopMemberDao coopMemberDao) {
        super(coopMemberDao);
        this.coopMemberDao = coopMemberDao;
    }

    @Override
    public List<CoopMember> getByMemberId(Long memberId) {
        return coopMemberDao.getByMemberId(memberId);
    }

    @Override
    public List<CoopMember> getByCoopId(Long coopId){
        return coopMemberDao.getByCoopId(coopId);
    }

    @Override
    public CoopMember saveWithAknNo(CoopMember coopMember) {
        Coop coop = coopDao.get(coopMember.getCoopId());
        String akn = coop.getAndIncreaseMemberHexRunningNo();
        log.debug("aknNo = " + akn);
        log.debug("next aknNo = " + coop.getMemberHexRunningNo());
        coopMember.setMembershipAknNo(akn);
        coopDao.save(coop);
        log.debug("coop saved.");

        // Add name and IC for search/indexing purposes
        if(coopMember.getMemberId()!=null){
            Member member = memberDao.get(coopMember.getMemberId());
            coopMember.setIcNumber(member.getIcNumber());
            coopMember.setName(member.getName());
        }

        return coopMemberDao.save(coopMember);
    }
}