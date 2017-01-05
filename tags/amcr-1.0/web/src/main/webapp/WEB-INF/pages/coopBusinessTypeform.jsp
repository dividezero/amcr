<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="coopBusinessTypeDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='coopBusinessTypeDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="coopBusinessTypeList.coopBusinessType"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="coopBusinessTypeDetail.heading"/></h2>
    <fmt:message key="coopBusinessTypeDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="coopBusinessType" method="post" action="coopBusinessTypeform" cssClass="well"
           id="coopBusinessTypeForm" onsubmit="return validateCoopBusinessType(this)">
<form:hidden path="id"/>
<form:hidden path="version"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>

    <spring:bind path="coopBusinessType.code">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coopBusinessType.code" styleClass="control-label"/>
        <form:input cssClass="form-control" path="code" id="code"  maxlength="2"/>
        <form:errors path="code" cssClass="help-block"/>
    </div>
    <spring:bind path="coopBusinessType.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coopBusinessType.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description"  maxlength="200"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <spring:bind path="coopBusinessType.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coopBusinessType.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="50"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty coopBusinessType.id}">
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

<v:javascript formName="coopBusinessType" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['coopBusinessTypeForm']).focus();
    });
</script>
