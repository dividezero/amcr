<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="dmsMemberUpdateDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='dmsMemberUpdateDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="dmsMemberUpdateList.dmsMemberUpdate"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="dmsMemberUpdateDetail.heading"/></h2>
    <fmt:message key="dmsMemberUpdateDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="dmsMemberUpdate" method="post" action="dmsMemberUpdateform" cssClass="well"
           id="dmsMemberUpdateForm" onsubmit="return validateDmsMemberUpdate(this)">
<form:hidden path="id"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdTime"/>
<form:hidden path="modifiedBy"/>
<form:hidden path="modifiedTime"/>

    <spring:bind path="dmsMemberUpdate.icNumber">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="dmsMemberUpdate.icNumber" styleClass="control-label"/>
        <form:input cssClass="form-control" path="icNumber" id="icNumber"  maxlength="20"/>
        <form:errors path="icNumber" cssClass="help-block"/>
    </div>
    <spring:bind path="dmsMemberUpdate.phoneNum">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="dmsMemberUpdate.phoneNum" styleClass="control-label"/>
        <form:input cssClass="form-control" path="phoneNum" id="phoneNum"  maxlength="20"/>
        <form:errors path="phoneNum" cssClass="help-block"/>
    </div>
    <spring:bind path="dmsMemberUpdate.utilityField1">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="dmsMemberUpdate.utilityField1" styleClass="control-label"/>
    <form:input cssClass="form-control" path="utilityField1" id="utilityField1"  maxlength="50"/>
    <form:errors path="utilityField1" cssClass="help-block"/>
    </div>
    <spring:bind path="dmsMemberUpdate.utilityField2">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="dmsMemberUpdate.utilityField2" styleClass="control-label"/>
    <form:input cssClass="form-control" path="utilityField2" id="utilityField2"  maxlength="50"/>
    <form:errors path="utilityField2" cssClass="help-block"/>
    </div>
    <spring:bind path="dmsMemberUpdate.utilityField3">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="dmsMemberUpdate.utilityField3" styleClass="control-label"/>
    <form:input cssClass="form-control" path="utilityField3" id="utilityField3"  maxlength="50"/>
    <form:errors path="utilityField3" cssClass="help-block"/>
    </div>

    <spring:bind path="dmsMemberUpdate.status">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="dmsMemberUpdate.status" styleClass="control-label"/>
        <form:input cssClass="form-control" path="status" id="status"  maxlength="2"/>
        <form:errors path="status" cssClass="help-block"/>
    </div>
    <spring:bind path="dmsMemberUpdate.updatedMemberDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="dmsMemberUpdate.updatedMemberDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="updatedMemberDate" id="updatedMemberDate" size="11" title="date"
                    datepicker="true"/>
        <form:errors path="updatedMemberDate" cssClass="help-block"/>
    </div>


    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty dmsMemberUpdate.id}">
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

<v:javascript formName="dmsMemberUpdate" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['dmsMemberUpdateForm']).focus();
        $('#updatedMemberDate').datetimepicker();
    });
</script>
