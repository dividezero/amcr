package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.CoopBusinessType;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CoopBusinessTypeDaoTest extends BaseDaoTestCase {
    @Autowired
    private CoopBusinessTypeDao coopBusinessTypeDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveCoopBusinessType() {
        CoopBusinessType coopBusinessType = new CoopBusinessType();

        // enter all required fields
        coopBusinessType.setCode("Ow");
        coopBusinessType.setDescription("DySrImDfAgYqKnZkJnYaCgFyZtNyLnEwPlCsKzTkJgWhTuVdKjPmMbHiArFuJkBdBmLgQjXqJrMwMzWeBtIwWuVkGoWlPtZvEmYcUtEeStAoCfPnMcUvTmKoXpMmLkQsVxHoLwZgAuJkAmYpMaWyCoPmWqTjUhTlCtZxNjUpAoMcFtNnUuTiFqCdNgRqDsOsRsSyDoNi");
        coopBusinessType.setName("WrUiBgJoMpWhQbPsSkEhJqOaMzFzVlOiYgDgRtQvTpMiJcMaHk");

        log.debug("adding coopBusinessType...");
        coopBusinessType = coopBusinessTypeDao.save(coopBusinessType);

        coopBusinessType = coopBusinessTypeDao.get(coopBusinessType.getId());

        assertNotNull(coopBusinessType.getId());

        log.debug("removing coopBusinessType...");

        coopBusinessTypeDao.remove(coopBusinessType.getId());

        // should throw DataAccessException 
        coopBusinessTypeDao.get(coopBusinessType.getId());
    }
}