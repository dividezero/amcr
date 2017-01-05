package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.SurveyResponseManager;
import com.angkasa.model.SurveyResponse;
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
import java.util.Locale;

@Controller
@RequestMapping("/surveyResponseform*")
public class SurveyResponseFormController extends BaseFormController {
    private SurveyResponseManager surveyResponseManager = null;

    @Autowired
    public void setSurveyResponseManager(SurveyResponseManager surveyResponseManager) {
        this.surveyResponseManager = surveyResponseManager;
    }

    public SurveyResponseFormController() {
        setCancelView("redirect:surveyResponses");
        setSuccessView("redirect:surveyResponses");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected SurveyResponse showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return surveyResponseManager.get(new Long(id));
        }

        return new SurveyResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(SurveyResponse surveyResponse, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(surveyResponse, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "surveyResponseform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (surveyResponse.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            surveyResponseManager.remove(surveyResponse.getId());
            saveMessage(request, getText("surveyResponse.deleted", locale));
        } else {
            surveyResponseManager.save(surveyResponse);
            String key = (isNew) ? "surveyResponse.added" : "surveyResponse.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:surveyResponseform?id=" + surveyResponse.getId();
            }
        }

        return success;
    }
}
