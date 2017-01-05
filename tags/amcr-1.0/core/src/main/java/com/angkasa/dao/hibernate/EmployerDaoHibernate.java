package com.angkasa.dao.hibernate;

import com.angkasa.model.Employer;
import com.angkasa.dao.EmployerDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("employerDao")
public class EmployerDaoHibernate extends GenericDaoHibernate<Employer, Long> implements EmployerDao {

    public EmployerDaoHibernate() {
        super(Employer.class);
    }

    @Override
    public boolean employerCodeExists(String empCode) {
        Employer result = getByEmployerCode(empCode);

        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public Employer getByEmployerCode(String empCode) {
        Criteria criteria = getSession().createCriteria(Employer.class);
        criteria.add(Restrictions.eq("employerCode", empCode));

        List<Employer> result = criteria.list();

        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public List<Employer> processEmployerImport(List<Employer> employerList) {
        List<Employer> result = new ArrayList<>();
        for (Employer employer : employerList) {
            if (employerCodeExists(employer.getEmployerCode())) {

                employer.setImportStatus(Employer.STATUS_FAILED);
                employer.addImportRemark(REMARK_EMPLOYER_CODE_EXISTS);
            } else {
                Employer savedEmp = this.save(employer);

                employer = savedEmp;
                employer.setImportStatus(Employer.STATUS_SUCCESS);

            }
            result.add(employer);
        }
        return result;
    }


}
