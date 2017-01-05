package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.CoopImport;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface CoopImportManager extends GenericManager<CoopImport, Long> {

    CoopImport saveWithFile(CoopImport coopImport) throws Exception;
}