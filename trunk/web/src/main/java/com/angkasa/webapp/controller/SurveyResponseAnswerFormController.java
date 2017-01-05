package com.angkasa.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.angkasa.service.SurveyResponseAnswerManager;
import com.angkasa.model.SurveyResponseAnswer;
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
@RequestMapping("/surveyResponseAnswerform*")
public class SurveyResponseAnswerFormController extends BaseFormController {
    private SurveyResponseAnswerManager surveyResponseAnswerManager = null;

    @Autowired
    public void setSurveyResponseAnswerManager(SurveyResponseAnswerManager surveyResponseAnswerManager) {
        this.surveyResponseAnswerManager = surveyResponseAnswerManager;
    }

    public SurveyResponseAnswerFormController() {
        setCancelView("redirect:surveyResponseAnswers");
        setSuccessView("redirect:surveyResponseAnswers");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected SurveyResponseAnswer showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return surveyResponseAnswerManager.get(new Long(id));
        }

        return new SurveyResponseAnswer();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(SurveyResponseAnswer surveyResponseAnswer, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(surveyResponseAnswer, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "surveyResponseAnswerform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (surveyResponseAnswer.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            surveyResponseAnswerManager.remove(surveyResponseAnswer.getId());
            saveMessage(request, getText("surveyResponseAnswer.deleted", locale));
        } else {
            surveyResponseAnswerManager.save(surveyResponseAnswer);
            String key = (isNew) ? "surveyResponseAnswer.added" : "surveyResponseAnswer.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:surveyResponseAnswerform?id=" + surveyResponseAnswer.getId();
            }
        }

        return success;
    }
}
