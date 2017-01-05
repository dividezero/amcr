package com.angkasa.service.impl;

import com.angkasa.dao.ProductDao;
import com.angkasa.dao.ProductTransactionDao;
import com.angkasa.model.Product;
import com.angkasa.model.ProductTransaction;
import com.angkasa.service.ProductManager;
import com.angkasa.service.ProductTransactionManager;
import com.angkasa.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("productManager")
@WebService(serviceName = "ProductService", endpointInterface = "com.angkasa.service.ProductManager")
public class ProductManagerImpl extends GenericManagerImpl<Product, Long> implements ProductManager {
    ProductDao productDao;

    @Autowired
    public ProductManagerImpl(ProductDao productDao) {
        super(productDao);
        this.productDao = productDao;
    }

    @Autowired
    ProductTransactionDao productTransactionDao;

    @Override
    public Product saveWithTransactions(Product product){
        product = this.save(product);

        List<ProductTransaction> productTransactionList = productTransactionDao.createTransactionsFromProduct(product);
        return product;
    }

}