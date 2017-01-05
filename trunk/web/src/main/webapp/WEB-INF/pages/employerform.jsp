<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="employerDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='employerDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="employerList.employer"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="employerDetail.heading"/></h2>
    <fmt:message key="employerDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="employer" method="post" action="employerform" cssClass="well"
           id="employerForm" onsubmit="return validateEmployer(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>
    <form:hidden path="employerNo"/>

    <spring:bind path="employer.employerCode">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="employer.employerCode" styleClass="control-label"/>
        <form:input cssClass="form-control" path="employerCode" id="employerCode"  maxlength="10"/>
        <form:errors path="employerCode" cssClass="help-block"/>
    </div>
    <spring:bind path="employer.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="employer.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="200"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="employer.phoneNo">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="employer.phoneNo" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNo" id="phoneNo"  maxlength="255"/>
                <form:errors path="phoneNo" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="employer.phoneNo2">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="employer.phoneNo2" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNo2" id="phoneNo2"  maxlength="255"/>
                <form:errors path="phoneNo2" cssClass="help-block"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="employer.phoneNo3">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="employer.phoneNo3" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNo3" id="phoneNo3"  maxlength="255"/>
                <form:errors path="phoneNo3" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="employer.faxNo">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="employer.faxNo" styleClass="control-label"/>
                <form:input cssClass="form-control" path="faxNo" id="faxNo"  maxlength="255"/>
                <form:errors path="faxNo" cssClass="help-block"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="employer.website">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="employer.website" styleClass="control-label"/>
                <form:input cssClass="form-control" path="website" id="website"  maxlength="255"/>
                <form:errors path="website" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
        </div>
    </div>
    <%--<div class="row">--%>
        <%--<div class="col-sm-6">--%>
        <%--</div>--%>
        <%--<div class="col-sm-6">--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--ADDRESS--%>
    <div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.address.address"/>
            <form:input cssClass="form-control" path="address.address1" id="address.address1"/>
            <form:input cssClass="form-control" path="address.address2" id="address.address2"/>
            <form:input cssClass="form-control" path="address.address3" id="address.address3"/>
        </div>
        <div class="row">
            <div class="col-sm-7 form-group">
                <appfuse:label styleClass="control-label" key="user.address.city"/>
                <form:input cssClass="form-control" path="address.city" id="address.city"/>
            </div>
            <div class="col-sm-2 form-group">
                <appfuse:label styleClass="control-label" key="user.address.province"/>
                <form:input cssClass="form-control" path="address.province" id="address.province"/>
            </div>
            <div class="col-sm-3 form-group">
                <appfuse:label styleClass="control-label" key="user.address.postalCode"/>
                <form:input cssClass="form-control" path="address.postalCode" id="address.postalCode"/>
            </div>
        </div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.address.country"/>
            <appfuse:country name="address.country" prompt="" default="${employer.address.country}"/>
        </div>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty employer.id}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="employer" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['employerForm']).focus();
    });
</script>
