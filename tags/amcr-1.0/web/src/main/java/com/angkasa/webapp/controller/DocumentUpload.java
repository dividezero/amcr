package com.angkasa.webapp.controller;

import java.util.Date;

/**
 * User: blakeong
 */
public class DocumentUpload extends FileUpload {
    private String standardView;
    private String successView;
    private String cancelView;
    private String moduleName;
    private String modulePrimKey;
    private String failureView;
    private String description;
    private String openField;
    private Date paySlipMonth;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStandardView() {
        return standardView;
    }

    public void setStandardView(String standardView) {
        this.standardView = standardView;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    public String getCancelView() {
        return cancelView;
    }

    public void setCancelView(String cancelView) {
        this.cancelView = cancelView;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModulePrimKey() {
        return modulePrimKey;
    }

    public void setModulePrimKey(String modulePrimKey) {
        this.modulePrimKey = modulePrimKey;
    }

    public String getFailureView() {
        return failureView;
    }

    public void setFailureView(String failureView) {
        this.failureView = failureView;
    }

    public String getOpenField() {
        return openField;
    }

    public void setOpenField(String openField) {
        this.openField = openField;
    }

	public Date getPaySlipMonth() {
		return paySlipMonth;
	}

	public void setPaySlipMonth(Date paySlipMonth) {
		this.paySlipMonth = paySlipMonth;
	}
    
}
