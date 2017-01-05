package com.angkasa.dao.hibernate;

import com.angkasa.dao.DocumentDao;
import com.angkasa.dao.SearchException;
import com.angkasa.model.Document;
import com.angkasa.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("documentDao")
public class DocumentDaoHibernate extends GenericDaoHibernate<Document, String> implements DocumentDao {


    public DocumentDaoHibernate() {
        super(Document.class);
    }

    @Override
    public List<Document> findByModule(String moduleName, String... modulePrimKeys) {
        return findByDateRangeModule(null, null, ALL_POS, ALL_POS, moduleName, modulePrimKeys);
    }

    @Override
    public List<Document> findByDateRangeModule(Date startDate, Date endDate, int start, int max, String moduleName, String... modulePrimKeys) {
        Criteria criteria = getCriteria();

        criteria.add(Restrictions.eq("moduleName", moduleName));

        if (modulePrimKeys != null && modulePrimKeys.length > 0) {
            criteria.add(Restrictions.in("modulePrimKey", modulePrimKeys));
        }

        criteria.addOrder(Order.asc("createdTime"));

        if (startDate != null)
            startDate = DateUtil.getDayBegin(startDate);
        if (endDate != null)
            endDate = DateUtil.getDayEnd(endDate);

        if(startDate != null && endDate != null) {
            criteria.add(Restrictions.between("createdTime", startDate, endDate));
        }else if(startDate != null) {
            criteria.add(Restrictions.ge("createdTime", startDate));
        }else if(endDate != null) {
            criteria.add(Restrictions.le("createdTime", endDate));
        }

        if(max != ALL_POS) {
            criteria.setMaxResults(max);
        }

        if(start != ALL_POS) {
            criteria.setFirstResult(start);
        }

        return criteria.list();
    }

    @Override
    public List<Document> search(String searchTerm, Date fromDate, Date toDate) throws SearchException {
        Criteria criteria = getSession().createCriteria(Document.class);
        if (StringUtils.isNotBlank(searchTerm)) {
            Conjunction andClause = Restrictions.conjunction();

            criteria.add(andClause);//ensures that the criteria uses OR instead of AND
            criteria.addOrder(Order.desc("createdTime"));


            String[] terms = searchTerm.split(",");
            if (terms != null && terms.length > 0) {
                for (int i = 0; i < terms.length; i++) {
                    Disjunction orClause = Restrictions.disjunction();
                    String trimmedTerm = terms[i].trim();
                    if (StringUtils.isNotBlank(trimmedTerm)) {
                        String term = new StringBuilder("%")
                                .append(trimmedTerm).append("%").toString();

                        orClause.add(Restrictions.ilike("moduleName", term));//ignores case
                        orClause.add(Restrictions.ilike("modulePrimKey", term));
                        orClause.add(Restrictions.ilike("createdBy", term));
                        orClause.add(Restrictions.ilike("fileRelativePath", term));
                        andClause.add(orClause);
                    }
                }
            }
        }

        if (fromDate != null)
            fromDate = DateUtil.getDayBegin(fromDate);
        if (toDate != null)
            toDate = DateUtil.getDayEnd(toDate);

        if(fromDate != null && toDate != null) {
            criteria.add(Restrictions.between("createdTime", fromDate, toDate));
        }else if(fromDate != null) {
            criteria.add(Restrictions.ge("createdTime", fromDate));
        }else if(toDate != null) {
            criteria.add(Restrictions.le("createdTime", toDate));
        }

        return criteria.list();
    }

    private Criteria getCriteria() {
        return getSession().createCriteria(Document.class);
    }
}
