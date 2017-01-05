<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="eventMemberDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='eventMemberDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="eventMemberList.eventMember"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><c:out value="${eventMember.event.name}"/></h2>
    <h2><fmt:message key="eventMemberDetail.heading"/></h2>
    <fmt:message key="eventMemberDetail.message"/>

</div>

<div class="col-sm-8">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="eventMember" method="post" action="eventMemberform" cssClass="well"
           id="eventMemberForm" onsubmit="return validateEventMember(this)">
<form:hidden path="id"/>

    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>
    <form:hidden path="eventId"/>

    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="eventMember.icNumber">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="eventMember.icNumber" styleClass="control-label"/>
                <form:input cssClass="form-control" path="icNumber" id="icNumber"  maxlength="20"/>
                <form:errors path="icNumber" cssClass="help-block"/>
                <span class="help-block"><fmt:message key="help.icNumber"/></span>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="eventMember.designation">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="eventMember.designation" styleClass="control-label"/>
                <form:select cssClass="form-control" path="designation" items="${designationList}"/>
                <form:errors path="designation" cssClass="help-block"/>
            </div>
        </div>
    </div>


    <spring:bind path="eventMember.name">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="eventMember.name" styleClass="control-label"/>
    <form:input cssClass="form-control" path="name" id="name"  maxlength="200"/>
    <form:errors path="name" cssClass="help-block"/>
    </div>


    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="eventMember.dateOfBirth">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="eventMember.dateOfBirth" styleClass="control-label"/>
                <form:input cssClass="form-control" path="dateOfBirth" id="dateOfBirth"  maxlength="255"/>
                <form:errors path="dateOfBirth" cssClass="help-block"/>
                <span class="help-block"><fmt:message key="help.datepicker"/></span>
            </div>
        </div>
        <div class="col-sm-6">
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="eventMember.race">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="eventMember.race" styleClass="control-label"/>
                <form:select cssClass="form-control" path="race" items="${raceList}"/>
                <form:errors path="race" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="eventMember.maritalStatus">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="eventMember.maritalStatus" styleClass="control-label"/>
                <form:select cssClass="form-control" path="maritalStatus" items="${maritalStatusList}"/>
                <form:errors path="maritalStatus" cssClass="help-block"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="eventMember.email">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="eventMember.email" styleClass="control-label"/>
                <form:input cssClass="form-control" path="email" id="email"  maxlength="255"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="eventMember.phoneNo">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="eventMember.phoneNo" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNo" id="phoneNo"  maxlength="12"/>
                <form:errors path="phoneNo" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="eventMember.phoneNoMobile">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="eventMember.phoneNoMobile" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNoMobile" id="phoneNoMobile"  maxlength="255"/>
                <form:errors path="phoneNoMobile" cssClass="help-block"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="eventMember.phoneNoOffice">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="eventMember.phoneNoOffice" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNoOffice" id="phoneNoOffice"  maxlength="255"/>
                <form:errors path="phoneNoOffice" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
        </div>
    </div>

    <br/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty eventMember.id}">
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

<v:javascript formName="eventMember" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all"
      href="<c:url value='/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css'/>"/>
<script type="text/javascript"
        src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
    <script type="text/javascript"
            src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['eventMemberForm']).focus();


        $('#dateOfBirth').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
