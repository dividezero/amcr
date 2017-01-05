package com.angkasa.dao.hibernate;

import com.angkasa.model.SystemSetting;
import com.angkasa.dao.SystemSettingDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("systemSettingDao")
public class SystemSettingDaoHibernate extends GenericDaoHibernate<SystemSetting, Long> implements SystemSettingDao {

    public SystemSettingDaoHibernate() {
        super(SystemSetting.class);
    }

    @Override
    public String valueByPropertyName(String propertyName) {
        SystemSetting systemSetting = getByPropertyName(propertyName);

        if(systemSetting != null){
            return systemSetting.getPropertyValue();
        }
        return "";
    }

    @Override
    public SystemSetting getByPropertyName(String propertyName) {
        Criteria criteria = getSession().createCriteria(SystemSetting.class);

        criteria.add(Restrictions.eq("propertyName", propertyName));
        List<SystemSetting> lists = criteria.list();

        if(lists != null && !lists.isEmpty()) {
            return lists.get(0);
        }

        return null;
    }
}
