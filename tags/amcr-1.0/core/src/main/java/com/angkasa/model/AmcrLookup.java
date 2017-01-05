package com.angkasa.model;

import com.angkasa.util.StringUtil;
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
@Table(name = "amcr_lookup")
@XmlRootElement
public class AmcrLookup extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    public static String TABLE_COOP = "COOP";
    public static String TABLE_MEMBER = "MEMBER";

    private Long id;
    private String tableName;
    private String head;
    private Integer tail;
    private Integer tailLength;
    private String tailPad;

    public AmcrLookup() {

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

    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "head")
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Column(name = "tail")
    public Integer getTail() {
        return tail;
    }

    public void setTail(Integer tail) {
        this.tail = tail;
    }

    @Column(name = "tail_length")
    public Integer getTailLength() {
        return tailLength;
    }

    public void setTailLength(Integer tailLength) {
        this.tailLength = tailLength;
    }

    @Column(name = "tail_pad")
    public String getTailPad() {
        return tailPad;
    }

    public void setTailPad(String tailPad) {
        this.tailPad = tailPad;
    }

    @Transient
    public void increaseTail() {
        tail++;
    }

    @Transient
    public String getCode() {
        return head + StringUtil.leftPad(tail.toString(), tailPad, tailLength);
    }

    @Override
    public String toString() {
        return "AmcrLookup{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", head='" + head + '\'' +
                ", tail=" + tail +
                ", tailLength=" + tailLength +
                ", tailPad='" + tailPad + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmcrLookup that = (AmcrLookup) o;

        if (head != null ? !head.equals(that.head) : that.head != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (tail != null ? !tail.equals(that.tail) : that.tail != null) return false;
        if (tailLength != null ? !tailLength.equals(that.tailLength) : that.tailLength != null) return false;
        if (tailPad != null ? !tailPad.equals(that.tailPad) : that.tailPad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (head != null ? head.hashCode() : 0);
        result = 31 * result + (tail != null ? tail.hashCode() : 0);
        result = 31 * result + (tailLength != null ? tailLength.hashCode() : 0);
        result = 31 * result + (tailPad != null ? tailPad.hashCode() : 0);
        return result;
    }
}
