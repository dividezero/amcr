package com.angkasa.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "member_product")
public class MemberProduct extends BaseObject implements Serializable {

    private static final long serialVersionUID = -4416145272827247129L;

    private Long id;
    private Member member;
    private Product product;
    private Date registerDate;
    private Date commencementDate;


    /**
     * Default constructor
     */
    public MemberProduct() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public MemberProduct(Long id) {
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
    @JoinColumn(name = "member_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "register_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Column(name = "commencement_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(Date commencementDate) {
        this.commencementDate = commencementDate;
    }

    @Override
    public String toString() {
        return "MemberProduct{" +
                "id=" + id +
                ", member=" + member +
                ", product=" + product +
                ", registerDate=" + registerDate +
                ", commencementDate=" + commencementDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberProduct that = (MemberProduct) o;

        if (commencementDate != null ? !commencementDate.equals(that.commencementDate) : that.commencementDate != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (member != null ? !member.equals(that.member) : that.member != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (registerDate != null ? !registerDate.equals(that.registerDate) : that.registerDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (member != null ? member.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + (commencementDate != null ? commencementDate.hashCode() : 0);
        return result;
    }
}
