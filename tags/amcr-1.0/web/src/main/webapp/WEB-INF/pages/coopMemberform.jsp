<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="coopMemberDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='coopMemberDetail.heading'/>"/>

    <link rel="stylesheet" type="text/css" href="/scripts/select2/select2.css">
    <link rel="stylesheet" type="text/css" href="/scripts/select2/select2-bootstrap.css">
    <script type="text/javascript" src="/scripts/select2/select2.js"></script>
</head>

<c:set var="delObject" scope="request"><fmt:message key="coopMemberList.coopMember"/></c:set>
<script type="text/javascript">var msgDelConfirm = "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="coopMemberDetail.heading"/></h2>
    <fmt:message key="coopMemberDetail.message"/>
</div>

<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="coopMember" method="post" action="coopMemberform" cssClass="well"
               id="coopMemberForm" onsubmit="return validateCoopMember(this)">
        <form:hidden path="id"/>
        <%--<form:hidden path="coopId"/>--%>

        <form:hidden path="name"/>
        <form:hidden path="icNumber"/>
        <form:hidden path="createdBy"/>
        <form:hidden path="createdTime"/>
        <form:hidden path="modifiedBy"/>
        <form:hidden path="modifiedTime"/>

    <c:choose>
        <c:when test="${currentUser.memberUser || empty coopMember.coopId}" >
            <appfuse:label key="coopMember.member" styleClass="control-label"/>
            <form:select cssClass="form-control" path="memberId" items="${memberList}" disabled="true"/>
            <spring:bind path="memberId">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <form:hidden path="memberId"/>
<br/>
            <appfuse:label key="coopMember.coop" styleClass="control-label"/>
            <form:select cssClass="form-control" path="coopId" items="${coopList}"/>
            <spring:bind path="coopId">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
        </c:when>
        <c:when test="${currentUser.coopUser || empty coopMember.memberId}">
            <appfuse:label key="coopMember.coop" styleClass="control-label"/>
            <form:select cssClass="form-control" path="coopId" items="${coopList}" disabled="true"/>
            <spring:bind path="coopId">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <form:hidden path="coopId"/>
<br/>
            <appfuse:label key="coopMember.member" styleClass="control-label"/>
            <form:select cssClass="form-control" path="memberId" items="${memberList}"/>
            <spring:bind path="memberId">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
        </c:when>
        <c:otherwise>
            <appfuse:label key="coopMember.coop" styleClass="control-label"/>
            <form:select cssClass="form-control" path="coopId" items="${coopList}"/>
            <spring:bind path="coopId">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
<br/>
            <appfuse:label key="coopMember.member" styleClass="control-label"/>
            <form:select cssClass="form-control" path="memberId" items="${memberList}"/>
            <spring:bind path="memberId">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
        </c:otherwise>
    </c:choose>
<br/>
        <c:if test="${not empty coopMember.id}">
            <spring:bind path="coopMember.membershipAknNo">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="coopMember.membershipAknNo" styleClass="control-label"/>
            <form:input cssClass="form-control" path="membershipAknNo" id="membershipAknNo"  maxlength="30" readonly="true"/>
            <form:errors path="membershipAknNo" cssClass="help-block"/>
            </div>
        </c:if>

        <spring:bind path="coopMember.startDate">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="coopMember.startDate" styleClass="control-label"/>
            <form:input cssClass="form-control" path="startDate" id="startDate" size="11" title="date" datepicker="true"/>
            <form:errors path="startDate" cssClass="help-block"/>
        </div>
        <spring:bind path="coopMember.endDate">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="coopMember.endDate" styleClass="control-label"/>
            <form:input cssClass="form-control" path="endDate" id="endDate" size="11" title="date" datepicker="true"/>
            <form:errors path="endDate" cssClass="help-block"/>
        </div>

        <br/>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty coopMember.id}">
                <button type="submit" class="btn btn-warning" name="delete"
                        onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>

            <a class="btn btn-default" name="cancel" href="${ctx}/memberform?id=${coopMember.memberId}">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </a>
        </div>
        </form:form>
    </div>
</div>
    <v:javascript formName="coopMember" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
    <script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
    <script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#startDate').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
            $('#endDate').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
            $("input[type='text']:visible:enabled:first", document.forms['coopMemberForm']).focus();

            $("#memberId").select2();
            $("#coopId").select2();

        });
    </script>
