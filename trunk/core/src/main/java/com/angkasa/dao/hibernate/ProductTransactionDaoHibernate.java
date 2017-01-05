package com.angkasa.dao.hibernate;

import com.angkasa.model.Product;
import com.angkasa.model.ProductTransaction;
import com.angkasa.dao.ProductTransactionDao;
import com.angkasa.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Repository("productTransactionDao")
public class ProductTransactionDaoHibernate extends GenericDaoHibernate<ProductTransaction, Long> implements ProductTransactionDao {

    public ProductTransactionDaoHibernate() {
        super(ProductTransaction.class);
    }

    @Override
    public List<ProductTransaction> getByProductId(Long productId) {
        Criteria criteria = getSession().createCriteria(ProductTransaction.class);
        criteria.createAlias("product", "product");
        criteria.add(Restrictions.eq("product.id", productId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<ProductTransaction> result = criteria.list();
        return result;
    }

    @Override
    public List<ProductTransaction> save(List<ProductTransaction> productTransactions){
        for(ProductTransaction productTransaction : productTransactions){
            productTransaction = this.save(productTransaction);
        }
        return productTransactions;
    }

    @Override
    public List<ProductTransaction> createTransactionsFromProduct(Product product) {
        List<ProductTransaction> productTransactionList = new ArrayList<>();
        if (product.getId() != null) {
            int noOfTrans = product.getNoOfTransactions().intValue();
            BigDecimal totalAmount = product.getTotalAmount().setScale(2);
            log.debug("totalAmt=" + totalAmount.toString());
            BigDecimal amountPerTrans = totalAmount.divide(new BigDecimal(noOfTrans), 2, RoundingMode.FLOOR);
            log.debug("amtPerTrans=" + amountPerTrans.toString());


            BigDecimal totalTrans = new BigDecimal(0).setScale(2);
            for (int i = 0; i < noOfTrans; i++) {
                ProductTransaction productTransaction = new ProductTransaction();
                productTransaction.setTransNo(new Integer(i));
                productTransaction.setProduct(product);
                productTransaction.setTransactionType(product.getTransactionType());

                //last entry
                if ((i + 1) == noOfTrans) {
                    productTransaction.setAmount(totalAmount.subtract(totalTrans));
                    log.debug("Last entry=" + productTransaction.getAmount());
                } else {
                    totalTrans = totalTrans.add(amountPerTrans);
                    log.debug("totalTrans=" + totalTrans.toString());
                    productTransaction.setAmount(amountPerTrans);
                }
                productTransactionList.add(productTransaction);
            }
            save(productTransactionList);
        }
        return productTransactionList;
    }
}
