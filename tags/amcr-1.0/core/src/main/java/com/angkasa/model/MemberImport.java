package com.angkasa.model;

import com.angkasa.util.JsonUtil;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

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
@Table(name = "member_import")
@Indexed
@XmlRootElement
public class MemberImport extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    public static final String STATUS_NEW = "NEW";
    public static final String STATUS_VALIDATION = "VALIDATION";
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_PARTIAL_SUCCESS = "PARTIAL_SUCCESS";
    public static final String STATUS_FAILED = "FAILED";

    private Long id;

    private String documentId;
    private Document document;

    private String name;
    private Long coopId;

    private Integer noOfColumns;

    private String memberJson;



    private byte[] file;

    private String status = STATUS_NEW;

    public MemberImport() {

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

    @Column(name = "coop_id")
    public Long getCoopId() {
        return coopId;
    }

    public void setCoopId(Long coopId) {
        this.coopId = coopId;
    }

    @Column
    public Integer getNoOfColumns() {
        return noOfColumns;
    }

    public void setNoOfColumns(Integer noOfColumns) {
        this.noOfColumns = noOfColumns;
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
    public String getMemberJson() {
        return this.memberJson;
    }

    public void setMemberJson(String memberJson) {
        this.memberJson = memberJson;
    }

    @Transient
    public List<Member> getMemberList() {
        return JsonUtil.parseJsonToMemberList(this.memberJson);
    }

    @Transient
    public List<Member> getMemberListByStatus(String searchStatus){
        List<Member> list = getMemberList();
        List<Member> result = new ArrayList<Member>();
        for(Member member : list){
            if(member.getImportStatus().equals(searchStatus)){
                result.add(member);
            }
        }
        return result;
    }

    @Transient
    public void setMemberList(List<Member> memberList) {
        setMemberJson(JsonUtil.convertMemberListToJson(memberList));
    }

    @Transient
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Column
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MemberImport{" +
                "id=" + id +
                ", documentId='" + documentId + '\'' +
                ", document=" + document +
                ", name='" + name + '\'' +
                ", coopId=" + coopId +
                ", memberJson='" + memberJson + '\'' +
                ", file=" + Arrays.toString(file) +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberImport that = (MemberImport) o;

        if (coopId != null ? !coopId.equals(that.coopId) : that.coopId != null) return false;
        if (document != null ? !document.equals(that.document) : that.document != null) return false;
        if (documentId != null ? !documentId.equals(that.documentId) : that.documentId != null) return false;
        if (!Arrays.equals(file, that.file)) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (memberJson != null ? !memberJson.equals(that.memberJson) : that.memberJson != null) return false;
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
        result = 31 * result + (coopId != null ? coopId.hashCode() : 0);
        result = 31 * result + (memberJson != null ? memberJson.hashCode() : 0);
        result = 31 * result + (file != null ? Arrays.hashCode(file) : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
