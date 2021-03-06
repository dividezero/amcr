package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.DmsMemberUpdate;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface DmsMemberUpdateManager extends GenericManager<DmsMemberUpdate, Long> {

    DmsMemberUpdate saveNewDmsMemberUpdate(String icNumber, String phoneNum, String status);
}