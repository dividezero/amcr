package com.angkasa.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.angkasa.dao.ContactPersonDao;
import com.angkasa.model.ContactPerson;

@Repository("contactPersonDao")
public class ContactPersonDaoHibernate extends GenericDaoHibernate<ContactPerson, Long> implements ContactPersonDao {

	public ContactPersonDaoHibernate() {
		super(ContactPerson.class);
	}

	@Override
	public List<ContactPerson> getByCoopId(Long coopId) {
		Criteria criteria = getSession().createCriteria(ContactPerson.class);
		if (coopId != null) {
			criteria.add(Restrictions.eq("coopId", coopId));

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();

		} else {
			// return empty list
			return new ArrayList<>();
		}
	}
}
