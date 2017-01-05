package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.MemberEmployer;

import java.util.Date;
import java.util.List;

/**
 * An interface that provides a data management interface to the MemberEmployer table.
 */
public interface MemberEmployerDao extends GenericDao<MemberEmployer, Long> {

    MemberEmployer getActiveMemberEmployerByMemberId(Long memberId);
    MemberEmployer getActiveMemberEmployerByMemberId(Long memberId, Date date);

    List<MemberEmployer> getByMemberId(Long memberId);
}