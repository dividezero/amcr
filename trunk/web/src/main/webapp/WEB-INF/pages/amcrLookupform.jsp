<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="amcrLookupDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='amcrLookupDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="amcrLookupList.amcrLookup"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="amcrLookupDetail.heading"/></h2>
    <fmt:message key="amcrLookupDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="amcrLookup" method="post" action="amcrLookupform" cssClass="well"
           id="amcrLookupForm" onsubmit="return validateAmcrLookup(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>

    <spring:bind path="amcrLookup.tableName">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="amcrLookup.tableName" styleClass="control-label"/>
    <form:input cssClass="form-control" path="tableName" id="tableName"  maxlength="255" readonly="true"/>
    <form:errors path="tableName" cssClass="help-block"/>
    </div>
    <spring:bind path="amcrLookup.head">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="amcrLookup.head" styleClass="control-label"/>
        <form:input cssClass="form-control" path="head" id="head"  maxlength="255"/>
        <form:errors path="head" cssClass="help-block"/>
    </div>
    <spring:bind path="amcrLookup.tail">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="amcrLookup.tail" styleClass="control-label"/>
        <form:input cssClass="form-control" path="tail" id="tail"  maxlength="255"/>
        <form:errors path="tail" cssClass="help-block"/>
    </div>
    <spring:bind path="amcrLookup.tailLength">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="amcrLookup.tailLength" styleClass="control-label"/>
        <form:input cssClass="form-control" path="tailLength" id="tailLength"  maxlength="255"/>
        <form:errors path="tailLength" cssClass="help-block"/>
    </div>
    <spring:bind path="amcrLookup.tailPad">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="amcrLookup.tailPad" styleClass="control-label"/>
        <form:input cssClass="form-control" path="tailPad" id="tailPad"  maxlength="255"/>
        <form:errors path="tailPad" cssClass="help-block"/>
    </div>


    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <%--<c:if test="${not empty amcrLookup.id}">--%>
            <%--<button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">--%>
                <%--<i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>--%>
            <%--</button>--%>
        <%--</c:if>--%>

        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="amcrLookup" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['amcrLookupForm']).focus();
    });
</script>
