package com.angkasa.model;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "documents")
@Indexed
public class Document extends BaseObject implements Serializable {
    private static final long serialVersionUID = -6470655419530941819L;

    private String id;
    private String moduleName;
    private String modulePrimKey;
    private String fileRelativePath;
    private String contentType;
    private String openField;
    private String description;
    private DocumentData documentData;
    private Date paySlipMonth;

    /**
     * Unique system id
     * - VARCHAR2(36 CHAR)
     */
    @Id
    @Column(length = 36)
    public String getId() {
        return id;
    }

    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public DocumentData getDocumentData() {
        return documentData;
    }

    public void setDocumentData(DocumentData documentData) {
        this.documentData = documentData;
    }


    /**
     * The module of this document belongs to
     * - VARCHAR2(50 CHAR)
     */
    @Column(nullable = false, length = 100)
    @Field
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Referring to unique system id of the module's record that created this document
     * - VARCHAR2(100 CHAR)
     */
    @Column(nullable = false, length = 100)
    public String getModulePrimKey() {
        return modulePrimKey;
    }

    /**
     * Actual path for the file located
     * - VARCHAR2(250 CHAR)
     */
    @Column(nullable = false, length = 250)
    @Field
    @Deprecated
    public String getFileRelativePath() {
        return fileRelativePath;
    }

    /**
     * A field that can be use for additional data
     */
    @Column(nullable = true, length = 250)
    public String getOpenField() {
        return openField;
    }

    /**
     * Content type for the document uploaded
     * - VARCHAR2(100 CHAR)
     */
    @Column(nullable = false, length = 100)
    public String getContentType() {
        return contentType;
    }

    /**
     * Description for the file
     */
    @Column(nullable = true, length = 250)
    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Transient
    public String getFilename() {
        if (fileRelativePath != null) {
            return fileRelativePath.substring(fileRelativePath.lastIndexOf(File.separator) + 1);
        }
        return "";
    }

    @Transient
    public String getParentPath() {
        if (fileRelativePath != null) {
            return fileRelativePath.substring(0, fileRelativePath.lastIndexOf(File.separator));
        }
        return "";
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setModulePrimKey(String modulePrimKey) {
        this.modulePrimKey = modulePrimKey;
    }

    public void setFileRelativePath(String fileRelativePath) {
        this.fileRelativePath = fileRelativePath;
    }

    public void setOpenField(String openField) {
        this.openField = openField;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (!contentType.equals(document.contentType)) return false;
        if (!fileRelativePath.equals(document.fileRelativePath)) return false;
        if (!id.equals(document.id)) return false;
        if (!moduleName.equals(document.moduleName)) return false;
        if (!modulePrimKey.equals(document.modulePrimKey)) return false;
        if (openField != null ? !openField.equals(document.openField) : document.openField != null) return false;

        return true;
    }

    @Column(nullable = true)
    public Date getPaySlipMonth() {
        return paySlipMonth;
    }

    public void setPaySlipMonth(Date paySlipMonth) {
        this.paySlipMonth = paySlipMonth;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + moduleName.hashCode();
        result = 31 * result + modulePrimKey.hashCode();
        result = 31 * result + fileRelativePath.hashCode();
        result = 31 * result + contentType.hashCode();
        result = 31 * result + (openField != null ? openField.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", modulePrimKey='" + modulePrimKey + '\'' +
                ", fileRelativePath='" + fileRelativePath + '\'' +
                ", description='" + description + '\'' +
                ", contentType='" + contentType + '\'' +
                ", openField='" + openField + '\'' +
                '}';
    }

    @Override
    @Transient
    public String getPrimaryKey() {
        return getStringKey(this.id);
    }
}
