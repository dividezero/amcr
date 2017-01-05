package com.angkasa.dao.hibernate;

import com.angkasa.model.CoopImport;
import com.angkasa.dao.CoopImportDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("coopImportDao")
public class CoopImportDaoHibernate extends GenericDaoHibernate<CoopImport, Long> implements CoopImportDao {

    public CoopImportDaoHibernate() {
        super(CoopImport.class);
    }
}
