package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.DocumentManager;
import com.angkasa.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * my.com.microlink.webapp.controller.DocumentStoreController
 *
 * Date: April 17, 2013 Copyright (c) 2013, All rights reserved.
 *
 * Controller class to view document store
 *
 * @author Rugemarila J.N
 */
@Controller
@RequestMapping("/documentStore*")
public class DocumentStoreController extends BaseFormController {

    private DocumentManager documentManager;

    @Autowired
    public void setDocumentManager(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "toDate") String toDateStr,
            @RequestParam(required = false, value = "fromDate") String fromDateStr, HttpServletRequest request)
            throws Exception {
        Model model = new ExtendedModelMap();

        String moduleName = request.getParameter("moduleName");
        
        String query = request.getParameter("q");
        if (StringUtils.isBlank(query) || query.trim().equals(",")) {
            query = "";
        }
        Date fromDate = null;
        if (StringUtils.isNotBlank(fromDateStr) && !fromDateStr.trim().equals(",") && fromDateStr.split(",").length > 0) {
            try {
                fromDate = DateUtil.convertStringToDate(fromDateStr.split(",")[0].trim());
            } catch (Exception ex) {
            }
        }


        Date toDate = null;
        if (StringUtils.isNotBlank(toDateStr) && !toDateStr.trim().equals(",") && toDateStr.split(",").length > 0) {
            try {
                toDate = DateUtil.convertStringToDate(toDateStr.split(",")[0].trim());
            } catch (Exception ex) {
            }
        }
        if(fromDate==null && toDate==null && StringUtils.isEmpty(query)){
            model.addAttribute("documentList", documentManager.findAllDocumentsByModule(moduleName));
        }
        else {
            try {
                model.addAttribute("documentList", documentManager.findDocumentsByModuleAndSearch(moduleName, query, fromDate, toDate));
            } catch (SearchException se) {
                model.addAttribute("searchError", se.getMessage());
                model.addAttribute("documentList", documentManager.findAllDocumentsByModule(moduleName));
            }
        }
        model.addAttribute("documentModules", documentManager.getModuleListByRole(getCurrentUser().getRoles()));
		return new ModelAndView("documentStore", model.asMap());
	}
}
