package com.angkasa.dao.hibernate;

import com.angkasa.model.DocumentData;
import com.angkasa.dao.DocumentDataDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("documentDataDao")
public class DocumentDataDaoHibernate extends GenericDaoHibernate<DocumentData, Long> implements DocumentDataDao {

    public DocumentDataDaoHibernate() {
        super(DocumentData.class);
    }


    @Override
    public DocumentData getByDocumentId(String docId) {
        try {
            Session sess = getSession();
            Criteria criteria = sess.createCriteria(DocumentData.class);
            criteria.add(Restrictions.eq("document.id", docId));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            List<DocumentData> results = (List<DocumentData>) criteria.list();
            if (results != null && !results.isEmpty()) {
                DocumentData obj = results.get(0);//there should only be one
                sess.evict(obj);
                return obj;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
}
