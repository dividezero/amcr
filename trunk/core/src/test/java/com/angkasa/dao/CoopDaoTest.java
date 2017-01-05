package com.angkasa.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.angkasa.model.Coop;

public class CoopDaoTest extends BaseDaoTestCase {
	@Autowired
	private CoopDao coopDao;

	@Test(expected = DataAccessException.class)
	public void testAddAndRemoveCoop() {
		Coop coop = new Coop();

		// enter all required fields
		coop.setCoopCode("WzL");
		coop.setName("CuTaCbQoFdReFeOdKlHjJfUoAsJgRzRhKcFjIzWoUiKgVxDgWi");
		coop.setPhoneNo("NgUiVaBbDoBg");

		log.debug("adding coop...");
		coop = coopDao.save(coop);

		coop = coopDao.get(coop.getId());

		assertNotNull(coop.getId());

		log.debug("removing coop...");

		coopDao.remove(coop.getId());

		// should throw DataAccessException
		coopDao.get(coop.getId());
	}
}