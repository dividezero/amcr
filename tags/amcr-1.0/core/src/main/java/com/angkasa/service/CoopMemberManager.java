package com.angkasa.service;

import java.util.List;

import javax.jws.WebService;

import com.angkasa.model.CoopMember;

@WebService
public interface CoopMemberManager extends GenericManager<CoopMember, Long> {
    
	List<CoopMember> getByMemberId(Long memberId);
    List<CoopMember> getByCoopId(Long coopId);
    CoopMember saveWithAknNo(CoopMember coopMember);

}