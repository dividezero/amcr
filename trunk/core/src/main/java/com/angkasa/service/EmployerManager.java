package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.Employer;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface EmployerManager extends GenericManager<Employer, Long> {
    boolean employerCodeExists(String empCode);
    Employer getByEmployerCode(String empCode);

    Employer getActiveEmployerByMemberId(Long memberId);
}