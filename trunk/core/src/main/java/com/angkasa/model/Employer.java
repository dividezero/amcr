package com.angkasa.model;

import com.google.gson.annotations.Expose;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "employer")
@Indexed
@XmlRootElement
public class Employer extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILED = "FAILED";

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private String employerCode;
    @Expose
    private String employerNo;

    @Expose
    private SimpleAddress address = new SimpleAddress();

    @Expose
    private String phoneNo;
    @Expose
    private String phoneNo2;
    @Expose
    private String phoneNo3;
    @Expose
    private String faxNo;

    @Expose
    private String website;

    @Expose
    private String employerImportId;
    @Expose
    private String importId;
    @Expose
    private String importStatus;
    @Expose
    private String importRemark;

    public Employer() {

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

    @Column(name = "name", nullable = false, length = 200)
    @Field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "employer_code", nullable = false, length = 20, unique = true)
    @Field
    public String getEmployerCode() {
        return employerCode;
    }

    public void setEmployerCode(String employerCode) {
        this.employerCode = employerCode;
    }

    @Column(name = "employer_no", nullable = true, length = 20, unique = true)
    public String getEmployerNo() {
        return employerNo;
    }

    public void setEmployerNo(String employerNo) {
        this.employerNo = employerNo;
    }

    @Embedded
    @IndexedEmbedded
    public SimpleAddress getAddress() {
        return address;
    }

    public void setAddress(SimpleAddress address) {
        this.address = address;
    }

    @Column(name = "phone_number")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "phone_number2")
    public String getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    @Column(name = "phone_number3")
    public String getPhoneNo3() {
        return phoneNo3;
    }

    public void setPhoneNo3(String phoneNo3) {
        this.phoneNo3 = phoneNo3;
    }

    @Column(name = "fax_no")
    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    @Column(name = "website")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Transient
    public String getEmployerImportId() {
        return employerImportId;
    }

    public void setEmployerImportId(String employerImportId) {
        this.employerImportId = employerImportId;
    }

    @Transient
    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    @Transient
    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    @Transient
    public String getImportRemark() {
        return importRemark;
    }

    public void setImportRemark(String importRemark) {
        this.importRemark = importRemark;
    }

    @Transient
    public void addImportRemark(String remark) {
        if (importRemark == null) {
            importRemark = "";
        } else {
            importRemark += ", ";
        }
        importRemark = importRemark + remark;
    }


    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employerCode='" + employerCode + '\'' +
                ", employerNo='" + employerNo + '\'' +
                ", address=" + address +
                ", phoneNo='" + phoneNo + '\'' +
                ", phoneNo2='" + phoneNo2 + '\'' +
                ", phoneNo3='" + phoneNo3 + '\'' +
                ", faxNo='" + faxNo + '\'' +
                ", website='" + website + '\'' +
                ", employerImportId='" + employerImportId + '\'' +
                ", importId='" + importId + '\'' +
                ", importStatus='" + importStatus + '\'' +
                ", importRemark='" + importRemark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employer employer = (Employer) o;

        if (address != null ? !address.equals(employer.address) : employer.address != null) return false;
        if (employerCode != null ? !employerCode.equals(employer.employerCode) : employer.employerCode != null)
            return false;
        if (employerImportId != null ? !employerImportId.equals(employer.employerImportId) : employer.employerImportId != null)
            return false;
        if (employerNo != null ? !employerNo.equals(employer.employerNo) : employer.employerNo != null) return false;
        if (faxNo != null ? !faxNo.equals(employer.faxNo) : employer.faxNo != null) return false;
        if (id != null ? !id.equals(employer.id) : employer.id != null) return false;
        if (importId != null ? !importId.equals(employer.importId) : employer.importId != null) return false;
        if (importRemark != null ? !importRemark.equals(employer.importRemark) : employer.importRemark != null)
            return false;
        if (importStatus != null ? !importStatus.equals(employer.importStatus) : employer.importStatus != null)
            return false;
        if (name != null ? !name.equals(employer.name) : employer.name != null) return false;
        if (phoneNo != null ? !phoneNo.equals(employer.phoneNo) : employer.phoneNo != null) return false;
        if (phoneNo2 != null ? !phoneNo2.equals(employer.phoneNo2) : employer.phoneNo2 != null) return false;
        if (phoneNo3 != null ? !phoneNo3.equals(employer.phoneNo3) : employer.phoneNo3 != null) return false;
        if (website != null ? !website.equals(employer.website) : employer.website != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (employerCode != null ? employerCode.hashCode() : 0);
        result = 31 * result + (employerNo != null ? employerNo.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNo != null ? phoneNo.hashCode() : 0);
        result = 31 * result + (phoneNo2 != null ? phoneNo2.hashCode() : 0);
        result = 31 * result + (phoneNo3 != null ? phoneNo3.hashCode() : 0);
        result = 31 * result + (faxNo != null ? faxNo.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (employerImportId != null ? employerImportId.hashCode() : 0);
        result = 31 * result + (importId != null ? importId.hashCode() : 0);
        result = 31 * result + (importStatus != null ? importStatus.hashCode() : 0);
        result = 31 * result + (importRemark != null ? importRemark.hashCode() : 0);
        return result;
    }
}
