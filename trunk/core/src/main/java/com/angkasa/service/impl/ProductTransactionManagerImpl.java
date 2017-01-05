package com.angkasa.service.impl;

import com.angkasa.dao.ProductTransactionDao;
import com.angkasa.model.ProductTransaction;
import com.angkasa.service.ProductTransactionManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("productTransactionManager")
@WebService(serviceName = "ProductTransactionService", endpointInterface = "com.angkasa.service.ProductTransactionManager")
public class ProductTransactionManagerImpl extends GenericManagerImpl<ProductTransaction, Long> implements ProductTransactionManager {
    ProductTransactionDao productTransactionDao;

    @Autowired
    public ProductTransactionManagerImpl(ProductTransactionDao productTransactionDao) {
        super(productTransactionDao);
        this.productTransactionDao = productTransactionDao;
    }

    @Override
    public List<ProductTransaction> getByProductId(Long productId){
        return productTransactionDao.getByProductId(productId);
    }
}