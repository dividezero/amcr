package com.angkasa.service.impl;

import com.angkasa.dao.AmcrLookupDao;
import com.angkasa.model.AmcrLookup;
import com.angkasa.service.AmcrLookupManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("amcrLookupManager")
@WebService(serviceName = "AmcrLookupService", endpointInterface = "com.angkasa.service.AmcrLookupManager")
public class AmcrLookupManagerImpl extends GenericManagerImpl<AmcrLookup, Long> implements AmcrLookupManager {
    AmcrLookupDao amcrLookupDao;

    @Autowired
    public AmcrLookupManagerImpl(AmcrLookupDao amcrLookupDao) {
        super(amcrLookupDao);
        this.amcrLookupDao = amcrLookupDao;
    }
}