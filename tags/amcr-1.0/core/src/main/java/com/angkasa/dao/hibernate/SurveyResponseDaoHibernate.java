package com.angkasa.dao.hibernate;

import com.angkasa.model.SurveyResponse;
import com.angkasa.dao.SurveyResponseDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("surveyResponseDao")
public class SurveyResponseDaoHibernate extends GenericDaoHibernate<SurveyResponse, Long> implements SurveyResponseDao {

    public SurveyResponseDaoHibernate() {
        super(SurveyResponse.class);
    }
}
