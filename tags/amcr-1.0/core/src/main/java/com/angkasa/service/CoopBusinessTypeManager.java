package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.CoopBusinessType;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface CoopBusinessTypeManager extends GenericManager<CoopBusinessType, Long> {
    
}