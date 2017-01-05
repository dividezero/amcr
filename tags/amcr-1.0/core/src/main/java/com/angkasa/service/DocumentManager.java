package com.angkasa.service;

import com.angkasa.model.Document;
import com.angkasa.model.Role;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;
import java.util.Set;

@WebService
public interface DocumentManager extends GenericManager<Document, String> {

    public static final String ERROR_CODE_UPLOADFAILURE = "EC.DM.001";
    public static final String ERROR_CODE_DELETEFAIL = "EC.DM.002";

    public static final String MODULE_EMPLOYER_IMPORT ="employer_import";
    public static final String MODULE_MEMBER_IMPORT ="member_import";

    public static final String[] MODULE_LIST_DEFAULT = new String[]{
    };

    public static final String[] MODULE_LIST_ADMIN = new String[]{
            DocumentManager.MODULE_EMPLOYER_IMPORT,
            DocumentManager.MODULE_MEMBER_IMPORT
    };

    public String[] getModuleListByRole(Set<Role> roles);

    /**
     * use this when uploading a new file.
     * @param moduleName the module name. e.g. booking, finance. this String should only be alphanumeric. No space or special characters
     * @param modulePrimKey the primary key of the module
     * @param filename the filename
     * @param contentType the contentType e.g. image/png
     * @param openField an optional field for anything you need in 250 characters
     * @param base64EncodedFileContent Base64Encoded file that is being uploaded
     * @return Document object with the id that you can put in the respective module table
     * @throws Exception
     */
    public Document add(String moduleName, String modulePrimKey, String filename, String contentType, String openField, byte[] base64EncodedFileContent) throws Exception;

    /**
     * use this when uploading a new file.
     * @param moduleName the module name. e.g. booking, finance. this String should only be alphanumeric. No space or special characters
     * @param modulePrimKey the primary key of the module
     * @param filename the filename
     * @param description description
     * @param contentType the contentType e.g. image/png
     * @param openField an optional field for anything you need in 250 characters
     * @param base64EncodedFileContent Base64Encoded file that is being uploaded
     * @return Document object with the id that you can put in the respective module table
     * @throws Exception
     */
    public Document add(String moduleName, String modulePrimKey, String filename, String description, String contentType, String openField, byte[] base64EncodedFileContent) throws Exception;

    public Document addPayslip(String moduleName, String modulePrimKey, String filename, String description, String contentType, String openField, byte[] base64EncodedFileContent, Date payslipMonth) throws Exception;

    /**
     * use this when updating an existing document record. Will not delete previous file if filename is different. Will overwrite existing file if filename is same
     * @param username the username of the user executing this action
     * @param id the UUID of the document
     * @param filename the filename
     * @param contentType the contentType e.g. image/png
     * @param openField an optional field for anything you need in 250 characters
     * @param base64EncodedFileContent Base64Encoded file that is being uploaded
     * @return Document object with updated information
     * @throws Exception
     * @deprecated
     */
    public Document update(String username, String id, String filename, String contentType, String openField, byte[] base64EncodedFileContent) throws Exception;

    /**
     * use this when updating an existing document record. Will not delete previous file if filename is different. Will overwrite existing file if filename is same
     * @param username the username of the user executing this action
     * @param id the UUID of the document
     * @param filename the filename
     * @param description description
     * @param contentType the contentType e.g. image/png
     * @param openField an optional field for anything you need in 250 characters
     * @param base64EncodedFileContent Base64Encoded file that is being uploaded
     * @return Document object with updated information
     * @throws Exception
     */
    public Document update(String username, String id, String filename, String description, String contentType, String openField, byte[] base64EncodedFileContent) throws Exception;

    /**
     * This method will remove the physical file from file system as well
     * @param id
     * @throws Exception
     */
    public void removeDocument(String id) throws Exception;

    public List<Document> findAllDocumentsByModule(String moduleName, String... modulePrimKeys);

    public List<Document> findAllDocumentsByDateRangeAndModule(Date startDate, Date endDate, int start, int max, String moduleName, String... modulePrimKeys);

    public void deleteAllDocumentsByModule(String moduleName, String modulePrimKey);

    public String getDocument_root_path();

    public List<Document> findDocumentsByModuleAndSearch(String moduleName, String query, Date fromDate, Date toDate);

    /**
     * Returns the full path to a the document file that is stored in the system temp location
     * @return
     */
    public String getDocumentDataFromTemp(Document document) throws Exception;

    /**
     * Returns the Base64 encoded version of the file
     * @param document
     * @return
     * @throws Exception
     */
    public String getDocumentData(Document document) throws Exception;

    byte[] getFileByteArray(String uuid) throws Exception;
}