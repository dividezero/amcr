<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='memberDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="memberList.member"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="memberDetail.heading"/></h2>
    <fmt:message key="memberDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="member" method="post" action="memberImportEntryform" cssClass="well"
           id="memberForm" onsubmit="return validateMember(this)">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="importId"/>
    <form:hidden path="memberImportId"/>

    <spring:bind path="member.importStatus">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="memberImport.importStatus" styleClass="control-label"/>
    <form:input cssClass="form-control" path="importStatus" id="importStatus"  maxlength="50" readonly="true"/>
    <form:errors path="importStatus" cssClass="help-block"/>
    </div>
    <spring:bind path="member.importRemark">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="memberImport.importRemark" styleClass="control-label"/>
    <form:input cssClass="form-control" path="importRemark" id="importRemark"  maxlength="50" readonly="true"/>
    <form:errors path="importRemark" cssClass="help-block"/>
    </div>
	<spring:bind path="member.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="member.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="50"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="member.icNumber">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="member.icNumber" styleClass="control-label"/>
        <form:input cssClass="form-control" path="icNumber" id="icNumber"  maxlength="50"/>
        <form:errors path="icNumber" cssClass="help-block"/>
    </div>
    <spring:bind path="member.importEmployerCode">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="memberImport.importEmployerCode" styleClass="control-label"/>
    <form:input cssClass="form-control" path="importEmployerCode" id="importEmployerCode"  maxlength="50"/>
    <form:errors path="importEmployerCode" cssClass="help-block"/>
    </div>
    <spring:bind path="member.importCoopCode">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="memberImport.importCoopCode" styleClass="control-label"/>
    <form:input cssClass="form-control" path="importCoopCode" id="importCoopCode"  maxlength="50"/>
    <form:errors path="importCoopCode" cssClass="help-block"/>
    </div>
    <spring:bind path="member.importCoopAmcrCode">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="memberImport.importCoopAmcrCode" styleClass="control-label"/>
    <form:input cssClass="form-control" path="importCoopAmcrCode" id="importCoopAmcrCode"  maxlength="50"/>
    <form:errors path="importCoopAmcrCode" cssClass="help-block"/>
    </div>
    <spring:bind path="member.membershipNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="member.membershipNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="membershipNo" id="membershipNo"  maxlength="50"/>
        <form:errors path="membershipNo" cssClass="help-block"/>
    </div>
    <spring:bind path="member.phoneNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="member.phoneNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="phoneNo" id="phoneNo"  maxlength="12"/>
        <form:errors path="phoneNo" cssClass="help-block"/>
    </div>
    <spring:bind path="member.gender">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="member.gender" styleClass="control-label"/>
        <form:select cssClass="form-control" path="gender" items="${genderList}"/>
        <form:errors path="gender" cssClass="help-block"/>
    </div>
    <spring:bind path="member.maritalStatus">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.maritalStatus" styleClass="control-label"/>
    <form:select cssClass="form-control" path="maritalStatus" items="${maritalStatusList}"/>
    <form:errors path="maritalStatus" cssClass="help-block"/>
    </div>
    <spring:bind path="member.race">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.race" styleClass="control-label"/>
    <form:select cssClass="form-control" path="race" items="${raceList}"/>
    <form:errors path="race" cssClass="help-block"/>
    </div>

    <spring:bind path="member.preferredLanguage">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.preferredLanguage" styleClass="control-label"/>
    <form:select cssClass="form-control" path="preferredLanguage" items="${languageList}"/>
    <form:errors path="preferredLanguage" cssClass="help-block"/>
    </div>

    <spring:bind path="member.enabled">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="member.enabled" styleClass="control-label"/>
        <form:checkbox path="enabled" id="enabled" cssClass="checkbox"/>
        <form:errors path="enabled" cssClass="help-block"/>
    </div>


	<br/>
    <div class="form-group">
        <%--<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">--%>
            <%--<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>--%>
        <%--</button>--%>
        <%--<button type="submit" class="btn btn-info" name="validate" onclick="bCancel=false">--%>
            <%--<i class="fa fa-check"></i> <fmt:message key="button.validate"/>--%>
        <%--</button>--%>
        <%--<button type="submit" class="btn btn-info" name="saveToMembers" onclick="bCancel=false">--%>
            <%--<i class="fa fa-plus"></i> <fmt:message key="button.saveToMembers"/>--%>
        <%--</button>--%>
        <%--<button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">--%>
            <%--<i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>--%>
        <%--</button>--%>

        <a href="memberImportform?id=${param.memberImportId}" class="btn btn-primary" id="btncancel">
                 <fmt:message key="button.cancel"/></a>
        <%--<button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">--%>
            <%--<i class="icon-remove"></i> <fmt:message key="button.cancel"/>--%>
        <%--</button>--%>
    </div>
</form:form>
</div>

<v:javascript formName="member" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['memberForm']).focus();
    });
</script>
