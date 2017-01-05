<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberEmployerDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='memberEmployerDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="memberEmployerList.memberEmployer"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="memberEmployerDetail.heading"/></h2>
    <fmt:message key="memberEmployerDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="memberEmployer" method="post" action="memberEmployerform" cssClass="well"
           id="memberEmployerForm" onsubmit="return validateMemberEmployer(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>


    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="employer" items="employerList" itemLabel="label" itemValue="value"/>
    <spring:bind path="memberEmployer.employerId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberEmployer.employerId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="employerId" id="employerId"  maxlength="255"/>
        <form:errors path="employerId" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="member" items="memberList" itemLabel="label" itemValue="value"/>
    <spring:bind path="memberEmployer.memberId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberEmployer.memberId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="memberId" id="memberId"  maxlength="255"/>
        <form:errors path="memberId" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty memberEmployer.id}">
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

<v:javascript formName="memberEmployer" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['memberEmployerForm']).focus();
    });
</script>
