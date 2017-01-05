<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="systemSettingDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='systemSettingDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="systemSettingList.systemSetting"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="systemSettingDetail.heading"/></h2>
    <fmt:message key="systemSettingDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="systemSetting" method="post" action="systemSettingform" cssClass="well"
           id="systemSettingForm" onsubmit="return validateSystemSetting(this)">
<form:hidden path="id"/>
<form:hidden path="isBooleanSetting"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdTime"/>
<form:hidden path="modifiedBy"/>
<form:hidden path="modifiedTime"/>
<form:hidden path="propertyName"/>

    <spring:bind path="systemSetting.displayName">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="systemSetting.displayName" styleClass="control-label"/>
    <form:input cssClass="form-control" path="displayName" id="displayName"  maxlength="255"/>
    <form:errors path="displayName" cssClass="help-block"/>
    </div>
    <spring:bind path="systemSetting.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="systemSetting.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description"  maxlength="250"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <spring:bind path="systemSetting.propertyValue">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="systemSetting.propertyValue" styleClass="control-label"/>
    <c:choose>
        <c:when test="${systemSetting.isBooleanSetting == true}">
            <form:select cssClass="form-control" path="propertyValue" items="${booleanList}"/>
        </c:when>
        <c:otherwise>
            <form:input cssClass="form-control" path="propertyValue" id="propertyValue"  maxlength="2000"/>
        </c:otherwise>
    </c:choose>


        <form:errors path="propertyValue" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <%--<c:if test="${not empty systemSetting.id}">--%>
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

<v:javascript formName="systemSetting" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['systemSettingForm']).focus();
    });
</script>
