package com.angkasa.service.impl;

import com.angkasa.Constants;
import com.angkasa.dao.DocumentDao;
import com.angkasa.dao.DocumentDataDao;
import com.angkasa.model.Document;
import com.angkasa.model.DocumentData;
import com.angkasa.model.Role;
import com.angkasa.service.DocumentManager;
import com.angkasa.util.PropsUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Service("documentManager")
@WebService(serviceName = "DocumentService", endpointInterface = "com.angkasa.service.DocumentManager")
public class DocumentManagerImpl extends GenericManagerImpl<Document, String> implements DocumentManager {
    private PropsUtil propsUtil;
    private String document_root_path;
    DocumentDao documentDao;

    @Autowired
    DocumentDataDao documentDataDao;

    @Autowired
    public void setPropsUtil(PropsUtil propsUtil) {
        this.propsUtil = propsUtil;
    }

    @Autowired
    public DocumentManagerImpl(DocumentDao documentDao) {
        super(documentDao);
        this.documentDao = documentDao;
    }

    public void setDocument_root_path(String document_root_path) {
        this.document_root_path = document_root_path;
    }

    public String getDocument_root_path() {
        document_root_path = propsUtil.getDocumentRootPath();
        return document_root_path;
    }

    @Override
    public List<Document> findDocumentsByModuleAndSearch(String moduleName, String query, Date fromDate, Date toDate){
        String search = "";

        if(StringUtils.isNotBlank(moduleName)){
            search = moduleName +","+query;
        }

        List<Document> docList = search(search, fromDate, toDate, Document.class);
        List<Document> docResult = new ArrayList<Document>();
        for (Document document : docList) {
            if(StringUtils.contains(document.getFilename(), query)){
                docResult.add(document);
            }
        }
        return docResult;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String getDocumentDataFromTemp(Document document) throws Exception {
        DocumentData docData = documentDataDao.getByDocumentId(document.getId());
        String base64Encoded = docData.getData();
        byte[] base64Decoded = Base64.decode(base64Encoded.getBytes());

        String fileAbsolutePath = propsUtil.getDocumentRootPath() + File.separator + document.getId() + File.separator + document.getFilename();
        try {
            OutputStream out = FileUtils.openOutputStream(new File(fileAbsolutePath));
            out.write(base64Decoded);

            log.debug("File " + document.getFilename() + " being saved to " + fileAbsolutePath);

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(ERROR_CODE_UPLOADFAILURE);
        }

        return fileAbsolutePath;

    }

    @Override
    public String getDocumentData(Document document) throws Exception {
        DocumentData docData = documentDataDao.getByDocumentId(document.getId());
        return docData.getData();
    }

    @Override
    public Document add(String moduleName, String modulePrimKey, String filename, String contentType, String openField, byte[] base64EncodedFileContent) throws Exception {
        return add(moduleName, modulePrimKey, filename, "", contentType, openField, base64EncodedFileContent);
    }

    @Override
    public Document add(String moduleName, String modulePrimKey, String filename, String description, String contentType, String openField, byte[] base64EncodedFileContent) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String relativePath = File.separator + moduleName + File.separator + modulePrimKey + File.separator + uuid;
        String fileRelativePath = relativePath + File.separator + filename;
        String fileAbsolutePath = getDocument_root_path() + fileRelativePath;

        OutputStream out = null;

        //byte[] decodedFileContent = Base64.decode(base64EncodedFileContent);



        Document document = new Document();
        document.setId(uuid);
        document.setModuleName(moduleName);
        document.setFileRelativePath(fileRelativePath);
        document.setModulePrimKey(modulePrimKey);
        document.setOpenField(openField);
        document.setContentType(contentType);
        document.setDescription(description);

        log.debug("Saving document : "+fileRelativePath);
        document = dao.save(document);

        DocumentData docData = new DocumentData();
        docData.setData(new String(base64EncodedFileContent));
        docData.setDocument(document);

        log.debug("Saving document data : "+docData.getData());
        documentDataDao.save(docData);

        return document;

    }

    @Override
    public Document addPayslip(String moduleName, String modulePrimKey, String filename, String description, String contentType, String openField, byte[] base64EncodedFileContent, Date payslipMonth) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String relativePath = File.separator + moduleName + File.separator + modulePrimKey + File.separator + uuid;
        String fileRelativePath = relativePath + File.separator + filename;
        String fileAbsolutePath = getDocument_root_path() + fileRelativePath;

        OutputStream out = null;

        //byte[] decodedFileContent = Base64.decode(base64EncodedFileContent);



        Document document = new Document();
        document.setId(uuid);
        document.setModuleName(moduleName);
        document.setFileRelativePath(fileRelativePath);
        document.setModulePrimKey(modulePrimKey);
        document.setOpenField(openField);
        document.setContentType(contentType);
        document.setDescription(description);
        document.setPaySlipMonth(payslipMonth);

        document = dao.save(document);

        DocumentData docData = new DocumentData();
        docData.setData(new String(base64EncodedFileContent));
        docData.setDocument(document);
        documentDataDao.save(docData);

        return document;

    }

    public Document update(String username, String existingDocumentId, String filename, String contentType, String openField, byte[] base64EncodedFileContent) throws Exception {
        return update(username, existingDocumentId, filename, "", contentType, openField, base64EncodedFileContent);
    }

    public Document update(String username, String existingDocumentId, String filename, String description, String contentType, String openField, byte[] base64EncodedFileContent) throws Exception {
        Document document = documentDao.get(existingDocumentId);

        String relativePath = document.getParentPath();
        String fileRelativePath = relativePath + File.separator + filename;


        document.setFileRelativePath(fileRelativePath);
        document.setContentType(contentType);
        document.setOpenField(openField);
        document.setDescription(description);

        if(document.getDocumentData() != null) {
            document.getDocumentData().setData(new String(base64EncodedFileContent));
        }else {
            DocumentData docData = new DocumentData();
            docData.setData(new String(base64EncodedFileContent));
            document.setDocumentData(docData);
        }

        document = dao.save(document);
        return document;
    }

    public void deleteAllDocumentsByModule(String moduleName, String modulePrimKey) {
        List<Document> documents = documentDao.findByModule(moduleName, modulePrimKey);
        for (Document document : documents) {
            try {
                removeDocument(document.getId());
            } catch (Exception e) {
                log.warn(e);
            }
        }
    }

    @Override
    public void removeDocument(String id) throws Exception {

        documentDao.remove(id);

    }

    public List<Document> findAllDocumentsByModule(String moduleName, String... modulePrimKeys) {
        return findAllDocumentsByDateRangeAndModule(null, null, ALL_POS, ALL_POS, moduleName, modulePrimKeys);
    }

    @Override
    public List<Document> findAllDocumentsByDateRangeAndModule(Date startDate, Date endDate, int start, int max, String moduleName, String... modulePrimKeys) {
        return documentDao.findByDateRangeModule(startDate, endDate, start, max, moduleName, modulePrimKeys);
    }

    @Override
    public String[] getModuleListByRole(Set<Role> roles){

        Set<String> set = new HashSet<String>();

        for (Role role : roles) {
            if(StringUtils.equals(role.getName(), Constants.ADMIN_ROLE)){
                for (String string : MODULE_LIST_ADMIN) {
                    set.add(string);
                }
            }
        }

        return set.toArray(new String[0]);
    }

    @Override
    public byte[] getFileByteArray(String uuid) throws Exception{
        Document document = documentDao.get(uuid);
        String base64Encoded = this.getDocumentData(document);
        if(StringUtils.isBlank(base64Encoded)){
            return null;
        }
        byte[] base64Decoded = Base64.decode(base64Encoded.getBytes());
        return base64Decoded;
    }
}