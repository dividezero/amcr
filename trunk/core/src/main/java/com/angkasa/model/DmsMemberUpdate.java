package com.angkasa.model;

import com.angkasa.Constants;
import com.google.gson.annotations.Expose;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "dmsupdate")
@Indexed
@XmlRootElement
public class DmsMemberUpdate extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359516L;

    private Long id;

    @Expose
    private String icNumber;
    @Expose
    private String phoneNum;
    @Expose
    private String utilityField1;
    @Expose
    private String utilityField2;
    @Expose
    private String utilityField3;

    @Expose
    private String status;
    @Expose
    private Date updatedMemberDate;


    public DmsMemberUpdate() {

    }

    @Override
    @Transient
    public String getPrimaryKey() {
        return getStringKey(this.id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "icNumber", nullable = false, length = 20, unique = false)
    @Field
    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    @Column(name = "phonenum", nullable = false, length = 20, unique = false)
    @Field
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Column(name = "status", nullable = true, length = 2, unique = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "updatedMemberDate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdatedMemberDate() {
        return updatedMemberDate;
    }

    public void setUpdatedMemberDate(Date updatedMemberDate) {
        this.updatedMemberDate = updatedMemberDate;
    }

    @Column(name = "utilityfield1", nullable = true, length = 50, unique = false)
    public String getUtilityField1() {
        return utilityField1;
    }

    public void setUtilityField1(String utilityField1) {
        this.utilityField1 = utilityField1;
    }

    @Column(name = "utilityfield2", nullable = true, length = 50, unique = false)
    public String getUtilityField2() {
        return utilityField2;
    }

    public void setUtilityField2(String utilityField2) {
        this.utilityField2 = utilityField2;
    }

    @Column(name = "utilityfield3", nullable = true, length = 50, unique = false)
    public String getUtilityField3() {
        return utilityField3;
    }

    public void setUtilityField3(String utilityField3) {
        this.utilityField3 = utilityField3;
    }

    @Override
    public String toString() {
        return "DmsMemberUpdate{" +
                "id=" + id +
                ", icNumber='" + icNumber + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", utilityField1='" + utilityField1 + '\'' +
                ", utilityField2='" + utilityField2 + '\'' +
                ", utilityField3='" + utilityField3 + '\'' +
                ", status='" + status + '\'' +
                ", updatedMemberDate=" + updatedMemberDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmsMemberUpdate that = (DmsMemberUpdate) o;

        if (icNumber != null ? !icNumber.equals(that.icNumber) : that.icNumber != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (phoneNum != null ? !phoneNum.equals(that.phoneNum) : that.phoneNum != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (updatedMemberDate != null ? !updatedMemberDate.equals(that.updatedMemberDate) : that.updatedMemberDate != null)
            return false;
        if (utilityField1 != null ? !utilityField1.equals(that.utilityField1) : that.utilityField1 != null)
            return false;
        if (utilityField2 != null ? !utilityField2.equals(that.utilityField2) : that.utilityField2 != null)
            return false;
        if (utilityField3 != null ? !utilityField3.equals(that.utilityField3) : that.utilityField3 != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (icNumber != null ? icNumber.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        result = 31 * result + (utilityField1 != null ? utilityField1.hashCode() : 0);
        result = 31 * result + (utilityField2 != null ? utilityField2.hashCode() : 0);
        result = 31 * result + (utilityField3 != null ? utilityField3.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (updatedMemberDate != null ? updatedMemberDate.hashCode() : 0);
        return result;
    }
}
