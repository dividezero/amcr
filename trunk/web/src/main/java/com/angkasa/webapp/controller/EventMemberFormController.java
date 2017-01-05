package com.angkasa.webapp.controller;

import com.angkasa.model.Event;
import com.angkasa.service.EventManager;
import com.angkasa.util.PropsUtil;
import org.apache.commons.lang.StringUtils;
import com.angkasa.service.EventMemberManager;
import com.angkasa.model.EventMember;
import com.angkasa.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/eventMemberform*")
public class EventMemberFormController extends BaseFormController {
    private EventMemberManager eventMemberManager = null;

    @Autowired
    private PropsUtil propsUtil;

    @Autowired
    private EventManager eventManager;

    @ModelAttribute("genderList")
    public Map<String, String> getGenderList() {
        Map<String, String> map = new HashMap<>();
        map.put("", null);
        map.putAll(propsUtil.getGenderList());
        return map;
    }

    @ModelAttribute("maritalStatusList")
    public Map<String, String> getMaritalStatusList() {
        Map<String, String> map = new HashMap<>();
        map.put("", null);
        map.putAll(propsUtil.getMaritalStatusList());
        return map;
    }

    @ModelAttribute("raceList")
    public Map<String, String> getRaceList() {
        Map<String, String> map = new HashMap<>();
        map.put("", null);
        map.putAll(propsUtil.getRaceList());
        return map;
    }

    @ModelAttribute("designationList")
    public Map<String, String> getDesignationList() {
        Map<String, String> map = new HashMap<>();
        map.put("", null);
        map.putAll(propsUtil.getDesignationList());
        return map;
    }

    @Autowired
    public void setEventMemberManager(EventMemberManager eventMemberManager) {
        this.eventMemberManager = eventMemberManager;
    }

    public EventMemberFormController() {
        setCancelView("redirect:eventMembers");
        setSuccessView("redirect:eventMembers");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected EventMember showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        String eventId = request.getParameter("eventId");

        if (!StringUtils.isBlank(id)) {
            return eventMemberManager.get(new Long(id));
        }

        EventMember eventMember = new EventMember();

        Event event = null;
        if(StringUtils.isNotBlank(eventId)) {
            event = eventManager.get(new Long(eventId));
        }
        if(event == null || event.getId() == null){
            saveError(request, "Event not found.");
        } else {
            eventMember.setEventId(event.getId());
            eventMember.setEvent(event);
        }

        return eventMember;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(EventMember eventMember, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {

        String eventPage = "redirect:/eventform?tab=member&id=" + eventMember.getEventId();
        if (request.getParameter("cancel") != null) {
            return eventPage;
        }

        if (validator != null) { // validator is null during testing
            validator.validate(eventMember, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "eventMemberform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (eventMember.getId() == null);
        String success = eventPage;
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            eventMemberManager.remove(eventMember.getId());
            saveMessage(request, getText("eventMember.deleted", locale));
        } else {
            eventMemberManager.save(eventMember);
            String key = (isNew) ? "eventMember.added" : "eventMember.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:/eventMemberform?id=" + eventMember.getId();
            }
        }

        return success;
    }
}
