package com.angkasa.webapp.controller;

import com.angkasa.model.Product;
import com.angkasa.webapp.controller.BaseControllerTestCase;
import com.angkasa.model.ProductTransaction;
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

public class ProductTransactionFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private ProductTransactionFormController form;
    private ProductTransaction productTransaction;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/productTransactionform");
        request.addParameter("id", "-1");

        productTransaction = form.showForm(request);
        assertNotNull(productTransaction);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/productTransactionform");
        request.addParameter("id", "-1");

        productTransaction = form.showForm(request);
        assertNotNull(productTransaction);

        request = newPost("/productTransactionform");

        productTransaction = form.showForm(request);
        // update required fields
        Product product = new Product();
        product.setId(new Long(-1));
        productTransaction.setProduct(product);
        productTransaction.setAmount(new BigDecimal("100"));
        productTransaction.setTransNo(1141016433);

        BindingResult errors = new DataBinder(productTransaction).getBindingResult();
        form.onSubmit(productTransaction, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/productTransactionform");
        request.addParameter("delete", "");
        productTransaction = new ProductTransaction();
        productTransaction.setId(-2L);

        BindingResult errors = new DataBinder(productTransaction).getBindingResult();
        form.onSubmit(productTransaction, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
