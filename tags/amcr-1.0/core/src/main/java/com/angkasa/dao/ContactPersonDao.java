package com.angkasa.dao;

import java.util.List;

import com.angkasa.dao.GenericDao;
import com.angkasa.model.ContactPerson;

/**
 * An interface that provides a data management interface to the ContactPerson table.
 */
public interface ContactPersonDao extends GenericDao<ContactPerson, Long> {

	List<ContactPerson> getByCoopId(Long coopId);
}