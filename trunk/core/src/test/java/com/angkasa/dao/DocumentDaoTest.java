package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.Document;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DocumentDaoTest extends BaseDaoTestCase {
    @Autowired
    private DocumentDao documentDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveDocument() {
        Document document = new Document();

        // enter all required fields
        document.setContentType("FfUyUfHgQjAoOoXqPdLhCmAnCsZrSzWfMbIlPnIcAeScMmSnVdPeSxMoBbZtDuWkBzLgPkEpUuCxIvQnQwVmJqZyJmFbMyWgHoBs");
        document.setFileRelativePath("CcKyFsAqEhAlMlZfPzDcLcFuPqEiMtMcVzDxEmGuTuZdLgJtUaXcLmIpSeUjEpPrMzOdIzGzUoFgJhXgAiZuHrOeKbRzUsPuCuYfWwSlJfLlDaCpIvFdTfLqMiXgRpKhCjSnBmCqQhRrDqLhGcNbWpGpIbOsHiBfHaArHpKiPuExEcEuLwXhNvOaKbJqQmSoFlHaKyJsKgExPyCzTuToGkVuEcXqWlHoUwGoBpQzTqMmLzTcJiIzQlIzXb");
        document.setModuleName("ZfJtFdBaZuQtOzKeNqElRpHzTvIzOdCpExVxMkNrNeEtUcGpOmJgHmDwUmBsMyDuWmTeWqCwJtPiNqLlFvWqRwAjLmCvJwVjClFx");
        document.setModulePrimKey("MmDkMuHeWeVuCmCyQsWeFcVeRnNtCtPzLfAcMxUpGiKfBqPrEwUlDxUoHtOxYwZkCpHcAjWrJqOwBiUwGkWiSuKoClOwHyMiDpBo");

        log.debug("adding document...");
        document = documentDao.save(document);

        document = documentDao.get(document.getId());

        assertNotNull(document.getId());

        log.debug("removing document...");

        documentDao.remove(document.getId());

        // should throw DataAccessException 
        documentDao.get(document.getId());
    }
}