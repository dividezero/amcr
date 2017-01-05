package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.DocumentData;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface DocumentDataManager extends GenericManager<DocumentData, Long> {
    
}