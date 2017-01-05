package com.angkasa.model;

import com.angkasa.util.JsonUtil;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "employer_import")
@Indexed
@XmlRootElement
public class EmployerImport extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILED = "FAILED";

    private Long id;

    private String documentId;
    private Document document;

    private String name;

    private String employerJson;

    private byte[] file;

    private String status;

    public EmployerImport() {

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

    @Column(name = "name", nullable = false)
    @Field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //TODO might want to set this as false
    @Column(name = "document_id", nullable = true)
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Lob
    public String getEmployerJson() {
        return employerJson;
    }

    public void setEmployerJson(String employerJson) {
        this.employerJson = employerJson;
    }

    @Transient
    public List<Employer> getEmployerList() {
        return JsonUtil.parseJsonToEmployerList(this.employerJson);
    }

    @Transient
    public void setEmployerList(List<Employer> employerList) {
        setEmployerJson(JsonUtil.convertEmployerListToJson(employerList));
    }

    @Transient
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployerImport that = (EmployerImport) o;

        if (document != null ? !document.equals(that.document) : that.document != null) return false;
        if (documentId != null ? !documentId.equals(that.documentId) : that.documentId != null) return false;
        if (employerJson != null ? !employerJson.equals(that.employerJson) : that.employerJson != null) return false;
        if (!Arrays.equals(file, that.file)) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (employerJson != null ? employerJson.hashCode() : 0);
        result = 31 * result + (file != null ? Arrays.hashCode(file) : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmployerImport{" +
                "id=" + id +
                ", documentId='" + documentId + '\'' +
                ", document=" + document +
                ", name='" + name + '\'' +
                ", employerJson='" + employerJson + '\'' +
                ", file=" + Arrays.toString(file) +
                ", status='" + status + '\'' +
                '}';
    }
}
