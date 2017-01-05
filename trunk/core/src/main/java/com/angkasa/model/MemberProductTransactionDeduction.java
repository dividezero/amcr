package com.angkasa.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "member_product_transaction_deduction")
public class MemberProductTransactionDeduction extends BaseObject implements Serializable {

    private static final long serialVersionUID = -4416145272827628329L;

    private Long id;
    private MemberProduct memberProduct;
    private ProductTransaction productTransaction;
    private Long batchId;
    private Date deductionDate;
    private BigDecimal deductionAmount;

    /**
     * Default constructor
     */
    public MemberProductTransactionDeduction() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public MemberProductTransactionDeduction(Long id) {
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
    @JoinColumn(name = "member_product_id", referencedColumnName = "id", insertable = false, updatable = false)
    public MemberProduct getMemberProduct() {
        return memberProduct;
    }

    public void setMemberProduct(MemberProduct memberProduct) {
        this.memberProduct = memberProduct;
    }

    @ManyToOne
    @JoinColumn(name = "product_transaction_id", referencedColumnName = "id", insertable = false, updatable = false)
    public ProductTransaction getProductTransaction() {
        return productTransaction;
    }

    public void setProductTransaction(ProductTransaction productTransaction) {
        this.productTransaction = productTransaction;
    }

    @Column(name = "batch_id")
    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    @Column(name = "deduction_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDeductionDate() {
        return deductionDate;
    }

    public void setDeductionDate(Date deductionDate) {
        this.deductionDate = deductionDate;
    }

    @Column(name = "deduction_amount")
    public BigDecimal getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    @Override
    public String toString() {
        return "MemberProductTransactionDeduction{" +
                "id=" + id +
                ", memberProduct=" + memberProduct +
                ", productTransaction=" + productTransaction +
                ", batchId=" + batchId +
                ", deductionDate=" + deductionDate +
                ", deductionAmount=" + deductionAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberProductTransactionDeduction that = (MemberProductTransactionDeduction) o;

        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;
        if (deductionAmount != null ? !deductionAmount.equals(that.deductionAmount) : that.deductionAmount != null)
            return false;
        if (deductionDate != null ? !deductionDate.equals(that.deductionDate) : that.deductionDate != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (memberProduct != null ? !memberProduct.equals(that.memberProduct) : that.memberProduct != null)
            return false;
        if (productTransaction != null ? !productTransaction.equals(that.productTransaction) : that.productTransaction != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (memberProduct != null ? memberProduct.hashCode() : 0);
        result = 31 * result + (productTransaction != null ? productTransaction.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (deductionDate != null ? deductionDate.hashCode() : 0);
        result = 31 * result + (deductionAmount != null ? deductionAmount.hashCode() : 0);
        return result;
    }
}
