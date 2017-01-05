package com.angkasa.dao.hibernate;

import com.angkasa.Constants;
import com.angkasa.dao.AmcrLookupDao;
import com.angkasa.dao.CoopMemberDao;
import com.angkasa.model.*;
import com.angkasa.dao.CoopDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import com.angkasa.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("coopDao")
public class CoopDaoHibernate extends GenericDaoHibernate<Coop, Long> implements CoopDao {

    public CoopDaoHibernate() {
        super(Coop.class);
    }

    @Autowired
    AmcrLookupDao amcrLookupDao;

    @Override
    public Coop getByUserId(Long userId) {
        Criteria criteria = getSession().createCriteria(Coop.class);
        criteria.add(Restrictions.eq("userId", userId));

        List<Coop> result = criteria.list();

        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public Coop getByCoopCode(String coopCode) {
        Criteria criteria = getSession().createCriteria(Coop.class);
        criteria.add(Restrictions.eq("coopCode", coopCode));

        List<Coop> result = criteria.list();

        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public Coop getByBPACode(String bpaCode) {
        Criteria criteria = getSession().createCriteria(Coop.class);
        criteria.add(Restrictions.eq("bpaCode", bpaCode));

        List<Coop> result = criteria.list();

        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public Coop getByAmcrCode(String amcrCode) {
        Criteria criteria = getSession().createCriteria(Coop.class);
        criteria.add(Restrictions.eq("amcrCode", amcrCode));

        List<Coop> result = criteria.list();

        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public boolean coopCodeExists(String coopCode) {
        Coop result = getByCoopCode(coopCode);

        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean amcrCodeExists(String amcrCode) {
        Coop result = getByAmcrCode(amcrCode);

        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public Coop save(Coop object) {
        if (StringUtils.isBlank(object.getAmcrCode())) {
            object.setAmcrCode(amcrLookupDao.getNewCoopLookup());
        }
        return super.save(object);
    }

    @Override
    public Coop get(Long id) {
        Coop result = super.get(id);
        if (result != null) {
            Hibernate.initialize(result.getCoopBusinessTypes());
        }
        return result;
    }

    @Override
    public List<Coop> validateCoopImportList(List<Coop> coopList) {
        for (Coop coop : coopList) {
            coop = validateCoopImport(coop);
        }
        return coopList;
    }

    @Override
    public Coop validateCoopImport(Coop coop) {
        coop.setImportStatus(null);
        coop.setImportRemark("");

        // Validation time!
        if (StringUtils.isBlank(coop.getName())) {
            coop.setImportStatus(Constants.IMPORT_STATUS_INVALID);
            coop.addImportRemark(REMARK_COOP_NAME_NOT_EXISTS);
        }
        if (getByCoopCode(coop.getCoopCode()) != null) {
            coop.setImportStatus(Constants.IMPORT_STATUS_INVALID);
            coop.addImportRemark(REMARK_COOP_CODE_EXISTS);
        }

        // If no errors, its valid
        if (!StringUtils.equals(coop.getImportStatus(), Constants.IMPORT_STATUS_INVALID)) {
            coop.setImportStatus(Constants.IMPORT_STATUS_VALID);
        }
        return coop;
    }

    @Override
    public List<Coop> processCoopImportList(List<Coop> coopList) {
        int count = 1;
        int size = coopList.size();
        for (Coop coop : coopList) {
            // show progress
            log.debug("Saving " + count + " of " + size);
            count ++;

            // Dont save again if already success
            if (!StringUtils.equals(coop.getImportStatus(), Constants.IMPORT_STATUS_SUCCESS)) {

                // Last minute validation
                coop = validateCoopImport(coop);

                // Save
                if (StringUtils.equals(coop.getImportStatus(), Constants.IMPORT_STATUS_VALID)) {

                    coop.setTypeFlag(Constants.COOP_FLAG_ANGKASA);
                    coop.setPreloaded(true);
                    coop.setEnabled(false);
                    Coop savedCoop = this.save(coop);

                    savedCoop.setImportStatus(Employer.STATUS_SUCCESS);

                    savedCoop.setImportStatus(Constants.IMPORT_STATUS_SUCCESS);
                    savedCoop.setImportId(coop.getImportId());
                    coop = savedCoop;
                }
            }
        }
        return coopList;
    }
}
