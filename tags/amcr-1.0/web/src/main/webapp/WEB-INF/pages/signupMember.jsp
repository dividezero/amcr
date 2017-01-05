<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>

<body class="signup"/>

<div class="col-sm-2">
    <h2><fmt:message key="signup.heading"/></h2>
    <p><fmt:message key="signup.message"/></p>
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

    <form:form commandName="user" method="post" action="signup" id="signupForm" autocomplete="off"
               cssClass="well" onsubmit="return validateSignup(this)">

    <form:hidden path="member.id"/>
    <form:hidden path="member.version"/>
    <form:hidden path="member.createdBy"/>
    <form:hidden path="member.createdTime"/>
    <form:hidden path="member.modifiedBy"/>
    <form:hidden path="member.modifiedTime"/>
    <form:hidden path="member.description"/>
    <form:hidden path="member.coopId"/>
    <form:hidden path="member.typeFlag"/>
    <form:hidden path="member.enabled"/>

    <spring:bind path="member.designation">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.designation" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.designation" items="${designationList}"/>
    <form:errors path="member.designation" cssClass="help-block"/>
    </div>

    <spring:bind path="member.name">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.name" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.name" id="member.name"  maxlength="50"/>
    <form:errors path="member.name" cssClass="help-block"/>
    </div>
    <spring:bind path="member.icNumber">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.icNumber" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.icNumber" id="member.icNumber"  maxlength="50"/>
    <form:errors path="member.icNumber" cssClass="help-block"/>
    </div>

    <spring:bind path="member.dateOfBirth">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.dateOfBirth" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.dateOfBirth" id="member.dateOfBirth" size="11" title="date" datepicker="true"/>
    <form:errors path="member.dateOfBirth" cssClass="help-block"/>
    </div>

    <spring:bind path="member.email">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.email" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.email" id="member.email" />
    <form:errors path="member.email" cssClass="help-block"/>
    </div>

    <spring:bind path="member.email2">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.email2" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.email2" id="email2" />
    <form:errors path="member.email2" cssClass="help-block"/>
    </div>

    <spring:bind path="member.phoneNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.phoneNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.phoneNo" id="member.phoneNo"  maxlength="12"/>
    <form:errors path="member.phoneNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.phoneNoOffice">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.phoneNoOffice" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.phoneNoOffice" id="member.phoneNoOffice"  maxlength="12"/>
    <form:errors path="member.phoneNoOffice" cssClass="help-block"/>
    </div>

    <spring:bind path="member.phoneNoMobile">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.phoneNoMobile" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.phoneNoMobile" id="member.phoneNoMobile"  maxlength="12"/>
    <form:errors path="member.phoneNoMobile" cssClass="help-block"/>
    </div>

    <spring:bind path="member.amcrCode">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <c:if test="${not empty id}">
        <appfuse:label key="member.amcrCode" styleClass="control-label"/>
        <form:input cssClass="form-control" path="member.amcrCode" id="member.amcrCode"  readonly="true"/>
        <form:errors path="member.amcrCode" cssClass="help-block"/>
        </div>
    </c:if>

    <spring:bind path="member.membershipNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.membershipNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.membershipNo" id="member.membershipNo"  maxlength="50"/>
    <form:errors path="member.membershipNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.gender">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.gender" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.gender" items="${genderList}"/>
    <form:errors path="member.gender" cssClass="help-block"/>
    </div>
    <spring:bind path="member.maritalStatus">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.maritalStatus" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.maritalStatus" items="${maritalStatusList}"/>
    <form:errors path="member.maritalStatus" cssClass="help-block"/>
    </div>
    <spring:bind path="member.race">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.race" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.race" items="${raceList}"/>
    <form:errors path="member.race" cssClass="help-block"/>
    </div>

    <spring:bind path="member.preferredLanguage">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.preferredLanguage" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.preferredLanguage" items="${languageList}"/>
    <form:errors path="member.preferredLanguage" cssClass="help-block"/>
    </div>

    <spring:bind path="member.activeMembershipBinNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.activeMembershipBinNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.activeMembershipBinNo" id="member.activeMembershipBinNo"  maxlength="50"/>
    <form:errors path="member.activeMembershipBinNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.coinsBinNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.coinsBinNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.coinsBinNo" id="member.coinsBinNo"  maxlength="50"/>
    <form:errors path="member.coinsBinNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.coinsAccountNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.coinsAccountNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.coinsAccountNo" id="member.coinsAccountNo"  maxlength="50"/>
    <form:errors path="member.coinsAccountNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.coinsBank">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.coinsBank" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.coinsBankId" items="${banksList}"/>
    <form:errors path="member.coinsBank" cssClass="help-block"/>
    </div>

    <%--ADDRESS--%>
    <div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.address.address"/>
            <form:input cssClass="form-control" path="member.address.address1" id="address.address1"/>
            <form:input cssClass="form-control" path="member.address.address2" id="address.address2"/>
            <form:input cssClass="form-control" path="member.address.address3" id="address.address3"/>
        </div>
        <div class="row">
            <div class="col-sm-7 form-group">
                <appfuse:label styleClass="control-label" key="user.address.city"/>
                <form:input cssClass="form-control" path="member.address.city" id="address.city"/>
            </div>
            <div class="col-sm-2 form-group">
                <appfuse:label styleClass="control-label" key="user.address.province"/>
                <form:input cssClass="form-control" path="member.address.province" id="address.province"/>
            </div>
            <div class="col-sm-3 form-group">
                <appfuse:label styleClass="control-label" key="user.address.postalCode"/>
                <form:input cssClass="form-control" path="member.address.postalCode" id="address.postalCode"/>
            </div>
        </div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.address.country"/>
            <appfuse:country name="member.address.country" prompt="" default="${user.address.country}"/>
        </div>
    </div>

    <spring:bind path="user.username">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="user.username"/>
            <form:input cssClass="form-control" path="username" id="username" autofocus="true"/>
            <form:errors path="username" cssClass="help-block"/>
        </div>
        <spring:bind path="user.icNumber">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="user.icNumber"/>
        <form:input cssClass="form-control" path="icNumber" id="icNumber" />
        <form:errors path="icNumber" cssClass="help-block"/>
        </div>
        <div class="row">
            <spring:bind path="user.password">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.password"/>
                <form:password cssClass="form-control" path="password" id="password" showPassword="true"/>
                <form:errors path="password" cssClass="help-block"/>
            </div>
            <spring:bind path="user.passwordHint">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.passwordHint"/>
                <form:input cssClass="form-control" path="passwordHint" id="passwordHint"/>
                <form:errors path="passwordHint" cssClass="help-block"/>
            </div>
        </div>
        <div class="row">
            <spring:bind path="user.firstName">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.firstName"/>
                <form:input cssClass="form-control" path="firstName" id="firstName" maxlength="50"/>
                <form:errors path="firstName" cssClass="help-block"/>
            </div>
            <spring:bind path="user.lastName">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.lastName"/>
                <form:input cssClass="form-control" path="lastName" id="lastName" maxlength="50"/>
                <form:errors path="lastName" cssClass="help-block"/>
            </div>
        </div>
        <div class="row">
            <spring:bind path="user.email">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.email"/>
                <form:input cssClass="form-control" path="email" id="email"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
            <div class="col-sm-6 form-group">
                <appfuse:label styleClass="control-label" key="user.phoneNumber"/>
                <form:input cssClass="form-control" path="phoneNumber" id="phoneNumber"/>
            </div>
        </div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.website"/>
            <form:input cssClass="form-control" path="website" id="website"/>
        </div>

        <div class="hidden">
            <legend class="accordion-heading">
                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.address.address"/></a>
            </legend>
            <div id="collapse-address" class="accordion-body collapse">
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.address"/>
                    <form:input cssClass="form-control" path="address.address" id="address.address"/>
                </div>
                <div class="row">
                    <div class="col-sm-7 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.city"/>
                        <form:input cssClass="form-control" path="address.city" id="address.city"/>
                    </div>
                    <div class="col-sm-2 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.province"/>
                        <form:input cssClass="form-control" path="address.province" id="address.province"/>
                    </div>
                    <div class="col-sm-3 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.postalCode"/>
                        <form:input cssClass="form-control" path="address.postalCode" id="address.postalCode"/>
                    </div>
                </div>
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.country"/>
                    <appfuse:country name="address.country" prompt="" default="${user.address.country}"/>
                </div>
            </div>
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