package com.angkasa.model;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * This class is used to represent an address with address,
 * city, province and postal-code information.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Embeddable
@Indexed
public class SimpleAddress implements Serializable {
    private static final long serialVersionUID = 3617859655330969141L;
    @Expose
    private String address1;
    @Expose
    private String address2;
    @Expose
    private String address3;
    @Expose
    private String city;
    @Expose
    private String province;
    @Expose
    private String country = "MY";
    @Expose
    private String postalCode;

    @Column(length = 300)
    @Field
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Column(length = 300)
    @Field
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(length = 300)
    @Field
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Column(length = 50)
    @Field
    public String getCity() {
        return city;
    }

    @Column(length = 100)
    @Field
    public String getProvince() {
        return province;
    }

    @Column(length = 100)
    @Field
    public String getCountry() {
        return country;
    }

    @Column(name = "postal_code", length = 15)
    @Field(analyze = Analyze.NO)
    public String getPostalCode() {
        return postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Returns a multi-line String with key=value pairs.
     *
     * @return a String representation of this class.
     */
    @Override
    public String toString() {
        return "SimpleAddress{" +
                "address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleAddress that = (SimpleAddress) o;

        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null) return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
        if (address3 != null ? !address3.equals(that.address3) : that.address3 != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = address1 != null ? address1.hashCode() : 0;
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }
}
