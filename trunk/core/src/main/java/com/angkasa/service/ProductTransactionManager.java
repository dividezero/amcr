package com.angkasa.service;

import com.angkasa.service.GenericManager;
import com.angkasa.model.ProductTransaction;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface ProductTransactionManager extends GenericManager<ProductTransaction, Long> {

    List<ProductTransaction> getByProductId(Long productId);
}