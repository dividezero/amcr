package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.Coop;

import java.util.List;

/**
 * An interface that provides a data management interface to the Coop table.
 */
public interface CoopDao extends GenericDao<Coop, Long> {

    public static final String REMARK_COOP_NAME_NOT_EXISTS = "Coop name not found";
    public static final String REMARK_COOP_CODE_EXISTS = "Coop code already exists";

    Coop getByUserId(Long userId);
    Coop getByCoopCode(String coopCode);
    Coop getByBPACode(String bpaCode);
    Coop getByAmcrCode(String amcrCode);

    boolean coopCodeExists(String coopCode);
    boolean amcrCodeExists(String amcrCode);
    List<Coop> processCoopImportList(List<Coop> coopList);

    List<Coop> validateCoopImportList(List<Coop> coopList);
    Coop validateCoopImport(Coop coop);
}