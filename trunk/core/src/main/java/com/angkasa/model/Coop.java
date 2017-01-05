package com.angkasa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.angkasa.Constants;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.StringUtils;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "coop")
@Indexed
@XmlRootElement
public class Coop extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    public static String MEMBER_HEX_RUNNING_NO_MIN = "0000";
    public static String MEMBER_HEX_RUNNING_NO_MAX = "FFFF";

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String amcrCode;
    //private Address address = new Address();
    @Expose
    private SimpleAddress address = new SimpleAddress();

    private Set<ContactPerson> contactPersons;

    @Expose
    private String phoneNo;
    @Expose
    private String phoneNo2;
    @Expose
    private String phoneNo3;

    @Expose
    private String faxNo;

    @Expose
    private String email;
    @Expose
    private String email2;

    @Expose
    private String coopCode;
    @Expose
    private String employerCode;
    @Expose
    private String memberHexRunningNo = MEMBER_HEX_RUNNING_NO_MIN;

    @Expose
    private Set<CoopBusinessType> coopBusinessTypes;

    @Expose
    private Long coopBusinessTypeId;
    private CoopBusinessType coopBusinessType;
    @Expose
    private Long coopBusinessType2Id;
    private CoopBusinessType coopBusinessType2;
    @Expose
    private Long coopBusinessType3Id;
    private CoopBusinessType coopBusinessType3;

    @Expose
    private String typeFlag = Constants.COOP_FLAG_NEW;
    private boolean preloaded = false;

    @Expose
    private Date incorporatedDate;

    @Expose
    private Date skmJoinDate;
    @Expose
    private Date angkasaJoinDate;
    @Expose
    private String function;
    @Expose
    private int memberTotal = 0;
    @Expose
    private int memberMale = 0;
    @Expose
    private int memberFemale = 0;

    @Expose
    private String skmRegistrationNo;
    @Expose
    private String bpaCode;

    private String website;

    private Integer version;
    private boolean enabled = true;

    private boolean createUser;
    private Long userId;
    private User user;





    // Import stuff
    @Expose
    private String coopImportId;
    @Expose
    private String importId;
    @Expose
    private String importStatus;
    @Expose
    private String importRemark;


    public Coop() {
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

    @Column(name = "description", nullable = true, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "amcrCode", nullable = true, unique = true)
    @Field
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

    @OneToMany(targetEntity = ContactPerson.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "contactPerson_id", updatable = false)
    public Set<ContactPerson> getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(Set<ContactPerson> contactPersons) {
        this.contactPersons = contactPersons;
    }

    @Column(name = "phoneNo", nullable = true, length = 20)
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "phoneNo2", nullable = true, length = 20)
    public String getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    @Column(name = "phoneNo3", nullable = true, length = 20)
    public String getPhoneNo3() {
        return phoneNo3;
    }

    public void setPhoneNo3(String phoneNo3) {
        this.phoneNo3 = phoneNo3;
    }

    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email2", nullable = true, length = 50)
    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    @Column(name = "coopCode", nullable = true, unique = true, length = 8)
    @Field
    public String getCoopCode() {
        return coopCode;
    }

    public void setCoopCode(String coopCode) {
        this.coopCode = coopCode;
    }

    @Column(name = "employer_code", nullable = true, unique = false, length = 20)
    @Field
    public String getEmployerCode() {
        return employerCode;
    }

    public void setEmployerCode(String employerCode) {
        this.employerCode = employerCode;
    }

    @Column(name = "member_hex_running_no", nullable = false, length = 4)
    public String getMemberHexRunningNo() {
        return memberHexRunningNo;
    }

    public void setMemberHexRunningNo(String memberHexRunningNo) {
        this.memberHexRunningNo = memberHexRunningNo;
    }

    @Transient
    public String getAndIncreaseMemberHexRunningNo() {
        boolean increased = false;
        String result = memberHexRunningNo;
        int decimal = Integer.parseInt(memberHexRunningNo, 16);
        decimal++;
        memberHexRunningNo = StringUtils.upperCase(Integer.toHexString(decimal));
        return this.amcrCode + result;
    }

    @ManyToMany()
    @JoinTable(name = "coop_coop_business_type", joinColumns = {
            @JoinColumn(name = "coop_id")}, inverseJoinColumns =
    @JoinColumn(name = "id"))
    public Set<CoopBusinessType> getCoopBusinessTypes() {
        return coopBusinessTypes;
    }

    public void setCoopBusinessTypes(Set<CoopBusinessType> coopBusinessTypes) {
        this.coopBusinessTypes = coopBusinessTypes;
    }

    @Column(name = "coop_business_type_id", nullable = true)
    public Long getCoopBusinessTypeId() {
        return coopBusinessTypeId;
    }

    public void setCoopBusinessTypeId(Long coopBusinessTypeId) {
        this.coopBusinessTypeId = coopBusinessTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "coop_business_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    public CoopBusinessType getCoopBusinessType() {
        return coopBusinessType;
    }

    public void setCoopBusinessType(CoopBusinessType coopBusinessType) {
        this.coopBusinessType = coopBusinessType;
    }

    @Column(name = "coop_business_type2_id", nullable = true)
    public Long getCoopBusinessType2Id() {
        return coopBusinessType2Id;
    }

    public void setCoopBusinessType2Id(Long coopBusinessType2Id) {
        this.coopBusinessType2Id = coopBusinessType2Id;
    }

    @ManyToOne
    @JoinColumn(name = "coop_business_type2_id", referencedColumnName = "id", insertable = false, updatable = false)
    public CoopBusinessType getCoopBusinessType2() {
        return coopBusinessType2;
    }

    public void setCoopBusinessType2(CoopBusinessType coopBusinessType2) {
        this.coopBusinessType2 = coopBusinessType2;
    }

    @Column(name = "coop_business_type3_id", nullable = true)
    public Long getCoopBusinessType3Id() {
        return coopBusinessType3Id;
    }

    public void setCoopBusinessType3Id(Long coopBusinessType3Id) {
        this.coopBusinessType3Id = coopBusinessType3Id;
    }

    @ManyToOne
    @JoinColumn(name = "coop_business_type3_id", referencedColumnName = "id", insertable = false, updatable = false)
    public CoopBusinessType getCoopBusinessType3() {
        return coopBusinessType3;
    }

    public void setCoopBusinessType3(CoopBusinessType coopBusinessType3) {
        this.coopBusinessType3 = coopBusinessType3;
    }

    @Column(name = "incorporatedDate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getIncorporatedDate() {
        return incorporatedDate;
    }

    public void setIncorporatedDate(Date incorporatedDate) {
        this.incorporatedDate = incorporatedDate;
    }

    @Column(name = "type_flag", nullable = false, unique = false, length = 50)
    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
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

    @Column(name = "preloaded")
    public boolean isPreloaded() {
        return preloaded;
    }

    public void setPreloaded(boolean preloaded) {
        this.preloaded = preloaded;
    }

    @Column(name = "fax_no", length = 20)
    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    @Column(name = "skm_join_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getSkmJoinDate() {
        return skmJoinDate;
    }

    public void setSkmJoinDate(Date skmJoinDate) {
        this.skmJoinDate = skmJoinDate;
    }

    @Column(name = "angkasa_join_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getAngkasaJoinDate() {
        return angkasaJoinDate;
    }

    public void setAngkasaJoinDate(Date angkasaJoinDate) {
        this.angkasaJoinDate = angkasaJoinDate;
    }

    @Column(name = "function")
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Column(name = "member_total")
    public int getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(int memberTotal) {
        this.memberTotal = memberTotal;
    }

    @Column(name = "member_male")
    public int getMemberMale() {
        return memberMale;
    }

    public void setMemberMale(int memberMale) {
        this.memberMale = memberMale;
    }

    @Column(name = "member_female")
    public int getMemberFemale() {
        return memberFemale;
    }

    public void setMemberFemale(int memberFemale) {
        this.memberFemale = memberFemale;
    }

    @Column(name = "skm_reg_no")
    @Field
    public String getSkmRegistrationNo() {
        return skmRegistrationNo;
    }

    public void setSkmRegistrationNo(String skmRegistrationNo) {
        this.skmRegistrationNo = skmRegistrationNo;
    }

    @Column(name = "bpa_code")
    @Field
    public String getBpaCode() {
        return bpaCode;
    }

    public void setBpaCode(String bpaCode) {
        this.bpaCode = bpaCode;
    }

    @Column(name = "website")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Transient
    public boolean isCreateUser() {
        return createUser;
    }

    public void setCreateUser(boolean createUser) {
        this.createUser = createUser;
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
    public String getCoopImportId() {
        return coopImportId;
    }

    public void setCoopImportId(String coopImportId) {
        this.coopImportId = coopImportId;
    }

    @Transient
    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    @Transient
    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    @Transient
    public String getImportRemark() {
        return importRemark;
    }

    public void setImportRemark(String importRemark) {
        this.importRemark = importRemark;
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

    @Override
    public String toString() {
        return "Coop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amcrCode='" + amcrCode + '\'' +
                ", address=" + address +
                ", contactPersons=" + contactPersons +
                ", phoneNo='" + phoneNo + '\'' +
                ", phoneNo2='" + phoneNo2 + '\'' +
                ", phoneNo3='" + phoneNo3 + '\'' +
                ", faxNo='" + faxNo + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", coopCode='" + coopCode + '\'' +
                ", employerCode='" + employerCode + '\'' +
                ", memberHexRunningNo='" + memberHexRunningNo + '\'' +
                ", coopBusinessTypes=" + coopBusinessTypes +
                ", coopBusinessTypeId=" + coopBusinessTypeId +
                ", coopBusinessType=" + coopBusinessType +
                ", coopBusinessType2Id=" + coopBusinessType2Id +
                ", coopBusinessType2=" + coopBusinessType2 +
                ", coopBusinessType3Id=" + coopBusinessType3Id +
                ", coopBusinessType3=" + coopBusinessType3 +
                ", typeFlag='" + typeFlag + '\'' +
                ", preloaded=" + preloaded +
                ", incorporatedDate=" + incorporatedDate +
                ", skmJoinDate=" + skmJoinDate +
                ", angkasaJoinDate=" + angkasaJoinDate +
                ", function='" + function + '\'' +
                ", memberTotal=" + memberTotal +
                ", memberMale=" + memberMale +
                ", memberFemale=" + memberFemale +
                ", skmRegistrationNo='" + skmRegistrationNo + '\'' +
                ", bpaCode='" + bpaCode + '\'' +
                ", website='" + website + '\'' +
                ", version=" + version +
                ", enabled=" + enabled +
                ", createUser=" + createUser +
                ", userId=" + userId +
                ", user=" + user +
                ", coopImportId='" + coopImportId + '\'' +
                ", importId='" + importId + '\'' +
                ", importStatus='" + importStatus + '\'' +
                ", importRemark='" + importRemark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coop coop = (Coop) o;

        if (createUser != coop.createUser) return false;
        if (enabled != coop.enabled) return false;
        if (memberFemale != coop.memberFemale) return false;
        if (memberMale != coop.memberMale) return false;
        if (memberTotal != coop.memberTotal) return false;
        if (preloaded != coop.preloaded) return false;
        if (address != null ? !address.equals(coop.address) : coop.address != null) return false;
        if (amcrCode != null ? !amcrCode.equals(coop.amcrCode) : coop.amcrCode != null) return false;
        if (angkasaJoinDate != null ? !angkasaJoinDate.equals(coop.angkasaJoinDate) : coop.angkasaJoinDate != null)
            return false;
        if (bpaCode != null ? !bpaCode.equals(coop.bpaCode) : coop.bpaCode != null) return false;
        if (contactPersons != null ? !contactPersons.equals(coop.contactPersons) : coop.contactPersons != null)
            return false;
        if (coopBusinessType != null ? !coopBusinessType.equals(coop.coopBusinessType) : coop.coopBusinessType != null)
            return false;
        if (coopBusinessType2 != null ? !coopBusinessType2.equals(coop.coopBusinessType2) : coop.coopBusinessType2 != null)
            return false;
        if (coopBusinessType2Id != null ? !coopBusinessType2Id.equals(coop.coopBusinessType2Id) : coop.coopBusinessType2Id != null)
            return false;
        if (coopBusinessType3 != null ? !coopBusinessType3.equals(coop.coopBusinessType3) : coop.coopBusinessType3 != null)
            return false;
        if (coopBusinessType3Id != null ? !coopBusinessType3Id.equals(coop.coopBusinessType3Id) : coop.coopBusinessType3Id != null)
            return false;
        if (coopBusinessTypeId != null ? !coopBusinessTypeId.equals(coop.coopBusinessTypeId) : coop.coopBusinessTypeId != null)
            return false;
        if (coopBusinessTypes != null ? !coopBusinessTypes.equals(coop.coopBusinessTypes) : coop.coopBusinessTypes != null)
            return false;
        if (coopCode != null ? !coopCode.equals(coop.coopCode) : coop.coopCode != null) return false;
        if (coopImportId != null ? !coopImportId.equals(coop.coopImportId) : coop.coopImportId != null) return false;
        if (description != null ? !description.equals(coop.description) : coop.description != null) return false;
        if (email != null ? !email.equals(coop.email) : coop.email != null) return false;
        if (email2 != null ? !email2.equals(coop.email2) : coop.email2 != null) return false;
        if (employerCode != null ? !employerCode.equals(coop.employerCode) : coop.employerCode != null) return false;
        if (faxNo != null ? !faxNo.equals(coop.faxNo) : coop.faxNo != null) return false;
        if (function != null ? !function.equals(coop.function) : coop.function != null) return false;
        if (id != null ? !id.equals(coop.id) : coop.id != null) return false;
        if (importId != null ? !importId.equals(coop.importId) : coop.importId != null) return false;
        if (importRemark != null ? !importRemark.equals(coop.importRemark) : coop.importRemark != null) return false;
        if (importStatus != null ? !importStatus.equals(coop.importStatus) : coop.importStatus != null) return false;
        if (incorporatedDate != null ? !incorporatedDate.equals(coop.incorporatedDate) : coop.incorporatedDate != null)
            return false;
        if (memberHexRunningNo != null ? !memberHexRunningNo.equals(coop.memberHexRunningNo) : coop.memberHexRunningNo != null)
            return false;
        if (name != null ? !name.equals(coop.name) : coop.name != null) return false;
        if (phoneNo != null ? !phoneNo.equals(coop.phoneNo) : coop.phoneNo != null) return false;
        if (phoneNo2 != null ? !phoneNo2.equals(coop.phoneNo2) : coop.phoneNo2 != null) return false;
        if (phoneNo3 != null ? !phoneNo3.equals(coop.phoneNo3) : coop.phoneNo3 != null) return false;
        if (skmJoinDate != null ? !skmJoinDate.equals(coop.skmJoinDate) : coop.skmJoinDate != null) return false;
        if (skmRegistrationNo != null ? !skmRegistrationNo.equals(coop.skmRegistrationNo) : coop.skmRegistrationNo != null)
            return false;
        if (typeFlag != null ? !typeFlag.equals(coop.typeFlag) : coop.typeFlag != null) return false;
        if (user != null ? !user.equals(coop.user) : coop.user != null) return false;
        if (userId != null ? !userId.equals(coop.userId) : coop.userId != null) return false;
        if (version != null ? !version.equals(coop.version) : coop.version != null) return false;
        if (website != null ? !website.equals(coop.website) : coop.website != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (amcrCode != null ? amcrCode.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (contactPersons != null ? contactPersons.hashCode() : 0);
        result = 31 * result + (phoneNo != null ? phoneNo.hashCode() : 0);
        result = 31 * result + (phoneNo2 != null ? phoneNo2.hashCode() : 0);
        result = 31 * result + (phoneNo3 != null ? phoneNo3.hashCode() : 0);
        result = 31 * result + (faxNo != null ? faxNo.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (coopCode != null ? coopCode.hashCode() : 0);
        result = 31 * result + (employerCode != null ? employerCode.hashCode() : 0);
        result = 31 * result + (memberHexRunningNo != null ? memberHexRunningNo.hashCode() : 0);
        result = 31 * result + (coopBusinessTypes != null ? coopBusinessTypes.hashCode() : 0);
        result = 31 * result + (coopBusinessTypeId != null ? coopBusinessTypeId.hashCode() : 0);
        result = 31 * result + (coopBusinessType != null ? coopBusinessType.hashCode() : 0);
        result = 31 * result + (coopBusinessType2Id != null ? coopBusinessType2Id.hashCode() : 0);
        result = 31 * result + (coopBusinessType2 != null ? coopBusinessType2.hashCode() : 0);
        result = 31 * result + (coopBusinessType3Id != null ? coopBusinessType3Id.hashCode() : 0);
        result = 31 * result + (coopBusinessType3 != null ? coopBusinessType3.hashCode() : 0);
        result = 31 * result + (typeFlag != null ? typeFlag.hashCode() : 0);
        result = 31 * result + (preloaded ? 1 : 0);
        result = 31 * result + (incorporatedDate != null ? incorporatedDate.hashCode() : 0);
        result = 31 * result + (skmJoinDate != null ? skmJoinDate.hashCode() : 0);
        result = 31 * result + (angkasaJoinDate != null ? angkasaJoinDate.hashCode() : 0);
        result = 31 * result + (function != null ? function.hashCode() : 0);
        result = 31 * result + memberTotal;
        result = 31 * result + memberMale;
        result = 31 * result + memberFemale;
        result = 31 * result + (skmRegistrationNo != null ? skmRegistrationNo.hashCode() : 0);
        result = 31 * result + (bpaCode != null ? bpaCode.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (createUser ? 1 : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (coopImportId != null ? coopImportId.hashCode() : 0);
        result = 31 * result + (importId != null ? importId.hashCode() : 0);
        result = 31 * result + (importStatus != null ? importStatus.hashCode() : 0);
        result = 31 * result + (importRemark != null ? importRemark.hashCode() : 0);
        return result;
    }
}
