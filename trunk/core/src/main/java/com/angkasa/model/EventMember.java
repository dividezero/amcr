package com.angkasa.model;

import com.angkasa.Constants;
import com.google.gson.annotations.Expose;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "event_member")
@Indexed
@XmlRootElement
public class EventMember extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private String designation = Constants.DESIGNATION_MR;
    @Expose
    private SimpleAddress address = new SimpleAddress();
    @Expose
    private String icNumber;
    @Expose
    private String phoneNo;
    @Expose
    private String phoneNoOffice;
    @Expose
    private String phoneNoMobile;
    @Expose
    private String email;
    @Expose
    private String maritalStatus;
    @Expose
    private String race;
    @Expose
    private Date dateOfBirth;

    @Expose
    private Long eventId;
    private Event event;


    public EventMember() {

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

    @Column(name = "name", nullable = false, length = 200)
    @Field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "designation", length = 10)
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Embedded
    @IndexedEmbedded
    public SimpleAddress getAddress() {
        return address;
    }

    public void setAddress(SimpleAddress address) {
        this.address = address;
    }

    @Column(name = "icNumber", nullable = false, length = 20, unique = true)
    @Field
    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    @Column(name = "phoneNo", nullable = true, length = 12)
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "phonoNo_office")
    public String getPhoneNoOffice() {
        return phoneNoOffice;
    }

    public void setPhoneNoOffice(String phoneNoOffice) {
        this.phoneNoOffice = phoneNoOffice;
    }

    @Column(name = "phonoNo_mobile")
    public String getPhoneNoMobile() {
        return phoneNoMobile;
    }

    public void setPhoneNoMobile(String phoneNoMobile) {
        this.phoneNoMobile = phoneNoMobile;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "marital_status")
    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Column(name = "race")
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Column(name = "date_of_birth", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "event_id", nullable = false)
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", address=" + address +
                ", icNumber='" + icNumber + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", phoneNoOffice='" + phoneNoOffice + '\'' +
                ", phoneNoMobile='" + phoneNoMobile + '\'' +
                ", email='" + email + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", race='" + race + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", eventId=" + eventId +
                ", event=" + event +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventMember that = (EventMember) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (designation != null ? !designation.equals(that.designation) : that.designation != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (event != null ? !event.equals(that.event) : that.event != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (icNumber != null ? !icNumber.equals(that.icNumber) : that.icNumber != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (maritalStatus != null ? !maritalStatus.equals(that.maritalStatus) : that.maritalStatus != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (phoneNo != null ? !phoneNo.equals(that.phoneNo) : that.phoneNo != null) return false;
        if (phoneNoMobile != null ? !phoneNoMobile.equals(that.phoneNoMobile) : that.phoneNoMobile != null)
            return false;
        if (phoneNoOffice != null ? !phoneNoOffice.equals(that.phoneNoOffice) : that.phoneNoOffice != null)
            return false;
        if (race != null ? !race.equals(that.race) : that.race != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (icNumber != null ? icNumber.hashCode() : 0);
        result = 31 * result + (phoneNo != null ? phoneNo.hashCode() : 0);
        result = 31 * result + (phoneNoOffice != null ? phoneNoOffice.hashCode() : 0);
        result = 31 * result + (phoneNoMobile != null ? phoneNoMobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (maritalStatus != null ? maritalStatus.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }
}
