package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.SurveyManager;
import com.angkasa.model.Survey;
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
@RequestMapping("/surveyform*")
public class SurveyFormController extends BaseFormController {
    private SurveyManager surveyManager = null;

    @Autowired
    public void setSurveyManager(SurveyManager surveyManager) {
        this.surveyManager = surveyManager;
    }

    public SurveyFormController() {
        setCancelView("redirect:surveys");
        setSuccessView("redirect:surveys");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Survey showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return surveyManager.get(new Long(id));
        }

        return new Survey();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Survey survey, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(survey, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "surveyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (survey.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            surveyManager.remove(survey.getId());
            saveMessage(request, getText("survey.deleted", locale));
        } else {
            surveyManager.save(survey);
            String key = (isNew) ? "survey.added" : "survey.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:surveyform?id=" + survey.getId();
            }
        }

        return success;
    }
}
