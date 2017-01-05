<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="surveyResponseDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='surveyResponseDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="surveyResponseList.surveyResponse"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="surveyResponseDetail.heading"/></h2>
    <fmt:message key="surveyResponseDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="surveyResponse" method="post" action="surveyResponseform" cssClass="well"
           id="surveyResponseForm" onsubmit="return validateSurveyResponse(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>

    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="coopMember" items="coopMemberList" itemLabel="label" itemValue="value"/>
    <spring:bind path="surveyResponse.coopMemberId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="surveyResponse.coopMemberId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="coopMemberId" id="coopMemberId"  maxlength="255"/>
        <form:errors path="coopMemberId" cssClass="help-block"/>
    </div>
    <spring:bind path="surveyResponse.submitTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="surveyResponse.submitTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="submitTime" id="submitTime"  maxlength="255"/>
        <form:errors path="submitTime" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="survey" items="surveyList" itemLabel="label" itemValue="value"/>
    <spring:bind path="surveyResponse.surveyId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="surveyResponse.surveyId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="surveyId" id="surveyId"  maxlength="255"/>
        <form:errors path="surveyId" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty surveyResponse.id}">
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

<v:javascript formName="surveyResponse" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['surveyResponseForm']).focus();
    });
</script>
