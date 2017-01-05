package com.angkasa.dao.hibernate;

import com.angkasa.model.SurveyResponseAnswer;
import com.angkasa.dao.SurveyResponseAnswerDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("surveyResponseAnswerDao")
public class SurveyResponseAnswerDaoHibernate extends GenericDaoHibernate<SurveyResponseAnswer, Long> implements SurveyResponseAnswerDao {

    public SurveyResponseAnswerDaoHibernate() {
        super(SurveyResponseAnswer.class);
    }
}
