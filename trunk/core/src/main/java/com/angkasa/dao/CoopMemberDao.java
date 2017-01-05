package com.angkasa.dao;

import java.util.List;

import com.angkasa.dao.GenericDao;
import com.angkasa.model.CoopMember;

/**
 * An interface that provides a data management interface to the CoopMember table.
 */
public interface CoopMemberDao extends GenericDao<CoopMember, Long> {

	List<CoopMember> getByMemberId(Long memberId);
    List<CoopMember> getByCoopId(Long coopId);
    CoopMember saveWithHexNo(CoopMember coopMember);
}