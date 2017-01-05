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
<form:form commandName="employer" method="post" action="employerImportEntryform" cssClass="well"
           id="employerForm" onsubmit="return validateEmployer(this)">
<form:hidden path="id"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdTime"/>
<form:hidden path="modifiedBy"/>
<form:hidden path="modifiedTime"/>

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
    <spring:bind path="employer.phoneNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="employer.phoneNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="phoneNo" id="phoneNo"  maxlength="255"/>
        <form:errors path="phoneNo" cssClass="help-block"/>
    </div>
    <spring:bind path="employer.version">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="employer.version" styleClass="control-label"/>
        <form:input cssClass="form-control" path="version" id="version"  maxlength="255"/>
        <form:errors path="version" cssClass="help-block"/>
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
