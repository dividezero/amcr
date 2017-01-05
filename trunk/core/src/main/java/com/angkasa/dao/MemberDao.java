package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.Employer;
import com.angkasa.model.Member;

import java.util.List;

/**
 * An interface that provides a data management interface to the Member table.
 */
public interface MemberDao extends GenericDao<Member, Long> {

    public static final String REMARK_EMPLOYER_CODE_NOT_EXISTS = "Employer code not found";
    public static final String REMARK_ACTIVE_EMPLOYER_EXISTS = "Active Employer for the member already exists for this date";
    public static final String REMARK_IC_NUMBER_EXISTS = "IC Number already exists. Will update/reuse existing member.";
    public static final String REMARK_IC_NUMBER_EMPTY = "IC Number is required.";
    public static final String REMARK_COOP_NOT_EXISTS = "Coop not found";
    public static final String REMARK_COOP_COOPCODE_NOT_EXISTS = "Coop not found using coop code";
    public static final String REMARK_COOP_BPACODE_NOT_EXISTS = "Coop not found using BPA code";
    public static final String REMARK_COOP_AMCRCODE_NOT_EXISTS = "Coop not found using AMCR code";
    public static final String REMARK_COOP_MEMBER_EXISTS = "Coop member link already exists.";
    public static final String REMARK_IC_NUMBER_BROKEN = "IC Number broken";

    Member getByIcNumber(String icNumber);
    Member getByUserId(Long userId);

    List<Member> searchByCoop(String searchTerm, Long coopId);

    boolean icNumberExists(String icNumber);

    List<Member> validateMemberImportList(List<Member> memberList, boolean useBpaCode);
    Member validateMemberImport(Member member, boolean useBpaCode);
    List<Member> processMemberImport(List<Member> memberList, boolean useBpaCode);
}