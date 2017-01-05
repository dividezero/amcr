<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>

<body class="signup"/>

<div class="col-sm-2">
    <h2><fmt:message key="signup.heading"/></h2>
    <p><fmt:message key="signupid.message"/></p>
</div>
<div class="col-sm-7">
    <spring:bind path="user.*">
        <c:if test="${not empty status.errorMessages}">
            <div class="alert alert-danger alert-dismissable">
                <a href="#" data-dismiss="alert" class="close">&times;</a>
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
        </c:if>
    </spring:bind>

    <form:form commandName="user" method="get" action="signup" id="signupForm" autocomplete="off"
               cssClass="well" onsubmit="return validateSignup(this)">

        <spring:bind path="user.icNumber">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="user.icNumber"/>
        <form:input cssClass="form-control" path="icNumber" id="icNumber" />
        <form:errors path="icNumber" cssClass="help-block"/>
        </div>

        <spring:bind path="user.email">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="user.email"/>
        <form:input cssClass="form-control" path="email" id="email" />
        <form:errors path="email" cssClass="help-block"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.register"/>
            </button>
            <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>
</div>

<c:set var="scripts" scope="request">
<v:javascript formName="signup" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>