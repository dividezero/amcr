package com.angkasa.dao.hibernate;

import com.angkasa.model.Survey;
import com.angkasa.dao.SurveyDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("surveyDao")
public class SurveyDaoHibernate extends GenericDaoHibernate<Survey, Long> implements SurveyDao {

    public SurveyDaoHibernate() {
        super(Survey.class);
    }
}
