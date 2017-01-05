package com.angkasa.webapp.controller;

import com.angkasa.model.EventMember;
import com.angkasa.service.EventMemberManager;
import com.angkasa.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import com.angkasa.service.EventManager;
import com.angkasa.model.Event;
import com.angkasa.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/eventform*")
public class EventFormController extends BaseFormController {
    private EventManager eventManager = null;

    @Autowired
    private EventMemberManager eventMemberManager;

    @Autowired
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public EventFormController() {
        setCancelView("redirect:events");
        setSuccessView("redirect:events");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request)
            throws Exception {
        ModelAndView model = new ModelAndView("eventform");

        String id = request.getParameter("id");

        Event event = new Event();

        if (!StringUtils.isBlank(id)) {
            event = eventManager.get(new Long(id));

            List<EventMember> eventMemberList = eventMemberManager.getByEventId(new Long(id));
            model.addObject("eventMemberList", eventMemberList);
        }
        model.addObject("event", event);

        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Event event, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(event, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "eventform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (event.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            eventManager.remove(event.getId());
            saveMessage(request, getText("event.deleted", locale));
        } else if (request.getParameter("randomdraw") != null) {
            int numDraws = 1;
            event = eventManager.get(event.getId());
            List<EventMember> drawResults = eventMemberManager.getRandomDrawByEventId(event.getId(), numDraws);
            for (EventMember result : drawResults) {
                event.setDrawResult(event.getDrawResult() + "[" + result.getIcNumber() + "][" + result.getName() + "][" + result.getEmail() + "][" + result.getPhoneNo() + "]\n");
            }
            eventManager.save(event);

            success = "redirect:/eventform?tab=member&id=" + event.getId();
        } else {
            eventManager.save(event);
            String key = (isNew) ? "event.added" : "event.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:/eventform?id=" + event.getId();
            }
        }

        return success;
    }
}
