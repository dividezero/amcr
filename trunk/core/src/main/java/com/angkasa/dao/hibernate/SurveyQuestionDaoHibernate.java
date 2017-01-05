package com.angkasa.dao.hibernate;

import com.angkasa.model.SurveyQuestion;
import com.angkasa.dao.SurveyQuestionDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("surveyQuestionDao")
public class SurveyQuestionDaoHibernate extends GenericDaoHibernate<SurveyQuestion, Long> implements SurveyQuestionDao {

    public SurveyQuestionDaoHibernate() {
        super(SurveyQuestion.class);
    }
}
