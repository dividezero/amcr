package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.ContactPerson;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContactPersonDaoTest extends BaseDaoTestCase {
    @Autowired
    private ContactPersonDao contactPersonDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveContactPerson() {
        ContactPerson contactPerson = new ContactPerson();

        // enter all required fields
        contactPerson.setDescription("DzYfKcFiDsIwYaXrNxCwGkPvNcJsOhKgNgWjDzYuMdWdAsMpKgFnAkNaYzVnJkKwHrUcJzTqScMlOlMjMwMpBnNvUjGdDqVdNyVoNoQbJlVvPzBeOpTbTyCoGcKlKsCkYtQuJrZxHmAfZdFnGaYeSnOfZoJlHyOeAyHcVnMnCmMiFiXlEwYvQoAhPcUbPsPkQwQlYpGl");
        contactPerson.setEmail("OmGsKdExPeAjNaQvJiYn");
        contactPerson.setName("ZgWvSsKlFyGjQpLdUeUhHbCjAbIkZcPwIrBbFmAgDwXtFdSfEh");
        contactPerson.setWorkPhoneNo("TlUpUwBnKxAc");

        log.debug("adding contactPerson...");
        contactPerson = contactPersonDao.save(contactPerson);

        contactPerson = contactPersonDao.get(contactPerson.getId());

        assertNotNull(contactPerson.getId());

        log.debug("removing contactPerson...");

        contactPersonDao.remove(contactPerson.getId());

        // should throw DataAccessException 
        contactPersonDao.get(contactPerson.getId());
    }
}