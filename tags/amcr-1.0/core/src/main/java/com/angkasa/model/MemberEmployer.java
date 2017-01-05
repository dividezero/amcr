package com.angkasa.model;

import com.angkasa.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "member_employer")
//@Indexed
@XmlRootElement
public class MemberEmployer extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    private Long id;
    private Date startDate;
    private Date endDate;

    private Long memberId;
    private Member member;
    private Long employerId;
    private Employer employer;

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

    @Column(name = "member_id", nullable = true)
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "employer_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Column(name = "employer_id", nullable = true)
    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    @Column(name = "start_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
//    @Field
    @DateBridge(resolution = Resolution.DAY)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
//    @Field
    @DateBridge(resolution = Resolution.DAY)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Transient
    public boolean isActive() {
        return isActive(DateUtil.getDateToday());
    }

    @Transient
    public boolean isActive(Date date) {
        if (date.after(startDate)) {
            if (endDate == null || date.before(endDate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberEmployer that = (MemberEmployer) o;

        if (employer != null ? !employer.equals(that.employer) : that.employer != null) return false;
        if (employerId != null ? !employerId.equals(that.employerId) : that.employerId != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (member != null ? !member.equals(that.member) : that.member != null) return false;
        if (memberId != null ? !memberId.equals(that.memberId) : that.memberId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        result = 31 * result + (member != null ? member.hashCode() : 0);
        result = 31 * result + (employerId != null ? employerId.hashCode() : 0);
        result = 31 * result + (employer != null ? employer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MemberEmployer{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", memberId=" + memberId +
                ", member=" + member +
                ", employerId=" + employerId +
                ", employer=" + employer +
                '}';
    }
}
