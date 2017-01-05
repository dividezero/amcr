package com.angkasa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "coop_member")
@Indexed
@XmlRootElement
public class CoopMember extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    private Long id;
    private Long coopId;
    private Coop coop;
    private Long memberId;
    private Member member;

    private String name;
    private String icNumber;
    private String membershipAknNo;
    private String memberHexNo;
    private Date startDate;
    private Date endDate;

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


    @Column(name = "coop_id", nullable = false)
    public Long getCoopId() {
        return coopId;
    }

    public void setCoopId(Long coopId) {
        this.coopId = coopId;
    }

    @ManyToOne
    @JoinColumn(name = "coop_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Coop getCoop() {
        return coop;
    }

    public void setCoop(Coop coop) {
        this.coop = coop;
    }

    @Column(name = "member_id", nullable = false)
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

    @Column(name = "name", nullable = false)
    @Field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "icNumber", nullable = false)
    @Field
    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    @Column(name = "membership_akn_no", length = 30)
    @Field
    public String getMembershipAknNo() {
        return membershipAknNo;
    }

    public void setMembershipAknNo(String membershipAknNo) {
        this.membershipAknNo = membershipAknNo;
    }

    @Column(name = "start_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "member_hex_no", length = 30)
    public String getMemberHexNo() {
        return memberHexNo;
    }

    public void setMemberHexNo(String memberHexNo) {
        this.memberHexNo = memberHexNo;
    }

    @Override
    public String toString() {
        return "CoopMember{" +
                "id=" + id +
                ", coopId=" + coopId +
                ", coop=" + coop +
                ", memberId=" + memberId +
                ", member=" + member +
                ", name='" + name + '\'' +
                ", icNumber='" + icNumber + '\'' +
                ", membershipAknNo='" + membershipAknNo + '\'' +
                ", memberHexNo='" + memberHexNo + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoopMember that = (CoopMember) o;

        if (coop != null ? !coop.equals(that.coop) : that.coop != null) return false;
        if (coopId != null ? !coopId.equals(that.coopId) : that.coopId != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (icNumber != null ? !icNumber.equals(that.icNumber) : that.icNumber != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (member != null ? !member.equals(that.member) : that.member != null) return false;
        if (memberHexNo != null ? !memberHexNo.equals(that.memberHexNo) : that.memberHexNo != null) return false;
        if (memberId != null ? !memberId.equals(that.memberId) : that.memberId != null) return false;
        if (membershipAknNo != null ? !membershipAknNo.equals(that.membershipAknNo) : that.membershipAknNo != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (coopId != null ? coopId.hashCode() : 0);
        result = 31 * result + (coop != null ? coop.hashCode() : 0);
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        result = 31 * result + (member != null ? member.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (icNumber != null ? icNumber.hashCode() : 0);
        result = 31 * result + (membershipAknNo != null ? membershipAknNo.hashCode() : 0);
        result = 31 * result + (memberHexNo != null ? memberHexNo.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
