<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="surveyDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='surveyDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="surveyList.survey"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="surveyDetail.heading"/></h2>
    <fmt:message key="surveyDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="survey" method="post" action="surveyform" cssClass="well"
           id="surveyForm" onsubmit="return validateSurvey(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>

    <spring:bind path="survey.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="survey.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description"  maxlength="200"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <spring:bind path="survey.endDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="survey.endDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="endDate" id="endDate" size="11" title="date" datepicker="true"/>
        <form:errors path="endDate" cssClass="help-block"/>
    </div>
    <spring:bind path="survey.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="survey.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="50"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="survey.startDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="survey.startDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="startDate" id="startDate" size="11" title="date" datepicker="true"/>
        <form:errors path="startDate" cssClass="help-block"/>
    </div>
    <spring:bind path="survey.version">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="survey.version" styleClass="control-label"/>
        <form:input cssClass="form-control" path="version" id="version"  maxlength="255"/>
        <form:errors path="version" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty survey.id}">
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

<v:javascript formName="survey" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['surveyForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
