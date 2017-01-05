package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.SurveyQuestionManager;
import com.angkasa.model.SurveyQuestion;
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
@RequestMapping("/surveyQuestionform*")
public class SurveyQuestionFormController extends BaseFormController {
    private SurveyQuestionManager surveyQuestionManager = null;

    @Autowired
    public void setSurveyQuestionManager(SurveyQuestionManager surveyQuestionManager) {
        this.surveyQuestionManager = surveyQuestionManager;
    }

    public SurveyQuestionFormController() {
        setCancelView("redirect:surveyQuestions");
        setSuccessView("redirect:surveyQuestions");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected SurveyQuestion showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return surveyQuestionManager.get(new Long(id));
        }

        return new SurveyQuestion();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(SurveyQuestion surveyQuestion, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(surveyQuestion, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "surveyQuestionform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (surveyQuestion.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            surveyQuestionManager.remove(surveyQuestion.getId());
            saveMessage(request, getText("surveyQuestion.deleted", locale));
        } else {
            surveyQuestionManager.save(surveyQuestion);
            String key = (isNew) ? "surveyQuestion.added" : "surveyQuestion.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:surveyQuestionform?id=" + surveyQuestion.getId();
            }
        }

        return success;
    }
}
