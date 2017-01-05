package com.angkasa.service.impl;

import com.angkasa.dao.CoopDao;
import com.angkasa.dao.CoopImportDao;
import com.angkasa.model.*;
import com.angkasa.service.CoopImportManager;
import com.angkasa.service.DocumentManager;
import com.angkasa.service.impl.GenericManagerImpl;

import com.angkasa.util.PropsUtil;
import com.angkasa.util.XlsxParserUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import javax.jws.WebService;

@Service("coopImportManager")
@WebService(serviceName = "CoopImportService", endpointInterface = "com.angkasa.service.CoopImportManager")
public class CoopImportManagerImpl extends GenericManagerImpl<CoopImport, Long> implements CoopImportManager {
    CoopImportDao coopImportDao;

    @Autowired
    CoopDao coopDao;

    @Autowired
    DocumentManager documentManager;

    @Autowired
    PropsUtil propsUtil;

    @Autowired
    public CoopImportManagerImpl(CoopImportDao coopImportDao) {
        super(coopImportDao);
        this.coopImportDao = coopImportDao;
    }

    @Override
    public CoopImport saveWithFile(CoopImport coopImport) throws Exception{
        if(coopImport.getFile() != null && StringUtils.isNotBlank(coopImport.getName())){

            Document document = null;
            try {
                document = documentManager.add(DocumentManager.MODULE_EMPLOYER_IMPORT, coopImport.getName(), coopImport.getName(),
                        DocumentManager.MODULE_EMPLOYER_IMPORT, "application/xlsx", "", Base64.encode(coopImport.getFile()));
            } catch (Exception e){
                e.printStackTrace();
            }
            if(document != null){

                byte[] byteArray = documentManager.getFileByteArray(document.getId());
                String fileName = propsUtil.getDocumentRootPath() + "/" + coopImport.getName() + "-test.xlsx";
                FileUtils.writeByteArrayToFile(new File(fileName), byteArray);
                List<Coop> coopList = XlsxParserUtil.parseCoopXlsx(new File(fileName));

//                String fileName = propsUtil.getDocumentRootPath() + "/" + employerImport.getName() + "-test.xlsx";
//                FileUtils.writeByteArrayToFile(new File(fileName), employerImport.getFile());
//                List<Employer> employerList = XlsxParserUtil.parseEmployerXlsx(new File(fileName));

                List<Coop> coopListAfter = coopDao.validateCoopImportList(coopList);

                coopImport.setCoopList(coopListAfter);
                coopImport.setDocumentId(document.getId());

                coopImport = coopImportDao.save(coopImport);
            }
        }

        return coopImport;
    }
}