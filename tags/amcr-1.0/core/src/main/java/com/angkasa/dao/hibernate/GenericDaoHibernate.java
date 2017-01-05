package com.angkasa.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.angkasa.Constants;
import com.angkasa.dao.GenericDao;
import com.angkasa.dao.SearchException;
import com.angkasa.model.BaseObject;
import com.angkasa.util.DateUtil;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.angkasa.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.angkasa.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 *      Updated by jgarcia: update hibernate3 to hibernate4
 * @author jgarcia (update: added full text search + reindexing)
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;
    @Resource
    private SessionFactory sessionFactory;
    private Analyzer defaultAnalyzer;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
        defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
//        Session sess = getSession();
//        return sess.createCriteria(persistentClass).list();
        return getAllLatestFirst();
    }
    
    /**
     * {@inheritDoc}
     * @return 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAllLatestFirst(){
        Session sess = getSession();
        Criteria criteria = sess.createCriteria(persistentClass);
        if(BaseObject.class.isAssignableFrom(persistentClass))
            criteria.addOrder(Order.desc("createdTime"));
        return criteria.list();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> search(String searchTerm) throws SearchException {
        Session sess = getSession();
        FullTextSession txtSession = Search.getFullTextSession(sess);

        org.apache.lucene.search.Query qry;
        try {
            qry = HibernateSearchTools.generateQuery(searchTerm, this.persistentClass, sess, defaultAnalyzer);
        } catch (ParseException ex) {
            throw new SearchException(ex);
        }
        org.hibernate.search.FullTextQuery hibQuery = txtSession.createFullTextQuery(qry,
                this.persistentClass);
        return hibQuery.list();
    }

    /**
     * {@inheritDoc}
     * @param searchTerm
     * @param fromDate
     * @param toDate
     * @return 
     */
    @Override
    public List<T> search(String searchTerm, Date fromDate, Date toDate) throws SearchException {
        Session sess = getSession();
        FullTextSession txtSession = Search.getFullTextSession(sess);

        List<BaseObject> result;
        org.apache.lucene.search.Query qry;
        try {
            qry = HibernateSearchTools.generateQuery(searchTerm, this.persistentClass, sess, defaultAnalyzer);
        } catch (ParseException ex) {
            throw new SearchException(ex);
        }

        // if both dates are available...
        Criteria dateCriteria = getSession().createCriteria(persistentClass);
        if(fromDate != null && toDate != null) {
            dateCriteria.add(Restrictions.and(Restrictions.ge("createdTime", fromDate), Restrictions.le("createdTime", toDate)));

        }else if(fromDate != null) {
            dateCriteria.add(Restrictions.ge("createdTime", fromDate));
        }else if(toDate != null) {
            dateCriteria.add(Restrictions.le("createdTime", toDate));
        }

        org.hibernate.search.FullTextQuery hibQuery = txtSession.createFullTextQuery(qry,this.persistentClass).setCriteriaQuery(dateCriteria);
        result = hibQuery.list();

        return (List<T>) result;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
Session sess = getSession();
        
        /*
        Boolean isNew = true;
        if(sess.contains(object)){
            Object id = sess.getIdentifier(object);

            if (id != null) {
                isNew = false;
            }
        
        }*/
        
        String currentUserInContext = getUserInCurrentContext();

        if (object instanceof BaseObject) {
            BaseObject base = (BaseObject) object;

            Boolean isNew = false;
			if (base.getPrimaryKey() == null
					|| ((base.getCreatedBy() == null || base.getCreatedBy()
							.equals("")) && base.getCreatedTime() == null)){
                isNew = true;
			}
            
            if (isNew) {
                base.setCreatedBy(currentUserInContext);
                base.setCreatedTime(DateUtil.getDateToday());

            } else {

                base.setModifiedBy(currentUserInContext);
                base.setModifiedTime(DateUtil.getDateToday());
            }
        }

        return (T) sess.merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        Session sess = getSession();
        sess.delete(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        sess.delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Session sess = getSession();
        Query namedQuery = sess.getNamedQuery(queryName);

        for (String s : queryParams.keySet()) {
            namedQuery.setParameter(s, queryParams.get(s));
        }

        return namedQuery.list();
    }

    /**
     * {@inheritDoc}
     */
    public void reindex() {
        HibernateSearchTools.reindex(persistentClass, getSessionFactory().getCurrentSession());
    }


    /**
     * {@inheritDoc}
     */
    public void reindexAll(boolean async) {
        HibernateSearchTools.reindexAll(async, getSessionFactory().getCurrentSession());
    }
    
    protected String getUserInCurrentContext(){
        if(SecurityContextHolder.getContext()==null || 
           SecurityContextHolder.getContext().getAuthentication()==null ||
           SecurityContextHolder.getContext().getAuthentication().getName()==null)
            return Constants.DEFAULT_USER;
        else{
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
    }
}
