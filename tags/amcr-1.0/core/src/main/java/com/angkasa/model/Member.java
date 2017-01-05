package com.angkasa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.angkasa.Constants;
import com.google.gson.annotations.Expose;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "member")
@Indexed
@XmlRootElement
public class Member extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359516L;

    private Long id;
    @Expose
    private String name;
    @Expose
    private String designation = Constants.DESIGNATION_MR;
    @Expose
    private String description;
    @Expose
    private String amcrCode;
    //private Address address = new Address();
    @Expose
    private SimpleAddress address = new SimpleAddress();
    @Expose
    private String phoneNo;
    private String phoneNo2;
    @Expose
    private String phoneNoOffice;
    @Expose
    private String phoneNoMobile;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String maritalStatus;
    @Expose
    private String race;
    @Expose
    private String preferredLanguage;
    @Expose
    private Date dateOfBirth;
    @Expose
    private String gender; // Constants.GENDER_MALE or Constants.GENDER_FEMALE
    @Expose
    private String icNumber;
    @Expose
    private String membershipNo;
    @Expose
    private String activeMembershipBinNo;
    @Expose
    private String coinsBinNo;
    @Expose
    private String coinsAccountNo;
    @Expose
    private Long coinsBankId;
    @Expose
    private Bank coinsBank;

    @Expose
    private String typeFlag = Constants.MEMBER_FLAG_NEW;
    @Expose
    private boolean preloaded = false;
    @Expose
    private String status = Constants.MEMBER_STATUS_ACTIVE;
    private boolean enabled = true;

    private Integer version;
    private Set<CoopMember> coopMembers = new HashSet<CoopMember>();

    @Expose
    private Long userId;
    @Expose
    private User user;

    // Transient
    private Long coopId;

    // Import stuff
    @Expose
    private String memberImportId;
    @Expose
    private String importId;
    @Expose
    private String importStatus;
    @Expose
    private String importRemark;
    @Expose
    private String importEmployerCode;
    @Expose
    private String importCoopCode;


    public Member() {

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

    @Column(name = "name", nullable = false, length = 50)
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

    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "amcrCode", unique = true, nullable = true)
    public String getAmcrCode() {
        return amcrCode;
    }

    public void setAmcrCode(String amcrCode) {
        this.amcrCode = amcrCode;
    }

    @Embedded
    @IndexedEmbedded
    public SimpleAddress getAddress() {
        return address;
    }

    public void setAddress(SimpleAddress address) {
        this.address = address;
    }

    @Column(name = "phoneNo", nullable = true, length = 12)
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "phoneNo2", nullable = true, length = 12)
    public String getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    @Column(name = "gender", nullable = true, length = 1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "icNumber", nullable = false, length = 20, unique = true)
    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    @Column(name = "membershipNo", nullable = true, length = 20)
    public String getMembershipNo() {
        return membershipNo;
    }

    public void setMembershipNo(String membershipNo) {
        this.membershipNo = membershipNo;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column
    @Field
    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    @Column(name = "type_flag", nullable = false, unique = false, length = 50)
    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    @Column(name = "preloaded")
    public boolean isPreloaded() {
        return preloaded;
    }

    public void setPreloaded(boolean preloaded) {
        this.preloaded = preloaded;
    }

    @Column(name = "active_membership_bin_no")
    public String getActiveMembershipBinNo() {
        return activeMembershipBinNo;
    }

    public void setActiveMembershipBinNo(String activeMembershipBinNo) {
        this.activeMembershipBinNo = activeMembershipBinNo;
    }

    @Column(name = "coins_bin_no")
    public String getCoinsBinNo() {
        return coinsBinNo;
    }

    public void setCoinsBinNo(String coinsBinNo) {
        this.coinsBinNo = coinsBinNo;
    }

    @Column(name = "coins_account_no")
    public String getCoinsAccountNo() {
        return coinsAccountNo;
    }

    public void setCoinsAccountNo(String coinsAccountNo) {
        this.coinsAccountNo = coinsAccountNo;
    }

    @Column(name = "coins_bank_id")
    public Long getCoinsBankId() {
        return coinsBankId;
    }

    public void setCoinsBankId(Long coinsBankId) {
        this.coinsBankId = coinsBankId;
    }

    @ManyToOne
    @JoinColumn(name = "coins_bank_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Bank getCoinsBank() {
        return coinsBank;
    }

    public void setCoinsBank(Bank coinsBank) {
        this.coinsBank = coinsBank;
    }

    @OneToMany(targetEntity = CoopMember.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    public Set<CoopMember> getCoopMembers() {
        return coopMembers;
    }

    public void setCoopMembers(Set<CoopMember> coopMembers) {
        this.coopMembers = coopMembers;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Transient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public Long getCoopId() {
        return coopId;
    }

    public void setCoopId(Long coopId) {
        this.coopId = coopId;
    }

    @Transient
    public String getImportStatus() {
        return importStatus;
    }

    @Transient
    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    @Transient
    public void addImportRemark(String remark) {
        if (importRemark == null) {
            importRemark = "";
        } else {
            importRemark += ", ";
        }
        importRemark = importRemark + remark;
    }

    @Transient
    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    @Transient
    public String getMemberImportId() {
        return memberImportId;
    }

    public void setMemberImportId(String memberImportId) {
        this.memberImportId = memberImportId;
    }

    @Transient
    public String getImportRemark() {
        return importRemark;
    }

    @Transient
    public void setImportRemark(String importRemark) {
        this.importRemark = importRemark;
    }

    @Transient
    public String getImportEmployerCode() {
        return importEmployerCode;
    }

    @Transient
    public void setImportEmployerCode(String importEmployerCode) {
        this.importEmployerCode = importEmployerCode;
    }

    @Transient
    public String getImportCoopCode() {
        return importCoopCode;
    }

    @Transient
    public void setImportCoopCode(String importCoopCode) {
        this.importCoopCode = importCoopCode;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Column(name = "preffered_langage")
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    @Column(name = "date_of_birth", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                ", amcrCode='" + amcrCode + '\'' +
                ", address=" + address +
                ", phoneNo='" + phoneNo + '\'' +
                ", phoneNo2='" + phoneNo2 + '\'' +
                ", phoneNoOffice='" + phoneNoOffice + '\'' +
                ", phoneNoMobile='" + phoneNoMobile + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", race='" + race + '\'' +
                ", preferredLanguage='" + preferredLanguage + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", icNumber='" + icNumber + '\'' +
                ", membershipNo='" + membershipNo + '\'' +
                ", activeMembershipBinNo='" + activeMembershipBinNo + '\'' +
                ", coinsBinNo='" + coinsBinNo + '\'' +
                ", coinsAccountNo='" + coinsAccountNo + '\'' +
                ", coinsBankId=" + coinsBankId +
                ", coinsBank=" + coinsBank +
                ", typeFlag='" + typeFlag + '\'' +
                ", preloaded=" + preloaded +
                ", status='" + status + '\'' +
                ", enabled=" + enabled +
                ", version=" + version +
                ", coopMembers=" + coopMembers +
                ", userId=" + userId +
                ", user=" + user +
                ", coopId=" + coopId +
                ", memberImportId='" + memberImportId + '\'' +
                ", importId='" + importId + '\'' +
                ", importStatus='" + importStatus + '\'' +
                ", importRemark='" + importRemark + '\'' +
                ", importEmployerCode='" + importEmployerCode + '\'' +
                ", importCoopCode='" + importCoopCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (enabled != member.enabled) return false;
        if (preloaded != member.preloaded) return false;
        if (activeMembershipBinNo != null ? !activeMembershipBinNo.equals(member.activeMembershipBinNo) : member.activeMembershipBinNo != null)
            return false;
        if (address != null ? !address.equals(member.address) : member.address != null) return false;
        if (amcrCode != null ? !amcrCode.equals(member.amcrCode) : member.amcrCode != null) return false;
        if (coinsAccountNo != null ? !coinsAccountNo.equals(member.coinsAccountNo) : member.coinsAccountNo != null)
            return false;
        if (coinsBank != null ? !coinsBank.equals(member.coinsBank) : member.coinsBank != null) return false;
        if (coinsBankId != null ? !coinsBankId.equals(member.coinsBankId) : member.coinsBankId != null) return false;
        if (coinsBinNo != null ? !coinsBinNo.equals(member.coinsBinNo) : member.coinsBinNo != null) return false;
        if (coopId != null ? !coopId.equals(member.coopId) : member.coopId != null) return false;
        if (coopMembers != null ? !coopMembers.equals(member.coopMembers) : member.coopMembers != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(member.dateOfBirth) : member.dateOfBirth != null) return false;
        if (description != null ? !description.equals(member.description) : member.description != null) return false;
        if (designation != null ? !designation.equals(member.designation) : member.designation != null) return false;
        if (email != null ? !email.equals(member.email) : member.email != null) return false;
        if (email2 != null ? !email2.equals(member.email2) : member.email2 != null) return false;
        if (gender != null ? !gender.equals(member.gender) : member.gender != null) return false;
        if (icNumber != null ? !icNumber.equals(member.icNumber) : member.icNumber != null) return false;
        if (id != null ? !id.equals(member.id) : member.id != null) return false;
        if (importCoopCode != null ? !importCoopCode.equals(member.importCoopCode) : member.importCoopCode != null)
            return false;
        if (importEmployerCode != null ? !importEmployerCode.equals(member.importEmployerCode) : member.importEmployerCode != null)
            return false;
        if (importId != null ? !importId.equals(member.importId) : member.importId != null) return false;
        if (importRemark != null ? !importRemark.equals(member.importRemark) : member.importRemark != null)
            return false;
        if (importStatus != null ? !importStatus.equals(member.importStatus) : member.importStatus != null)
            return false;
        if (maritalStatus != null ? !maritalStatus.equals(member.maritalStatus) : member.maritalStatus != null)
            return false;
        if (memberImportId != null ? !memberImportId.equals(member.memberImportId) : member.memberImportId != null)
            return false;
        if (membershipNo != null ? !membershipNo.equals(member.membershipNo) : member.membershipNo != null)
            return false;
        if (name != null ? !name.equals(member.name) : member.name != null) return false;
        if (phoneNo != null ? !phoneNo.equals(member.phoneNo) : member.phoneNo != null) return false;
        if (phoneNo2 != null ? !phoneNo2.equals(member.phoneNo2) : member.phoneNo2 != null) return false;
        if (phoneNoMobile != null ? !phoneNoMobile.equals(member.phoneNoMobile) : member.phoneNoMobile != null)
            return false;
        if (phoneNoOffice != null ? !phoneNoOffice.equals(member.phoneNoOffice) : member.phoneNoOffice != null)
            return false;
        if (preferredLanguage != null ? !preferredLanguage.equals(member.preferredLanguage) : member.preferredLanguage != null)
            return false;
        if (race != null ? !race.equals(member.race) : member.race != null) return false;
        if (status != null ? !status.equals(member.status) : member.status != null) return false;
        if (typeFlag != null ? !typeFlag.equals(member.typeFlag) : member.typeFlag != null) return false;
        if (user != null ? !user.equals(member.user) : member.user != null) return false;
        if (userId != null ? !userId.equals(member.userId) : member.userId != null) return false;
        if (version != null ? !version.equals(member.version) : member.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (amcrCode != null ? amcrCode.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNo != null ? phoneNo.hashCode() : 0);
        result = 31 * result + (phoneNo2 != null ? phoneNo2.hashCode() : 0);
        result = 31 * result + (phoneNoOffice != null ? phoneNoOffice.hashCode() : 0);
        result = 31 * result + (phoneNoMobile != null ? phoneNoMobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (maritalStatus != null ? maritalStatus.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (preferredLanguage != null ? preferredLanguage.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (icNumber != null ? icNumber.hashCode() : 0);
        result = 31 * result + (membershipNo != null ? membershipNo.hashCode() : 0);
        result = 31 * result + (activeMembershipBinNo != null ? activeMembershipBinNo.hashCode() : 0);
        result = 31 * result + (coinsBinNo != null ? coinsBinNo.hashCode() : 0);
        result = 31 * result + (coinsAccountNo != null ? coinsAccountNo.hashCode() : 0);
        result = 31 * result + (coinsBankId != null ? coinsBankId.hashCode() : 0);
        result = 31 * result + (coinsBank != null ? coinsBank.hashCode() : 0);
        result = 31 * result + (typeFlag != null ? typeFlag.hashCode() : 0);
        result = 31 * result + (preloaded ? 1 : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (coopMembers != null ? coopMembers.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (coopId != null ? coopId.hashCode() : 0);
        result = 31 * result + (memberImportId != null ? memberImportId.hashCode() : 0);
        result = 31 * result + (importId != null ? importId.hashCode() : 0);
        result = 31 * result + (importStatus != null ? importStatus.hashCode() : 0);
        result = 31 * result + (importRemark != null ? importRemark.hashCode() : 0);
        result = 31 * result + (importEmployerCode != null ? importEmployerCode.hashCode() : 0);
        result = 31 * result + (importCoopCode != null ? importCoopCode.hashCode() : 0);
        return result;
    }
}
