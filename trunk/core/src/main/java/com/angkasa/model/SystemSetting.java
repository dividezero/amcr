package com.angkasa.model;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "system_setting")
@org.hibernate.annotations.Table(appliesTo = "system_setting",
        indexes = {@Index(name = "IDX_SS_PN", columnNames = {"propertyName"})})
@Indexed
public class SystemSetting extends BaseObject implements Serializable {

    private static final long serialVersionUID = -892307371528192728L;
    /**
     * Unique system id
     * - NUMBER(20)
     */
    private Long id;
    private String propertyName;
    private String displayName;
    private String propertyValue;
    private String description;

    private Boolean isBooleanSetting = Boolean.FALSE;

    @Override
    @Transient
    public String getPrimaryKey() {
        return getStringKey(this.id);
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * A user input name. Should follow the following convention: [module].[unique name]
     * e.g. it.userNamePattern
     * @return
     */
    @Column(nullable = false, unique = true)
    @Field
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * A value with a limited length. using the username pattern as an example: ^[a-z0-9_-]{3,15}$
     * @return
     */
    @Column(nullable = true, length = 2000)
    @Field
    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    /**
     * A short description about this property
     * @return
     */
    @Column(nullable = true, length = 250)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "is_boolean_setting")
    public Boolean getIsBooleanSetting() {
        return isBooleanSetting;
    }

    public void setIsBooleanSetting(Boolean isBooleanSetting) {
        this.isBooleanSetting = isBooleanSetting;
    }

    @Override
    public String toString() {
        return "SystemSetting{" +
                "id=" + id +
                ", propertyName='" + propertyName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", propertyValue='" + propertyValue + '\'' +
                ", description='" + description + '\'' +
                ", isBooleanSetting=" + isBooleanSetting +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemSetting that = (SystemSetting) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isBooleanSetting != null ? !isBooleanSetting.equals(that.isBooleanSetting) : that.isBooleanSetting != null)
            return false;
        if (propertyName != null ? !propertyName.equals(that.propertyName) : that.propertyName != null) return false;
        if (propertyValue != null ? !propertyValue.equals(that.propertyValue) : that.propertyValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (propertyName != null ? propertyName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (propertyValue != null ? propertyValue.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isBooleanSetting != null ? isBooleanSetting.hashCode() : 0);
        return result;
    }
}
