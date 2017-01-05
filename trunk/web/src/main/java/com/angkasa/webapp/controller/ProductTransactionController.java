package com.angkasa.webapp.controller;

import com.angkasa.dao.SearchException;
import com.angkasa.service.ProductTransactionManager;
import com.angkasa.model.ProductTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productTransactions*")
public class ProductTransactionController {
    private ProductTransactionManager productTransactionManager;

    @Autowired
    public void setProductTransactionManager(ProductTransactionManager productTransactionManager) {
        this.productTransactionManager = productTransactionManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(productTransactionManager.getAll());

//            model.addAttribute(productTransactionManager.search(query, ProductTransaction.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(productTransactionManager.getAll());
        }
        return model;
    }
}
