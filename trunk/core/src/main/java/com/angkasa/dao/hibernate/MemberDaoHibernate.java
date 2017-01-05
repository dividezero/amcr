package com.angkasa.dao.hibernate;

import com.angkasa.Constants;
import com.angkasa.dao.*;
import com.angkasa.model.*;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import com.angkasa.util.DateUtil;
import com.angkasa.util.PropsUtil;
import com.angkasa.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("memberDao")
public class MemberDaoHibernate extends GenericDaoHibernate<Member, Long> implements MemberDao {

    @Autowired
    EmployerDao employerDao;

    @Autowired
    CoopDao coopDao;

    @Autowired
    CoopMemberDao coopMemberDao;

    @Autowired
    MemberEmployerDao memberEmployerDao;

    @Autowired
    AmcrLookupDao amcrLookupDao;

    public MemberDaoHibernate() {
        super(Member.class);
    }

    @Override
    public Member getByIcNumber(String icNumber) {
        Criteria criteria = getSession().createCriteria(Member.class);
        criteria.add(Restrictions.eq("icNumber", icNumber));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Member> result = criteria.list();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public Member getByUserId(Long userId) {
        Criteria criteria = getSession().createCriteria(Member.class);
        criteria.add(Restrictions.eq("userId", userId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Member> result = criteria.list();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public List<Member> searchByCoop(String searchTerm, Long coopId) {
        log.debug("coopId " + coopId);
        List<Member> searchResult = null;
        if (StringUtils.isNotBlank(searchTerm)) {
            searchResult = search(searchTerm);
        } else {
            searchResult = getAll();
        }
        List<Member> result = new ArrayList<Member>();
        List<CoopMember> coopMemberList = coopMemberDao.getByCoopId(coopId);
        for (Member member : searchResult) {
            for (CoopMember coopMember : coopMemberList) {
                if (coopMember.getMemberId().equals(member.getId())) {
                    result.add(member);
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public boolean icNumberExists(String icNumber) {
        Member result = getByIcNumber(icNumber);

        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<Member> validateMemberImportList(List<Member> memberList, boolean useBpaCode) {
        for (Member member : memberList) {
            member = validateMemberImport(member, useBpaCode);
        }
        return memberList;
    }

    @Override
    public Member validateMemberImport(Member member, boolean useBpaCode) {
        member.setImportStatus(null);
        member.setImportRemark("");

        // Validation time!
        // Member
        if (StringUtils.isBlank(member.getIcNumber())) {
            member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
            member.addImportRemark(REMARK_IC_NUMBER_EMPTY);
        } else if (member.getIcNumber().length() != 12) {
            log.debug("icnumber length:" + member.getIcNumber().length());
            member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
            member.addImportRemark(REMARK_IC_NUMBER_BROKEN);
        }
        if (icNumberExists(member.getIcNumber())) {
            Member existingMember = getByIcNumber(member.getIcNumber());
            existingMember.setEmployerCode(member.getEmployerCode());
            member = existingMember;

            //member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
            member.addImportRemark(REMARK_IC_NUMBER_EXISTS);
        }

        // Coop
        CoopMember coopMember = null;
        // use coop code
        if (useBpaCode) {
//            if (StringUtils.isNotBlank(member.getImportCoopCode())) {
//                if (coopDao.coopCodeExists(member.getImportCoopCode())) {
//                    Coop coop = coopDao.getByCoopCode(member.getImportCoopCode());
//                    coopMember = new CoopMember();
//                    coopMember.setCoopId(coop.getId());
//
//                } else {
//                    member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
//                    member.addImportRemark(REMARK_COOP_COOPCODE_NOT_EXISTS);
//                }
//            }
            // Now use BPA code instead of coop code
            if (StringUtils.isNotBlank(member.getImportBPACode())) {
                log.debug("Searching by BPA Code : "+member.getImportBPACode());
                Coop coop = coopDao.getByBPACode(member.getImportBPACode());
                if (coop != null) {
                    coopMember = new CoopMember();
                    coopMember.setCoopId(coop.getId());

                } else {
                    member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
                    member.addImportRemark(REMARK_COOP_BPACODE_NOT_EXISTS);
                }
            } else {
                log.debug("Coop code empty");
                member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
                member.addImportRemark(REMARK_COOP_BPACODE_NOT_EXISTS);
            }
            //use coop amcr code
        } else {
            if (StringUtils.isNotBlank(member.getImportCoopAmcrCode())) {
                if (coopDao.amcrCodeExists(member.getImportCoopAmcrCode())) {
                    Coop coop = coopDao.getByAmcrCode(member.getImportCoopAmcrCode());
                    coopMember = new CoopMember();
                    coopMember.setCoopId(coop.getId());

                } else {
                    member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
                    member.addImportRemark(REMARK_COOP_AMCRCODE_NOT_EXISTS);
                }
            } else {
                log.debug("coop Amcr code empty");
                member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
                member.addImportRemark(REMARK_COOP_AMCRCODE_NOT_EXISTS);
            }
        }

        // Check if the coopMember already exists
        if (member.getId() != null && coopMember != null) {
            List<CoopMember> existingCoopMembers = coopMemberDao.getByMemberId(member.getId());
            for (CoopMember cm : existingCoopMembers) {
                if (cm.getCoopId().equals(coopMember.getCoopId())) {
                    member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
                    member.addImportRemark(REMARK_COOP_MEMBER_EXISTS);
                }
            }
        }

        if (!StringUtils.equals(member.getImportStatus(), Constants.IMPORT_STATUS_INVALID)) {
            member.setImportStatus(Constants.IMPORT_STATUS_VALID);
        }
        return member;
    }

    @Override
    public List<Member> processMemberImport(List<Member> memberList, boolean useBpaCode) {
        List<Member> result = new ArrayList<Member>();
        for (Member member : memberList) {

            member = validateMemberImport(member, useBpaCode);

            // Save
            if (!StringUtils.equals(member.getImportStatus(), Constants.IMPORT_STATUS_INVALID)) {

                member.setTypeFlag(Constants.MEMBER_FLAG_ANGKASA);
                member.setEnabled(false);
                member.setPreloaded(true);
                Member savedMem = this.save(member);

                // Save coopMember
                CoopMember coopMember = new CoopMember();
                Coop coop = null;
                if (useBpaCode) {
//                    coop = coopDao.getByCoopCode(member.getImportCoopCode());
                    coop = coopDao.getByBPACode(member.getImportBPACode());
                } else {
                    coop = coopDao.getByAmcrCode(member.getImportCoopAmcrCode());

                }
                if (coop != null) {
                    coopMember.setCoopId(coop.getId());
                    coopMember.setCoop(coop);
                    coopMember.setMemberId(savedMem.getId());
                    coopMember.setMember(savedMem);
                    coopMember.setMembershipAknNo(member.getImportCoopMemberAknNo());
//                    coopMember.setIcNumber(savedMem.getIcNumber());
//                    coopMember.setName(savedMem.getName());
                    coopMemberDao.saveWithHexNo(coopMember);
                }

                savedMem.setImportStatus(Constants.IMPORT_STATUS_SUCCESS);
                savedMem.setImportId(member.getImportId());
                savedMem.setImportCoopCode(member.getImportCoopCode());
                savedMem.setImportCoopAmcrCode(member.getImportCoopAmcrCode());
                savedMem.setEmployerCode(member.getEmployerCode());
                member = savedMem;

                result.add(savedMem);
            }
        }
        return memberList;
    }

    @Override
    public Member save(Member object) {
        if (StringUtils.isBlank(object.getAmcrCode())) {
            object.setAmcrCode(amcrLookupDao.getNewMemberLookup());
        }
        return super.save(object);
    }
}
