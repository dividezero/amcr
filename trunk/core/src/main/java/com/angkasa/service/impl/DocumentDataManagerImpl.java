package com.angkasa.service.impl;

import com.angkasa.dao.DocumentDataDao;
import com.angkasa.model.DocumentData;
import com.angkasa.service.DocumentDataManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("documentDataManager")
@WebService(serviceName = "DocumentDataService", endpointInterface = "com.angkasa.service.DocumentDataManager")
public class DocumentDataManagerImpl extends GenericManagerImpl<DocumentData, Long> implements DocumentDataManager {
    DocumentDataDao documentDataDao;

    @Autowired
    public DocumentDataManagerImpl(DocumentDataDao documentDataDao) {
        super(documentDataDao);
        this.documentDataDao = documentDataDao;
    }
}