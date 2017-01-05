package com.angkasa.service.impl;

import com.angkasa.dao.EmployerDao;
import com.angkasa.dao.EmployerImportDao;
import com.angkasa.model.Document;
import com.angkasa.model.Employer;
import com.angkasa.model.EmployerImport;
import com.angkasa.service.DocumentManager;
import com.angkasa.service.EmployerImportManager;
import com.angkasa.util.PropsUtil;
import com.angkasa.util.XlsxParserUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

@Service("employerImportManager")
@WebService(serviceName = "EmployerImportService", endpointInterface = "com.angkasa.service.EmployerImportManager")
public class EmployerImportManagerImpl extends GenericManagerImpl<EmployerImport, Long> implements EmployerImportManager {
    EmployerImportDao employerImportDao;

    @Autowired
    EmployerDao employerDao;

    @Autowired
    DocumentManager documentManager;

    @Autowired
    PropsUtil propsUtil;

    @Autowired
    public EmployerImportManagerImpl(EmployerImportDao employerImportDao) {
        super(employerImportDao);
        this.employerImportDao = employerImportDao;
    }

    @Override
    public EmployerImport saveWithFile(EmployerImport employerImport) throws Exception{
        if(employerImport.getFile() != null && StringUtils.isNotBlank(employerImport.getName())){

            Document document = null;
            try {
                document = documentManager.add(DocumentManager.MODULE_EMPLOYER_IMPORT, employerImport.getName(), employerImport.getName(),
                        DocumentManager.MODULE_EMPLOYER_IMPORT, "application/xlsx", "", Base64.encode(employerImport.getFile()));
            } catch (Exception e){
                e.printStackTrace();
            }
            if(document != null){

                byte[] byteArray = documentManager.getFileByteArray(document.getId());
                String fileName = propsUtil.getDocumentRootPath() + "/" + employerImport.getName() + "-test.xlsx";
                FileUtils.writeByteArrayToFile(new File(fileName), byteArray);
                List<Employer> employerList = XlsxParserUtil.parseEmployerXlsx(new File(fileName));

//                String fileName = propsUtil.getDocumentRootPath() + "/" + employerImport.getName() + "-test.xlsx";
//                FileUtils.writeByteArrayToFile(new File(fileName), employerImport.getFile());
//                List<Employer> employerList = XlsxParserUtil.parseEmployerXlsx(new File(fileName));

                List<Employer> employerListAfter = employerDao.processEmployerImport(employerList);

                employerImport.setEmployerList(employerListAfter);
                employerImport.setDocumentId(document.getId());

                employerImport = employerImportDao.save(employerImport);
            }
        }


        return employerImport;
    }
}