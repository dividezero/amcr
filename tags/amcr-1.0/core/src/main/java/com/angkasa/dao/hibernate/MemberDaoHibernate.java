package com.angkasa.dao.hibernate;

import com.angkasa.Constants;
import com.angkasa.dao.*;
import com.angkasa.model.*;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import com.angkasa.util.DateUtil;
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
    public List<Member> validateMemberImportList(List<Member> memberList) {
        for (Member member : memberList) {
            member = validateMemberImport(member);
        }
        return memberList;
    }

    @Override
    public Member validateMemberImport(Member member) {
        member.setStatus(null);
        member.setImportRemark("");

        // Validation time!
        // Member
        if(StringUtils.isBlank(member.getIcNumber())){
            member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
            member.addImportRemark(REMARK_IC_NUMBER_EMPTY);
        }
        if (icNumberExists(member.getIcNumber())) {
            Member existingMember = getByIcNumber(member.getIcNumber());
            existingMember.setImportEmployerCode(member.getImportEmployerCode());
            member = existingMember;

            //member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
            member.addImportRemark(REMARK_IC_NUMBER_EXISTS);
        }

        // Coop
        if (StringUtils.isNotBlank(member.getImportCoopCode())) {
            CoopMember coopMember = new CoopMember();
            if (coopDao.coopCodeExists(member.getImportCoopCode())) {
                Coop coop = coopDao.getByCoopCode(member.getImportCoopCode());
                coopMember.setCoopId(coop.getId());

                // Check if the coopMember already exists
                if (member.getId() != null) {
                    List<CoopMember> existingCoopMembers = coopMemberDao.getByMemberId(member.getId());
                    for (CoopMember cm : existingCoopMembers) {
                        if (cm.getCoopId().equals(coop.getId())) {
                            member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
                            member.addImportRemark(REMARK_COOP_MEMBER_EXISTS);
                        }
                    }
                }

            } else {
                member.setImportStatus(Constants.IMPORT_STATUS_INVALID);
                member.addImportRemark(REMARK_COOP_NOT_EXISTS);
            }
        }

        if (!StringUtils.equals(member.getImportStatus(), Constants.IMPORT_STATUS_INVALID)) {
            member.setImportStatus(Constants.IMPORT_STATUS_VALID);
        }
        return member;
    }

    @Override
    public List<Member> processMemberImport(List<Member> memberList) {
        List<Member> result = new ArrayList<Member>();
        for (Member member : memberList) {

            member = validateMemberImport(member);

            // Save
            if (!StringUtils.equals(member.getImportStatus(), Constants.IMPORT_STATUS_INVALID)) {

                member.setTypeFlag(Constants.MEMBER_FLAG_ANGKASA);
                member.setPreloaded(true);
                Member savedMem = this.save(member);

                // Save coopMember
                CoopMember coopMember = new CoopMember();
                Coop coop = coopDao.getByCoopCode(member.getImportCoopCode());
                coopMember.setCoopId(coop.getId());
                coopMember.setMemberId(savedMem.getId());
                coopMember.setIcNumber(savedMem.getIcNumber());
                coopMember.setName(savedMem.getName());
                coopMemberDao.save(coopMember);

                savedMem.setImportStatus(Constants.IMPORT_STATUS_SUCCESS);
                savedMem.setImportId(member.getImportId());
                savedMem.setImportCoopCode(member.getImportCoopCode());
                savedMem.setImportEmployerCode(member.getImportEmployerCode());
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
