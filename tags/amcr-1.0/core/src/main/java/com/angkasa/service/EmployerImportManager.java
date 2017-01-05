package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.EmployerImport;

import java.io.File;
import java.util.List;
import javax.jws.WebService;

@WebService
public interface EmployerImportManager extends GenericManager<EmployerImport, Long> {

    EmployerImport saveWithFile(EmployerImport employerImport) throws Exception;


}