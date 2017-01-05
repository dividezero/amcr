package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.Employer;

import java.util.List;

/**
 * An interface that provides a data management interface to the Employer table.
 */
public interface EmployerDao extends GenericDao<Employer, Long> {

    public static final String REMARK_EMPLOYER_CODE_EXISTS = "Employer Code already exists.";

    boolean employerCodeExists(String empCode);

    Employer getByEmployerCode(String empCode);

    List<Employer> processEmployerImport(List<Employer> employerList);
}