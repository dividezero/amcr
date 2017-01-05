package com.angkasa.dao.hibernate;

import com.angkasa.model.SurveyQuestionChoice;
import com.angkasa.dao.SurveyQuestionChoiceDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("surveyQuestionChoiceDao")
public class SurveyQuestionChoiceDaoHibernate extends GenericDaoHibernate<SurveyQuestionChoice, Long> implements SurveyQuestionChoiceDao {

    public SurveyQuestionChoiceDaoHibernate() {
        super(SurveyQuestionChoice.class);
    }
}
