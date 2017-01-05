package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.Product;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface ProductManager extends GenericManager<Product, Long> {

    Product saveWithTransactions(Product product);
}