package com.angkasa.dao;

import com.angkasa.dao.GenericDao;

import com.angkasa.model.Product;
import com.angkasa.model.ProductTransaction;

import java.util.List;

/**
 * An interface that provides a data management interface to the ProductTransaction table.
 */
public interface ProductTransactionDao extends GenericDao<ProductTransaction, Long> {

    List<ProductTransaction> getByProductId(Long productId);

    List<ProductTransaction> save(List<ProductTransaction> productTransactions);
    List<ProductTransaction> createTransactionsFromProduct(Product product);
}