package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.Event;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface EventManager extends GenericManager<Event, Long> {
    
}