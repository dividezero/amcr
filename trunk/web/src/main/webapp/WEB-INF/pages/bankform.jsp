<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="bankDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='bankDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="bankList.bank"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="bankDetail.heading"/></h2>
    <fmt:message key="bankDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="bank" method="post" action="bankform" cssClass="well"
           id="bankForm" onsubmit="return validateBank(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>

    <spring:bind path="bank.code">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="bank.code" styleClass="control-label"/>
        <form:input cssClass="form-control" path="code" id="code"  maxlength="50"/>
        <form:errors path="code" cssClass="help-block"/>
    </div>
    <spring:bind path="bank.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="bank.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="250"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty bank.id}">
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

<v:javascript formName="bank" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['bankForm']).focus();
    });
</script>
