package com.angkasa.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.angkasa.model.Member;

public class MemberDaoTest extends BaseDaoTestCase {
	@Autowired
	private MemberDao memberDao;

	@Test(expected = DataAccessException.class)
	public void testAddAndRemoveMember() {
		Member member = new Member();

		// enter all required fields
		member.setGender("F");
		member.setIcNumber("VkAwIsFyCoQx");
		member.setMembershipNo("XaOmJeWlSs");
		member.setName("NdOgDrWgTnFiDdMaGyAdPqRyNwRlTcCpClQjFtNpHmKsLgWzUm");
		member.setPhoneNo("MjKsOnMaOvDh");

		log.debug("adding member...");
		member = memberDao.save(member);

		member = memberDao.get(member.getId());

		assertNotNull(member.getId());

		log.debug("removing member...");

		memberDao.remove(member.getId());

		// should throw DataAccessException 
		memberDao.get(member.getId());
	}
}