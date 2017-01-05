package com.angkasa.webapp.controller;

import com.angkasa.Constants;
import com.angkasa.dao.SearchException;
import com.angkasa.model.Coop;
import com.angkasa.model.Document;
import com.angkasa.service.CoopManager;
import com.angkasa.service.DocumentManager;
import com.angkasa.service.MemberManager;
import com.angkasa.model.Member;

import com.angkasa.util.DateUtil;
import com.angkasa.util.TsvFileUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/members*")
public class MemberController extends BaseFormController {
    private MemberManager memberManager;

    @Autowired
    private DocumentManager documentManager;

    @Autowired
    private CoopManager coopManager;

    @Autowired
    public void setMemberManager(MemberManager memberManager) {
        this.memberManager = memberManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query, HttpServletRequest request)
            throws Exception {
        Model model = new ExtendedModelMap();
        List<Member> memList = new ArrayList<Member>();
        Coop coop = null;
        try {
            if (getCurrentUser() != null && getCurrentUser().isCoopUser()) {
                coop = coopManager.getByUserId(getCurrentUser().getId());
                log.debug("user coop id:" + coop.getId());
                memList = memberManager.searchByCoop(query, new Long(coop.getId()));
            } else if (getCurrentUser() != null && getCurrentUser().hasRole(Constants.ADMIN_ROLE)
                    && StringUtils.isNotBlank(request.getParameter("coopId"))) {
                coop = coopManager.get(new Long(request.getParameter("coopId")));
                log.debug("selected coop id :" + request.getParameter("coopId"));
                memList = memberManager.searchByCoop(query, new Long(request.getParameter("coopId")));
            } else {
                memList = memberManager.search(query, Member.class);
            }

        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            memList = memberManager.getAll();
        }

        model.addAttribute("coop", coop);
        model.addAttribute(memList);

        return model;
    }

    @RequestMapping(value = "/exporttsv", method = RequestMethod.GET)
    public void getFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO should check if the user can download this file

        String query = request.getParameter("q");

        List<Member> memList = new ArrayList<Member>();
        Coop coop = null;
        try {
            if (getCurrentUser() != null && getCurrentUser().isCoopUser()) {
                coop = coopManager.getByUserId(getCurrentUser().getId());
                log.debug("user coop id:" + coop.getId());
                memList = memberManager.searchByCoop(query, new Long(coop.getId()));
            } else if (getCurrentUser() != null && getCurrentUser().hasRole(Constants.ADMIN_ROLE)
                    && StringUtils.isNotBlank(request.getParameter("coopId"))) {
                coop = coopManager.get(new Long(request.getParameter("coopId")));
                log.debug("selected coop id :" + request.getParameter("coopId"));
                memList = memberManager.searchByCoop(query, new Long(request.getParameter("coopId")));
            } else {
                memList = memberManager.search(query, Member.class);
            }

        } catch (SearchException se) {
            memList = memberManager.getAll();
        }

        String tsvString = TsvFileUtil.generateTsvStringFromMemberList(memList);


        //Document document = documentManager.get(uuid);
//        String base64Encoded = documentManager.getDocumentData(document);
//        if(StringUtils.isBlank(base64Encoded)){
//            return;
//        }
//        byte[] base64Decoded = Base64.decode(base64Encoded.getBytes());
        byte[] base64Decoded = tsvString.getBytes(Charset.forName("UTF-8"));
        String fileName = "Members-" + DateUtil.convertDateToString(DateUtil.getDateToday()) + ".tsv";
        if(coop!=null){
            fileName = coop.getAmcrCode()+"-"+fileName;
        }

        try {
            response.setContentType("application/txt");
            response.setContentLength(base64Decoded.length);
            // TODO check y content-disposition is not showing in header
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);

            ByteArrayInputStream is = new ByteArrayInputStream(base64Decoded);
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
        } catch (IOException ex) {
            log.error("Error writing file to output stream. File name was '" + fileName + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

}
