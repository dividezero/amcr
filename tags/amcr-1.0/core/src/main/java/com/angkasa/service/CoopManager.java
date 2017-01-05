package com.angkasa.service;

import com.angkasa.model.CoopMember;
import com.angkasa.service.GenericManager;
import com.angkasa.model.Coop;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface CoopManager extends GenericManager<Coop, Long> {

    Coop getByUserId(Long userId);
    List<Coop> getByMemberId(Long memberId);

    Coop saveWithUser(Coop coop) throws UserExistsException;

}