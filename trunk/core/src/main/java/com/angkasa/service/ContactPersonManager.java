package com.angkasa.service;

import java.util.List;

import javax.jws.WebService;

import com.angkasa.model.ContactPerson;

@WebService
public interface ContactPersonManager extends GenericManager<ContactPerson, Long> {
	List<ContactPerson> getByCoopId(Long coopId);
}