package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.MemberImport;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface MemberImportManager extends GenericManager<MemberImport, Long> {

    MemberImport saveWithFile(MemberImport memberImport, boolean useCoopCode) throws Exception;
}