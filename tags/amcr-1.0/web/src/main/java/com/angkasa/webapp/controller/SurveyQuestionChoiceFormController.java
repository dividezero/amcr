package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.SurveyQuestionChoiceManager;
import com.angkasa.model.SurveyQuestionChoice;
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
@RequestMapping("/surveyQuestionChoiceform*")
public class SurveyQuestionChoiceFormController extends BaseFormController {
    private SurveyQuestionChoiceManager surveyQuestionChoiceManager = null;

    @Autowired
    public void setSurveyQuestionChoiceManager(SurveyQuestionChoiceManager surveyQuestionChoiceManager) {
        this.surveyQuestionChoiceManager = surveyQuestionChoiceManager;
    }

    public SurveyQuestionChoiceFormController() {
        setCancelView("redirect:surveyQuestionChoices");
        setSuccessView("redirect:surveyQuestionChoices");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected SurveyQuestionChoice showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return surveyQuestionChoiceManager.get(new Long(id));
        }

        return new SurveyQuestionChoice();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(SurveyQuestionChoice surveyQuestionChoice, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(surveyQuestionChoice, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "surveyQuestionChoiceform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (surveyQuestionChoice.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            surveyQuestionChoiceManager.remove(surveyQuestionChoice.getId());
            saveMessage(request, getText("surveyQuestionChoice.deleted", locale));
        } else {
            surveyQuestionChoiceManager.save(surveyQuestionChoice);
            String key = (isNew) ? "surveyQuestionChoice.added" : "surveyQuestionChoice.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:surveyQuestionChoiceform?id=" + surveyQuestionChoice.getId();
            }
        }

        return success;
    }
}
