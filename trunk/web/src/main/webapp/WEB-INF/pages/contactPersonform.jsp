<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="contactPersonDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='contactPersonDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="contactPersonList.contactPerson"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="contactPersonDetail.heading"/></h2>
    <fmt:message key="contactPersonDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="contactPerson" method="post" action="contactPersonform" cssClass="well"
           id="contactPersonForm" onsubmit="return validateContactPerson(this)">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="coopId"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>
	
	<div class="form-group">
	    <label class="control-label">Coop</label>
	    <input type="text" class="form-control" disabled="disabled" value="${contactPerson.coop.name} (${contactPerson.coop.coopCode})"/>
	</div>

	<spring:bind path="contactPerson.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="contactPerson.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="50"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="contactPerson.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="contactPerson.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description"  maxlength="200"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <spring:bind path="contactPerson.email">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="contactPerson.email" styleClass="control-label"/>
        <form:input cssClass="form-control" path="email" id="email"  maxlength="20"/>
        <form:errors path="email" cssClass="help-block"/>
    </div>
    <spring:bind path="contactPerson.workPhoneNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="contactPerson.workPhoneNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="workPhoneNo" id="workPhoneNo"  maxlength="12"/>
        <form:errors path="workPhoneNo" cssClass="help-block"/>
    </div>
    
    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty contactPerson.id}">
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

<v:javascript formName="contactPerson" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['contactPersonForm']).focus();
    });
</script>
