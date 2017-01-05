package com.angkasa.model;

import java.io.Serializable;

import javax.persistence.*;

import com.angkasa.model.BaseObject;
import com.angkasa.model.SimpleAddress;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity
@Table(name = "bank")
@Indexed
public class Bank extends BaseObject implements Serializable {

    private static final long serialVersionUID = -4416145272823647129L;

    private Long id;
    private String code;
    private String name;
    private SimpleAddress address;

    /**
     * Default constructor
     */
    public Bank() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public Bank(Long id) {
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

    /**
     * Code for bank - VARCHAR2(50 CHAR)
     */
    @Column(nullable = false, length = 50, unique = true)
    @Field
    public String getCode() {
        return code;
    }

    /**
     * The name for bank - VARCHAR2(250 CHAR)
     */
    @Column(nullable = false, length = 250)
    @Field
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    @IndexedEmbedded
    public SimpleAddress getAddress() {
        return address;
    }

    public void setAddress(SimpleAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        if (address != null ? !address.equals(bank.address) : bank.address != null) return false;
        if (code != null ? !code.equals(bank.code) : bank.code != null) return false;
        if (id != null ? !id.equals(bank.id) : bank.id != null) return false;
        if (name != null ? !name.equals(bank.name) : bank.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
