<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="surveyQuestionChoiceDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='surveyQuestionChoiceDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="surveyQuestionChoiceList.surveyQuestionChoice"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="surveyQuestionChoiceDetail.heading"/></h2>
    <fmt:message key="surveyQuestionChoiceDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="surveyQuestionChoice" method="post" action="surveyQuestionChoiceform" cssClass="well"
           id="surveyQuestionChoiceForm" onsubmit="return validateSurveyQuestionChoice(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>

    <spring:bind path="surveyQuestionChoice.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="surveyQuestionChoice.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description"  maxlength="200"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <spring:bind path="surveyQuestionChoice.sequence">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="surveyQuestionChoice.sequence" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sequence" id="sequence"  maxlength="255"/>
        <form:errors path="sequence" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="surveyQuestion" items="surveyQuestionList" itemLabel="label" itemValue="value"/>
    <spring:bind path="surveyQuestionChoice.surveyQuestionId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="surveyQuestionChoice.surveyQuestionId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="surveyQuestionId" id="surveyQuestionId"  maxlength="255"/>
        <form:errors path="surveyQuestionId" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty surveyQuestionChoice.id}">
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

<v:javascript formName="surveyQuestionChoice" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['surveyQuestionChoiceForm']).focus();
    });
</script>
