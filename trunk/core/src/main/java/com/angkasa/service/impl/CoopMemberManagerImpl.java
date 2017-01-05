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
    public CoopMember saveWithHexNo(CoopMember coopMember) {
        coopMember.setCoop(coopDao.get(coopMember.getCoopId()));
        coopMember.setMember(memberDao.get(coopMember.getMemberId()));

        return coopMemberDao.saveWithHexNo(coopMember);
    }
}