package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.Employer;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployerDaoTest extends BaseDaoTestCase {
    @Autowired
    private EmployerDao employerDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveEmployer() {
        Employer employer = new Employer();

        // enter all required fields
        employer.setEmployerCode("JwJaYaNdSb");
        employer.setName("ThXoObKnNvIfQyKaGvVzLqSwBrSjMzHaBoUjVzOgBxWeAlEpHiMdZaHkRsPpTyOoKgJsGwNnLmPlAjKrKcJmHpUiPhXlTyNjMnNnWvVwGwGgDsBuQiVbShJkNnZhDiTpTqUyWoYbLiWvHdAjNaDmEbQjItOyPnByQoMlHuDwCcSqFxPtMcCrPlVhQjHsIpRoKwHgBkAa");

        log.debug("adding employer...");
        employer = employerDao.save(employer);

        employer = employerDao.get(employer.getId());

        assertNotNull(employer.getId());

        log.debug("removing employer...");

        employerDao.remove(employer.getId());

        // should throw DataAccessException 
        employerDao.get(employer.getId());
    }
}