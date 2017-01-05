package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.AmcrLookup;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface AmcrLookupManager extends GenericManager<AmcrLookup, Long> {
    
}