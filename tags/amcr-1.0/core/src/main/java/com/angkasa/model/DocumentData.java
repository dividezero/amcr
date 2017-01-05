/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angkasa.model;

import org.hibernate.annotations.Index;

import javax.persistence.*;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "document_data")
public class DocumentData extends BaseObject {

    private static final long serialVersionUID = -6487165420401302575L;
    private Long id;
    private String data;
    private Document document;

    @OneToOne
    @JoinColumn(name = "document_id")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocumentData other = (DocumentData) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DocumentData{" + "id=" + id + '}';
    }


    @Override
    @Transient
    public String getPrimaryKey() {
        return getStringKey(this.id);
    }

}
