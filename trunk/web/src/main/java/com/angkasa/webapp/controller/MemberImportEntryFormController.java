package com.angkasa.webapp.controller;

import com.angkasa.model.Member;
import com.angkasa.model.MemberImport;
import com.angkasa.service.MemberImportManager;
import com.angkasa.service.MemberManager;
import com.angkasa.util.PropsUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/memberImportEntryform*")
public class MemberImportEntryFormController extends BaseFormController {
    private MemberImportManager memberImportManager = null;

    @Autowired
    public void setMemberImportManager(MemberImportManager memberImportManager) {
        this.memberImportManager = memberImportManager;
    }

    @Autowired
    MemberManager memberManager;

    @Autowired
    PropsUtil propsUtil;

    @ModelAttribute("genderList")
    public Map<String, String> getGenderList() {
        Map<String, String> result = new HashMap<>();
        result.put(null, "N/A");
        result.putAll(propsUtil.getGenderList());
        return result;
    }

    @ModelAttribute("maritalStatusList")
    public Map<String, String> getMaritalStatusList() {
        Map<String, String> result = new HashMap<>();
        result.put(null, "N/A");
        result.putAll(propsUtil.getMaritalStatusList());
        return result;
    }

    @ModelAttribute("raceList")
    public Map<String, String> getRaceList() {
        Map<String, String> result = new HashMap<>();
        result.put(null, "N/A");
        result.putAll(propsUtil.getRaceList());
        return result;
    }

    @ModelAttribute("languageList")
    public Map<String, String> getLanguageList() {
        Map<String, String> result = new HashMap<>();
        result.put(null, "N/A");
        result.putAll(propsUtil.getLanguageList());
        return result;
    }

    public MemberImportEntryFormController() {
        setCancelView("redirect:memberImportEntryform");
        setSuccessView("redirect:memberImportEntryform");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showEntry(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView("memberImportEntryform");
        String memberImportId = request.getParameter("memberImportId");
        String importId = request.getParameter("importId");

        MemberImport memberImport = memberImportManager.get(new Long(memberImportId));

        for (Member member : memberImport.getMemberList()) {
            if (StringUtils.equals(member.getImportId(), importId)) {
                member.setMemberImportId(memberImportId);
                model.addObject("member", member);
                break;
            }
        }

        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmitEntry(Member member, BindingResult errors, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String returnUrl = "memberImportEntryform";

        if (request.getParameter("cancel") != null) {
            return returnUrl;
        }

        log.debug("entering 'onSubmit' method...");

        String success = getSuccessView();
        Locale locale = request.getLocale();

        MemberImport memberImport = memberImportManager.get(new Long(member.getMemberImportId()));

        if (request.getParameter("delete") != null) {
            List<Member> memList = memberImport.getMemberList();
            List<Member> newList = new ArrayList<>();
            for (Member listMem : memList) {
                if (!StringUtils.equals(listMem.getImportId(), member.getImportId())) {
                    newList.add(listMem);
                    break;
                }
            }
            memberImport.setMemberList(newList);
            saveMessage(request, getText("memberImport.deleted", locale));
        } else {
            boolean useCoopCode = propsUtil.isUseCoopCodeInMemberImport();
            if (request.getParameter("validate") != null) {
                member = memberManager.validateMemberImport(member, useCoopCode);
            }
            if (request.getParameter("saveToMembers") != null) {
                member = memberManager.processMemberImport(member, useCoopCode);
            }

            List<Member> memList = memberImport.getMemberList();
            List<Member> result = new ArrayList<Member>();
            for (Member listMem : memList) {
                if (StringUtils.equals(listMem.getImportId(), member.getImportId())) {
                    result.add(member);

                } else {
                    result.add(listMem);
                }
            }
            memberImport.setMemberList(result);

            memberImportManager.save(memberImport);

            String key = "memberImport.updated";
            saveMessage(request, getText(key, locale));

        }

        return returnUrl;
    }
}
