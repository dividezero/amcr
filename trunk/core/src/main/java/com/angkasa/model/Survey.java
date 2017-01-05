package com.angkasa.model;

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
@Table(name = "survey")
@Indexed
@XmlRootElement
public class Survey extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359516L;

    private Long id;
    private String name;
    private String description;

    private Date startDate;
    private Date endDate;

    private Integer version;

    public Survey() {

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

    @Column(name = "startDate", nullable = true)
//    @Field
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "endDate", nullable = true)
//    @Field
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        if (description != null ? !description.equals(survey.description) : survey.description != null) return false;
        if (endDate != null ? !endDate.equals(survey.endDate) : survey.endDate != null) return false;
        if (id != null ? !id.equals(survey.id) : survey.id != null) return false;
        if (name != null ? !name.equals(survey.name) : survey.name != null) return false;
        if (startDate != null ? !startDate.equals(survey.startDate) : survey.startDate != null) return false;
        if (version != null ? !version.equals(survey.version) : survey.version != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", version=" + version +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

}
