package com.angkasa.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Indexed
public class Product extends BaseObject implements Serializable {

    private static final long serialVersionUID = -4416145272827247129L;
    public static final String PERIOD_DAYS = "DAYS";
    public static final String PERIOD_MONTHLY = "MONTHLY";

    private Long id;
    private String code;
    private String name;
    private String description;
    private String transactionType;
    private Integer noOfTransactions = new Integer(1);
    private Long bankId;
    private BigDecimal totalAmount;
    private Date startDate;
    private Date endDate;
    private String period;
    private Integer periodMonthlyDays;
    private Integer periodDaysCount;

    private List<ProductTransaction> productTransactions = new ArrayList<ProductTransaction>();

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public Product(Long id) {
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

    @Column(unique = true, nullable = false)
    @Field
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(nullable = false)
    @Field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "transaction_type", nullable = false)
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Column(name = "no_of_transactions")
    public Integer getNoOfTransactions() {
        return noOfTransactions;
    }

    public void setNoOfTransactions(Integer noOfTransactions) {
        this.noOfTransactions = noOfTransactions;
    }

    @Column(name = "bank_id", nullable = true)
    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @Column(name = "total_amount", nullable = false)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(name = "start_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(nullable = false)
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Column(name = "period_monthly_days")
    public Integer getPeriodMonthlyDays() {
        return periodMonthlyDays;
    }

    public void setPeriodMonthlyDays(Integer periodMonthlyDays) {
        this.periodMonthlyDays = periodMonthlyDays;
    }

    @Column(name = "period_days_count")
    public Integer getPeriodDaysCount() {
        return periodDaysCount;
    }

    public void setPeriodDaysCount(Integer periodDaysCount) {
        this.periodDaysCount = periodDaysCount;
    }

    @Transient
    public List<ProductTransaction> getProductTransactions() {
        return productTransactions;
    }

    public void setProductTransactions(List<ProductTransaction> productTransactions) {
        this.productTransactions = productTransactions;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", noOfTransactions=" + noOfTransactions +
                ", bankId=" + bankId +
                ", totalAmount=" + totalAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", period='" + period + '\'' +
                ", periodMonthlyDays=" + periodMonthlyDays +
                ", periodDaysCount=" + periodDaysCount +
                ", productTransactions=" + productTransactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (bankId != null ? !bankId.equals(product.bankId) : product.bankId != null) return false;
        if (code != null ? !code.equals(product.code) : product.code != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (endDate != null ? !endDate.equals(product.endDate) : product.endDate != null) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (noOfTransactions != null ? !noOfTransactions.equals(product.noOfTransactions) : product.noOfTransactions != null)
            return false;
        if (period != null ? !period.equals(product.period) : product.period != null) return false;
        if (periodDaysCount != null ? !periodDaysCount.equals(product.periodDaysCount) : product.periodDaysCount != null)
            return false;
        if (periodMonthlyDays != null ? !periodMonthlyDays.equals(product.periodMonthlyDays) : product.periodMonthlyDays != null)
            return false;
        if (productTransactions != null ? !productTransactions.equals(product.productTransactions) : product.productTransactions != null)
            return false;
        if (startDate != null ? !startDate.equals(product.startDate) : product.startDate != null) return false;
        if (totalAmount != null ? !totalAmount.equals(product.totalAmount) : product.totalAmount != null) return false;
        if (transactionType != null ? !transactionType.equals(product.transactionType) : product.transactionType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        result = 31 * result + (noOfTransactions != null ? noOfTransactions.hashCode() : 0);
        result = 31 * result + (bankId != null ? bankId.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (periodMonthlyDays != null ? periodMonthlyDays.hashCode() : 0);
        result = 31 * result + (periodDaysCount != null ? periodDaysCount.hashCode() : 0);
        result = 31 * result + (productTransactions != null ? productTransactions.hashCode() : 0);
        return result;
    }
}
