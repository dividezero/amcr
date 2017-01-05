package com.angkasa.dto;

import com.angkasa.model.BaseObject;
import com.angkasa.model.CoopMember;
import com.angkasa.model.SimpleAddress;
import com.google.gson.annotations.Expose;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */

public class MemberExportDto implements Serializable {
    private static final long serialVersionUID = 3832626162173359516L;

    private Long id;
    private String icNumber;
    private String coopCode;
    private String name;
    private String phoneNo;
    private String employerCode;
    private String status;
    private String employerNo;

    public MemberExportDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCoopCode() {
        return coopCode;
    }

    public void setCoopCode(String coopCode) {
        this.coopCode = coopCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployerCode() {
        return employerCode;
    }

    public void setEmployerCode(String employerCode) {
        this.employerCode = employerCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployerNo() {
        return employerNo;
    }

    public void setEmployerNo(String employerNo) {
        this.employerNo = employerNo;
    }

}
