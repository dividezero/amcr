package com.angkasa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Resolution;


/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode().
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@MappedSuperclass
public abstract class BaseObject implements Serializable {

    protected String createdBy;
    protected Date createdTime;
    protected String modifiedBy;
    protected Date modifiedTime;

    @Transient
    public abstract String getPrimaryKey();

    protected String getStringKey(Object obj) {
        if (obj == null)
            return null;
        else
            return obj.toString();
    }

    @Column(name = "createdBy", nullable = true, length = 50)
    @Field
    public String getCreatedBy() {
        return createdBy;
    }

    @Column(name = "createdTime", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Field
    @DateBridge(resolution = Resolution.DAY)
    public Date getCreatedTime() {
        return createdTime;
    }

    @Column(name = "modifiedBy", nullable = true, length = 50)
    @Field
    public String getModifiedBy() {
        return modifiedBy;
    }

    @Column(name = "modifiedTime", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Field
    @DateBridge(resolution = Resolution.DAY)
    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }


    /**
     * Returns a multi-line String with key=value pairs.
     *
     * @return a String representation of this class.
     */
    public abstract String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     *
     * @param o object to compare to
     * @return true/false based on equality tests
     */
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     *
     * @return hashCode
     */
    public abstract int hashCode();
}
