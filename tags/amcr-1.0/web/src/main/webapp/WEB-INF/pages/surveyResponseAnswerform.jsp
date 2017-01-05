<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="surveyResponseAnswerDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='surveyResponseAnswerDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="surveyResponseAnswerList.surveyResponseAnswer"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="surveyResponseAnswerDetail.heading"/></h2>
    <fmt:message key="surveyResponseAnswerDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="surveyResponseAnswer" method="post" action="surveyResponseAnswerform" cssClass="well"
           id="surveyResponseAnswerForm" onsubmit="return validateSurveyResponseAnswer(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>


    <spring:bind path="surveyResponseAnswer.answerValue">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="surveyResponseAnswer.answerValue" styleClass="control-label"/>
        <form:input cssClass="form-control" path="answerValue" id="answerValue"  maxlength="255"/>
        <form:errors path="answerValue" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="surveyQuestionChoice" items="surveyQuestionChoiceList" itemLabel="label" itemValue="value"/>
    <spring:bind path="surveyResponseAnswer.surveyQuestionChoiceId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="surveyResponseAnswer.surveyQuestionChoiceId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="surveyQuestionChoiceId" id="surveyQuestionChoiceId"  maxlength="255"/>
        <form:errors path="surveyQuestionChoiceId" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="surveyResponse" items="surveyResponseList" itemLabel="label" itemValue="value"/>
    <spring:bind path="surveyResponseAnswer.surveyResponseId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="surveyResponseAnswer.surveyResponseId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="surveyResponseId" id="surveyResponseId"  maxlength="255"/>
        <form:errors path="surveyResponseId" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty surveyResponseAnswer.id}">
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

<v:javascript formName="surveyResponseAnswer" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['surveyResponseAnswerForm']).focus();
    });
</script>
