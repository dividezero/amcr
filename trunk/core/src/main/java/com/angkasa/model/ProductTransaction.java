package com.angkasa.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product_transaction")
public class ProductTransaction extends BaseObject implements Serializable {

    private static final long serialVersionUID = -4416145272827247829L;

    private Long id;
    private Product product;
    private Integer transNo;
    private BigDecimal amount;
    private String transactionType;

    /**
     * Default constructor
     */
    public ProductTransaction() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public ProductTransaction(Long id) {
        this.id = id;
    }

    @Override
    @Transient
    public String getPrimaryKey() {
        return getStringKey(this.id);
    }
    /**
     * Unique system id - NUMBER(19)
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "trans_no", nullable = false)
    public Integer getTransNo() {
        return transNo;
    }

    public void setTransNo(Integer transNo) {
        this.transNo = transNo;
    }

    @Column(nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "transaction_type")
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "ProductTransaction{" +
                "id=" + id +
                ", product=" + product +
                ", transNo=" + transNo +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductTransaction that = (ProductTransaction) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (transNo != null ? !transNo.equals(that.transNo) : that.transNo != null) return false;
        if (transactionType != null ? !transactionType.equals(that.transactionType) : that.transactionType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (transNo != null ? transNo.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        return result;
    }
}
