package com.angkasa.dao.hibernate;

import com.angkasa.model.Product;
import com.angkasa.dao.ProductDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoHibernate extends GenericDaoHibernate<Product, Long> implements ProductDao {

    public ProductDaoHibernate() {
        super(Product.class);
    }
}
