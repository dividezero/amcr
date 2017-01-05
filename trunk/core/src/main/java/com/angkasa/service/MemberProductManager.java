package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.MemberProduct;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface MemberProductManager extends GenericManager<MemberProduct, Long> {
    
}