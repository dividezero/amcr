package com.angkasa.service;

import com.angkasa.dto.MemberExportDto;
import com.angkasa.service.GenericManager;
import com.angkasa.model.Member;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface MemberManager extends GenericManager<Member, Long> {

    Member getByIcNumber(String ic);
    Member getByUserId(Long userId);
    List<Member> searchByCoop(String searchTerm, Long coopId);

    Member saveWithUser(Member member, boolean saveCoopMember) throws UserExistsException;

    List<MemberExportDto> memberExportDtoList(List<Member> memberList);

    List<Member> processMemberImport(List<Member> memberList);
    Member processMemberImport(Member member);

    List<Member> validateMemberImportList(List<Member> memberList);
    Member validateMemberImport(Member member);
}