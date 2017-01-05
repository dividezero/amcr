package com.angkasa.webapp.controller;

import com.angkasa.model.Document;
import com.angkasa.service.DocumentManager;
import com.angkasa.util.PropsUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * User: hazlan
 */
@Controller
@RequestMapping("/documents*")
public class DocumentController extends BaseFormController {

    private DocumentManager documentManager;

    @Autowired
    private PropsUtil propsUtil;

    @Autowired
    public void setDocumentManager(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    public DocumentController() {
        setCancelView("redirect:/mainMenu");
        setSuccessView("uploadDisplay");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    public DocumentUpload showForm() {
        return new DocumentUpload();
    }
    
    @RequestMapping(value = "/checkIfDataExists/{uuid}/ajax=true", method = RequestMethod.GET)
    public @ResponseBody
    Boolean getDataExists(@PathVariable("uuid") String uuid,HttpServletRequest request) throws Exception {
        Document document = documentManager.get(uuid);
        String base64Encoded = documentManager.getDocumentData(document);
        if(StringUtils.isBlank(base64Encoded)){
              return false;  
        }
        return true;
    }

    @RequestMapping(value = "/get/{uuid}", method = RequestMethod.GET)
    public void getFile(@PathVariable("uuid") String uuid, HttpServletResponse response) throws Exception {
        // TODO should check if the user can download this file
        Document document = documentManager.get(uuid);
        String base64Encoded = documentManager.getDocumentData(document);
        if(StringUtils.isBlank(base64Encoded)){
              return;  
        }
        byte[] base64Decoded = Base64.decode(base64Encoded.getBytes());

        try {



            response.setContentType(document.getContentType());
            response.setContentLength(base64Decoded.length); // ermm... we arent allowing big files, so should be ok to cast to int
            // TODO check y content-disposition is not showing in header
            response.setHeader("Content-disposition", "attachment; filename=" + document.getFilename());

            ByteArrayInputStream is = new ByteArrayInputStream(base64Decoded);
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
        } catch (IOException ex) {
            log.error("Error writing file to output stream. File UUID was '" + uuid + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(DocumentUpload fileUpload, BindingResult errors, HttpServletRequest request)
            throws Exception {

        Locale locale = request.getLocale();

        setSuccessView(fileUpload.getSuccessView());
        setFailureView(fileUpload.getFailureView());
        setCancelView(fileUpload.getCancelView());

        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fileUpload, errors);

            if (errors.hasErrors()) {
                return getFailureView();
            }
        }

        // validate a file was entered
        if (fileUpload.getFile().length == 0) {
            saveError(request, getText("errors.required", getText("uploadForm.file", locale), locale));
            return getFailureView();
        }

        Integer fileLimitSize = propsUtil.getFileLimitSize() * 1024 * 1024;

        if (fileUpload.getFile().length > fileLimitSize) {
            saveError(request, getText("maxLengthExceeded", locale));
            return getFailureView();
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");

        Document document = documentManager.add(fileUpload.getModuleName(), fileUpload.getModulePrimKey(), file.getOriginalFilename(), fileUpload.getDescription(), file.getContentType(), fileUpload.getOpenField(), Base64.encode(file.getBytes()));

        String link = request.getContextPath() + "/documents" + "/" + document.getId();
        request.setAttribute("link", link);
        request.setAttribute("fileUpload", fileUpload);
        return getSuccessView();
    }

    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public void deleteFile(@PathVariable("uuid") String uuid, HttpServletResponse response) {
        // TODO should check if the user can delete this file
        try {
            documentManager.removeDocument(uuid);
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * Use to show file base on given moduleName and modulePrimKey
     * 
     * @param moduleName
     * @param modulePrimKey
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/show/{moduleName}/{modulePrimKey}", method = RequestMethod.GET)
    public void showFile(@PathVariable("moduleName") String moduleName, @PathVariable("modulePrimKey") String modulePrimKey, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO should check if the user show this file
        log.debug("moduleName : " + moduleName);
        log.debug("modulePrimKey : " + modulePrimKey);
        List<Document> documents = documentManager.findAllDocumentsByModule(moduleName, modulePrimKey);
        if (documents.size() > 0) {
            Document document = documents.get(0);
            ServletContext sc = getServletContext();

            String base64Encoded = documentManager.getDocumentData(document);
            if(StringUtils.isBlank(base64Encoded)){
              return;  
            }
            byte[] base64Decoded = Base64.decode(base64Encoded.getBytes());

            // Set content type
            response.setContentType(document.getContentType());
            response.setContentLength(base64Decoded.length);

            response.setHeader("Content-disposition", "attachment; filename=" + document.getFilename());

            ByteArrayInputStream is = new ByteArrayInputStream(base64Decoded);
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
