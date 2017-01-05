package com.angkasa.dao;

import com.angkasa.dao.BaseDaoTestCase;
import com.angkasa.model.Product;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class ProductDaoTest extends BaseDaoTestCase {
    @Autowired
    private ProductDao productDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveProduct() {
        Product product = new Product();

        // enter all required fields
        product.setCode("OqVaTrGnHwSsIeTwAfGqJzHoPnGqElQaWiBmEePyTiDjSySiNuYkWoCeQgSdWmWjMaRoRcPiPrBgNkFaNwSfXuLiIkEwQiMaCyOmYnWyMqXgPhQvRmDpJzWwJnMbUmStErRpCxGoUxJrTcVhGsQiUdNpGjCqSvHsXhPrLjTlSjIhJvGxSnPiSnMoYjRmLyCkMuQwFxMjZeDjYkMrPsZgZvOfDuXgRbGrPwZlZcGkSiBwIvUqQgGvXeJaToQgQxK");
        product.setName("DcVbMxXxAhJgHmYcDdBhGvVjDnWlTqEiUfTjWhQmStNnGpLeVqNwSwSoRaSzIkBpWmWiQrQaLnTrScWzHtSfLaLyWvUeQfWtYmKpKyMdVwMeHfDkRfRsQhRuOvOcJsFmIpMiFqCnBjDrUsBcViCaEnLsFdTnLnGhRuFcNrVvMuIdCwNwKsZoXqWfUvWnMoDgTcFsLgRsGcZeFoHtLxRvXlGhBgFgLdAxTyIkIaYkFvZlQwSyArMmFzEqHiVhSuW");
        product.setPeriod("TmAmKoKeZeOtKyVgRiIvDsZcOwKbWgXvUaEyMkIcOdWkMnElHvIkZgVyWlCbMzAuDqSuZmEdDhCvBuVaTwGcCaIdFhJtZpWfEwZnAeSvCpCmJlCgGgVgNjGxIsGvNlLzXuWbZlUxUdJuWbQuDiFjDhOrDaFjYvGwLxCxDuUcKrElMzGmEgWuAeOsHkIfOjYbSqGoNqPtXyInVhFlWlGyCoNgWiGzXtFuQsKiKtUiTbWzRhPqChXmYjJqJcYfYrV");
        product.setTotalAmount(new BigDecimal("0"));
        product.setTransactionType("KrXkQkUuKxLgWuCpTcSeIqQiLrChWdHnFlBtTcYrKeDxOhSjWhAnUjLxFrUcJaAzKeYgThOwVoAoXqTbSgDpOdJyBvOdImXkGiFdSzEeZmNcEbEkJdHkQyZwZkLmCuOlPkTgLtViPaSiFbRoPySkMyZfPnNbNmOcJlHkGrRzKyFnSjKqWnOpSdJaIsFnZzEwAjDlLdXxDdEvQgNvKpNcYuIaXcAqKrZvPuGfSfZqPcVnJwOxXfLvVjVlIjSvYvG");

        log.debug("adding product...");
        product = productDao.save(product);

        product = productDao.get(product.getId());

        assertNotNull(product.getId());

        log.debug("removing product...");

        productDao.remove(product.getId());

        // should throw DataAccessException 
        productDao.get(product.getId());
    }
}