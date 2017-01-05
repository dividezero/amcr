package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.Coop;

import java.util.List;

/**
 * An interface that provides a data management interface to the Coop table.
 */
public interface CoopDao extends GenericDao<Coop, Long> {

    public static final String REMARK_COOP_NAME_NOT_EXISTS = "Coop name not found";

    Coop getByUserId(Long userId);
    Coop getByCoopCode(String coopCode);

    boolean coopCodeExists(String coopCode);
    List<Coop> processCoopImportList(List<Coop> coopList);

    List<Coop> validateCoopImportList(List<Coop> coopList);
    Coop validateCoopImport(Coop coop);
}