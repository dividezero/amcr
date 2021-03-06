package com.angkasa.webapp.controller;

import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.Product;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private ProductFormController form;
    private Product product;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/productform");
        request.addParameter("id", "-1");

        product = form.showForm(request);
        assertNotNull(product);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/productform");
        request.addParameter("id", "-1");

        product = form.showForm(request);
        assertNotNull(product);

        request = newPost("/productform");

        product = form.showForm(request);
        // update required fields
        product.setCode("GtDbSeBgOoLfAfCoWhReXdDaBjRjWnQnQfFwDtTaYhIuWyAmCsJyZcYmFgJmDkLeZtPtLeFxAgYrThDkUkWhCaBlQpWvMiWiChDkUnVvFqQjYhCdAnTyQbEdArFkLtGaIrEhCqGcMiMfBrObTxFyWuJnWaUtCyBfTtNdAhIePbMwLyZcVnYnZiHmHrBqUaOcDjBkTmHcEtDdOvEaJrGnBpLaTpZvQtTiMgGmJyKtKcCwXyPwFwHuJkXxGjAgMzM");
        product.setName("VsOkQbCmTkKjExOkVvMsGeZcNtIfMxZzQpBnSfXaHuTkBgWeGiEnQhLvXqPoByKjXyIbLbLjJhFtAoEqWwDbCmExEuStSjBsPbKiSbIhHwRqDyWtKzVkMnMlZoGaExKeViKvPhNpIyLgGeWzGtYyLaOyMfWfUeUkBjTaUhNfSuPuOxEvNnJeEdRiIpNfZiOeXzIgHvNjXmCaEgZqJmWvYqRhAxYsCkSlXtCyKjDcYrNfIpJqMaOcOkPvQxIyToL");
        product.setPeriod("KxRuAuIdYgBaVkNgJwQxJgXtJlHjUxWjXaBdSzWnBlScCsEmAvCvZnArMbFkKqDrFvUyLdYwHvXiLwSeHiOeEuHvRwHaQaGpWuTtZtLySoDsDcFlQmGlSpAeRqTqZgDhVuNpCbPiLoHqLjQfEeFmDrDdIcOqTvBtZeEcYkWvNiHzVuBiSkOxWtVuYjKmLnWbUtSrHtTlEgRjNoJbBhKiAfShKbHdLwVaDiIbRyCeKdSxEsTpMqXeBgGrXyTzVnF");
        product.setTotalAmount(new BigDecimal("0"));
        product.setTransactionType("QvFkGtXkYyNzQyCmOyFqStStFlXlWoVtDqBkTsWjTfNfOcAcWkPhImGiPvUjKhNyJuIkRfKpYbVkPnEeFgAjGqDyMkJcWcOkOjQqFrOsIcMgOkBxSvTkCfLbMaSaDwGqKiYcHoMxUaFmPuJzVnXfJkKmIgVbByAnLtSsKnFkTuOxUxHkSwOzKrHsNlVtAmSxKxUgAsNaIuAqClZvUuBmYgMvMeEoVvImSwGuQgGdKlOmZdMxJkOoKrVrBaLnWiT");

        BindingResult errors = new DataBinder(product).getBindingResult();
        form.onSubmit(product, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/productform");
        request.addParameter("delete", "");
        product = new Product();
        product.setId(-2L);

        BindingResult errors = new DataBinder(product).getBindingResult();
        form.onSubmit(product, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
