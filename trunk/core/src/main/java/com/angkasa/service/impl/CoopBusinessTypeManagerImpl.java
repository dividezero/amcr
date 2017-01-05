package com.angkasa.service.impl;

import com.angkasa.dao.CoopBusinessTypeDao;
import com.angkasa.model.CoopBusinessType;
import com.angkasa.service.CoopBusinessTypeManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("coopBusinessTypeManager")
@WebService(serviceName = "CoopBusinessTypeService", endpointInterface = "com.angkasa.service.CoopBusinessTypeManager")
public class CoopBusinessTypeManagerImpl extends GenericManagerImpl<CoopBusinessType, Long> implements CoopBusinessTypeManager {
    CoopBusinessTypeDao coopBusinessTypeDao;

    @Autowired
    public CoopBusinessTypeManagerImpl(CoopBusinessTypeDao coopBusinessTypeDao) {
        super(coopBusinessTypeDao);
        this.coopBusinessTypeDao = coopBusinessTypeDao;
    }
}