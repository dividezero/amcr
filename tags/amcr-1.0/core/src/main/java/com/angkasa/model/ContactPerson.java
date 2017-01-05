package com.angkasa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "contact_person")
@Indexed
@XmlRootElement
public class ContactPerson extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    private Long id;
    private String name;
    private String description;
    private String email;
    private String workPhoneNo;
    private Integer version;
    private Long coopId;

    public ContactPerson() {

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

    @Column(name = "name", nullable = false, length = 50)
    @Field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "email", nullable = false, length = 50)
    @Field
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "workPhone", nullable = false, length = 12)
    public String getWorkPhoneNo() {
        return workPhoneNo;
    }

    public void setWorkPhoneNo(String workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }

    @Column(name = "coop_id", nullable = true)
    public Long getCoopId() {
        return coopId;
    }

    public void setCoopId(Long coopId) {
        this.coopId = coopId;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", workPhoneNo='" + workPhoneNo + '\'' +
                ", version=" + version +
                ", coopId=" + coopId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactPerson that = (ContactPerson) o;

        if (coopId != null ? !coopId.equals(that.coopId) : that.coopId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (workPhoneNo != null ? !workPhoneNo.equals(that.workPhoneNo) : that.workPhoneNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (workPhoneNo != null ? workPhoneNo.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (coopId != null ? coopId.hashCode() : 0);
        return result;
    }
}
