package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.MemberEmployer;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface MemberEmployerManager extends GenericManager<MemberEmployer, Long> {
    
}