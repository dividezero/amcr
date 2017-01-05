package com.angkasa.dao.hibernate;

import com.angkasa.model.EmployerImport;
import com.angkasa.dao.EmployerImportDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("employerImportDao")
public class EmployerImportDaoHibernate extends GenericDaoHibernate<EmployerImport, Long> implements EmployerImportDao {

    public EmployerImportDaoHibernate() {
        super(EmployerImport.class);
    }
}
