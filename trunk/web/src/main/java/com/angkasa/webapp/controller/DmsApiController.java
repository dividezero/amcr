package com.angkasa.webapp.controller;

import com.angkasa.Constants;
import com.angkasa.dao.SearchException;
import com.angkasa.model.Coop;
import com.angkasa.model.DmsMemberUpdate;
import com.angkasa.model.Member;
import com.angkasa.model.User;
import com.angkasa.service.*;
import com.angkasa.util.DateUtil;
import com.angkasa.util.JsonUtil;
import com.angkasa.util.PropsUtil;
import com.angkasa.util.TsvFileUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dmsapi*")
public class DmsApiController extends BaseFormController {
    @Autowired
    private MemberManager memberManager;

    @Autowired
    private DmsMemberUpdateManager dmsMemberUpdateManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private PropsUtil propsUtil;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request)
            throws Exception {
        ModelAndView modelAndView = new ModelAndView("dmsApi");
        if (propsUtil.isEnableDmsApi()) {
            modelAndView.addObject("jsonDisplay", "DMS API is enabled.");

        } else {
            modelAndView.addObject("jsonDisplay", "DMS API is disabled.");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/{username}/{password}/member/{icNumber}", method = RequestMethod.GET)
    public ModelAndView getMemberByIc(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("icNumber") String icNumber, HttpServletRequest request)
            throws Exception {
        ModelAndView modelAndView = new ModelAndView("dmsApi");

        if (propsUtil.isEnableDmsApi()) {
            User user = userManager.getByLogin(username, password);
            if (user != null && user.isDmsUser()) {
                Member member = memberManager.getByIcNumber(icNumber);
                String jsonDisplay = "Member not found.";

                if(member!=null && member.getId()!=null){
                    jsonDisplay = JsonUtil.convertMember(member);
                }

                modelAndView.addObject("jsonDisplay", jsonDisplay);

            } else {
                modelAndView.addObject("jsonDisplay", "Login failed.");
            }

        } else {
            modelAndView.addObject("jsonDisplay", "DMS API is disabled.");
        }


        return modelAndView;
    }

    @RequestMapping(value = "/{username}/{password}/update/{icNumber}/{phoneNum}/{status}", method = RequestMethod.GET)
    public ModelAndView updateMemberByIc(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("icNumber") String icNumber, @PathVariable("phoneNum") String phoneNum, @PathVariable("status") String status, HttpServletRequest request)
            throws Exception {
        ModelAndView modelAndView = new ModelAndView("dmsApi");

        if (propsUtil.isEnableDmsApi()) {
            User user = userManager.getByLogin(username, password);
            if (user != null && user.isDmsUser()) {
                //TODO format error checking


                // save DmsMemberUpdate
                DmsMemberUpdate dmsMemberUpdate = dmsMemberUpdateManager.saveNewDmsMemberUpdate(icNumber, phoneNum, status);
                String dmsMemberUpdateJson = JsonUtil.convertDmsMemberUpdate(dmsMemberUpdate);
                modelAndView.addObject("jsonDisplay", dmsMemberUpdateJson);

            } else {
                modelAndView.addObject("jsonDisplay", "Login failed.");
            }


        } else {
            modelAndView.addObject("jsonDisplay", "DMS API is disabled.");
        }

        return modelAndView;
    }

}
