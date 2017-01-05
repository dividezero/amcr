<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="memberDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='memberDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="memberList.member"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="memberDetail.heading"/></h2>
    <fmt:message key="memberDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="member" method="post" action="memberform"
           id="memberForm" onsubmit="return validateMember(this)">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdTime"/>
<form:hidden path="modifiedBy"/>
<form:hidden path="modifiedTime"/>
<form:hidden path="description"/>
<form:hidden path="coopId"/>


<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#memberDetails" role="tab" data-toggle="tab"><i
            class="fa fa-user"></i> &nbsp;<fmt:message key="member.memberDetails"/></a></li>
    <li role="presentation"><a href="#contactDetails" role="tab" data-toggle="tab"><i
            class="fa fa-phone"></i> &nbsp;<fmt:message key="member.contactDetails"/></a></li>
    <li role="presentation"><a href="#coinsDetails" role="tab" data-toggle="tab"><i
            class="fa fa-bullseye"></i> &nbsp;<fmt:message key="member.coinsDetails"/></a></li>
    <li role="presentation"><a href="#loginDetails" role="tab" data-toggle="tab"><i
            class="fa fa-key"></i> &nbsp;<fmt:message key="member.loginDetails"/></a></li>
    <c:if test="${not empty member.id && (currentUser.memberId == member.id || currentUser.adminUser)}">
        <li role="presentation"><a href="#coopDetails" role="tab" data-toggle="tab"><i
                class="fa fa-university"></i> &nbsp;<fmt:message key="member.coopDetails"/></a></li>
    </c:if>
</ul>
<div class="well">
<!-- Tab panes -->
<div class="tab-content">
<div role="tabpanel" class="tab-pane active" id="memberDetails">

    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="member.amcrCode">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.amcrCode" styleClass="control-label"/>
                <form:input cssClass="form-control" path="amcrCode" id="amcrCode" maxlength="50" readonly="true"/>
                <form:errors path="amcrCode" cssClass="help-block"/>
                <span class="help-block"><fmt:message key="help.amcrCode"/></span>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="member.designation">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.designation" styleClass="control-label"/>
                <form:select cssClass="form-control" path="designation" items="${designationList}"/>
                <form:errors path="designation" cssClass="help-block"/>
            </div>
        </div>
    </div>
    <spring:bind path="member.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="member.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name" maxlength="50"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="member.icNumber">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.icNumber" styleClass="control-label"/>
                <form:input cssClass="form-control" path="icNumber" id="icNumber" maxlength="50"/>
                <form:errors path="icNumber" cssClass="help-block"/>
            </div>
            <span class="help-block"><fmt:message key="help.icNumber"/></span>
        </div>
        <div class="col-sm-6">

            <spring:bind path="member.dateOfBirth">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.dateOfBirth" styleClass="control-label"/>
                <form:input cssClass="form-control" path="dateOfBirth" id="dateOfBirth" size="11" title="date"
                            datepicker="true"/>
                <form:errors path="dateOfBirth" cssClass="help-block"/>
            </div>
            <span class="help-block"><fmt:message key="help.datepicker"/></span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="member.gender">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.gender" styleClass="control-label"/>
                <form:select cssClass="form-control" path="gender" items="${genderList}"/>
                <form:errors path="gender" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="member.maritalStatus">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.maritalStatus" styleClass="control-label"/>
                <form:select cssClass="form-control" path="maritalStatus" items="${maritalStatusList}"/>
                <form:errors path="maritalStatus" cssClass="help-block"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="member.race">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.race" styleClass="control-label"/>
                <form:select cssClass="form-control" path="race" items="${raceList}"/>
                <form:errors path="race" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="member.preferredLanguage">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.preferredLanguage" styleClass="control-label"/>
                <form:select cssClass="form-control" path="preferredLanguage" items="${languageList}"/>
                <form:errors path="preferredLanguage" cssClass="help-block"/>
            </div>
        </div>
    </div>


    <spring:bind path="member.membershipNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="member.membershipNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="membershipNo" id="membershipNo" maxlength="50"/>
        <form:errors path="membershipNo" cssClass="help-block"/>
    </div>


    <spring:bind path="member.activeMembershipBinNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="member.activeMembershipBinNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="activeMembershipBinNo" id="activeMembershipBinNo" maxlength="50"/>
        <form:errors path="activeMembershipBinNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.typeFlag">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="member.typeFlag" styleClass="control-label"/>
        <c:choose>
            <c:when test="${currentUser.adminUser}">
                <form:select cssClass="form-control" path="typeFlag" items="${typeFlagList}"/>
            </c:when>
            <c:otherwise>
                <div>${member.typeFlag}</div>
                <form:hidden path="typeFlag"/>
            </c:otherwise>
        </c:choose>
        <form:errors path="typeFlag" cssClass="help-block"/>
    </div>
    <spring:bind path="member.enabled">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="member.enabled" styleClass="control-label"/>
        <form:checkbox path="enabled" id="enabled" cssClass="checkbox"/>
        <form:errors path="enabled" cssClass="help-block"/>
    </div>
</div>
<div role="tabpanel" class="tab-pane" id="contactDetails">

    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="member.email">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.email" styleClass="control-label"/>
                <form:input cssClass="form-control" path="email" id="email"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="member.email2">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.email2" styleClass="control-label"/>
                <form:input cssClass="form-control" path="email2" id="email2"/>
                <form:errors path="email2" cssClass="help-block"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="member.phoneNo">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.phoneNo" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNo" id="phoneNo" maxlength="12"/>
                <form:errors path="phoneNo" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="member.phoneNoOffice">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.phoneNoOffice" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNoOffice" id="phoneNoOffice" maxlength="12"/>
                <form:errors path="phoneNoOffice" cssClass="help-block"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="member.phoneNoMobile">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="member.phoneNoMobile" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNoMobile" id="phoneNoMobile" maxlength="12"/>
                <form:errors path="phoneNoMobile" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6"></div>
    </div>


        <%--ADDRESS--%>
    <div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.address.address"/>
            <form:input cssClass="form-control" path="address.address1" id="address.address1"/>
            <form:input cssClass="form-control" path="address.address2" id="address.address2"/>
            <form:input cssClass="form-control" path="address.address3" id="address.address3"/>
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
<div role="tabpanel" class="tab-pane" id="coinsDetails">
    <spring:bind path="member.coinsBinNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="member.coinsBinNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="coinsBinNo" id="coinsBinNo" maxlength="50"/>
        <form:errors path="coinsBinNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.coinsAccountNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="member.coinsAccountNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="coinsAccountNo" id="coinsAccountNo" maxlength="50"/>
        <form:errors path="coinsAccountNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.coinsBank">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="member.coinsBank" styleClass="control-label"/>
        <form:select cssClass="form-control" path="coinsBankId" items="${banksList}"/>
        <form:errors path="coinsBank" cssClass="help-block"/>
    </div>
</div>
<div role="tabpanel" class="tab-pane" id="loginDetails">
    <form:hidden path="user.id"/>
    <form:hidden path="user.version"/>
    <form:hidden path="user.createdBy"/>
    <form:hidden path="user.createdTime"/>
    <form:hidden path="user.modifiedBy"/>
    <form:hidden path="user.modifiedTime"/>
    <form:hidden path="user.password"/>
    <form:hidden path="user.passwordHint"/>
    <form:hidden path="user.email"/>

    <spring:bind path="user.username">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="user.username"/>
        <form:input cssClass="form-control" path="user.username" id="user.username" readonly="true"/>
        <form:errors path="user.username" cssClass="help-block"/>
        <c:if test="${member.user.id == currentUser.id}">
            <span class="help-block">
                <a href="<c:url value="/updatePassword" />"><fmt:message key='updatePassword.changePasswordLink'/></a>
            </span>
        </c:if>
    </div>


    <%--<spring:bind path="user.email">--%>
    <%--<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">--%>
        <%--</spring:bind>--%>
        <%--<appfuse:label styleClass="control-label" key="user.email"/>--%>
        <%--<form:input cssClass="form-control" path="user.email" id="user.email"/>--%>
        <%--<form:errors path="user.email" cssClass="help-block"/>--%>
    <%--</div>--%>
        <%--HIDDEN--%>
    <div class="form-group">
            <%--<label class="control-label"><fmt:message key="user.roles"/>:</label>--%>
        <div class="readonly">
            <c:forEach var="role" items="${user.roleList}" varStatus="status">
                <c:out value="${role.label}"/><c:if test="${!status.last}">,</c:if>
                <input type="hidden" name="user.userRoles" value="<c:out value="${role.label}"/>"/>
            </c:forEach>
        </div>
        <form:hidden path="user.enabled"/>
        <form:hidden path="user.accountExpired"/>
        <form:hidden path="user.accountLocked"/>
        <form:hidden path="user.credentialsExpired"/>
    </div>
</div>
<c:if test="${not empty member.id && (currentUser.memberId == member.id || currentUser.adminUser)}">
    <div role="tabpanel" class="tab-pane" id="coopDetails">
        <h2><fmt:message key="member.coop"/></h2>
        <a href="${ctx}/coopMemberform?memberId=${member.id}" class="btn btn-primary pull-right"><i
                class="icon-white icon-plus-sign"></i> <fmt:message key="button.add"/></a>
        <display:table name="coopMemberList" class="table table-condensed table-striped table-hover" requestURI=""
                       id="coopMemberList" export="false" pagesize="25">
            <display:column titleKey="title.actions" style="width: 20%" media="html">
                <fmt:message key="view.or.edit" var="viewEdit"/>
                <c:url var="editUrl" value="/coopMemberform?memberId=${member.id}&id=${coopMemberList.id}"/>
                <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
                    <i class="fa fa-pencil-square-o"></i>
                </a>
                <fmt:message key="button.delete" var="viewEdit"/>
                <c:url var="editUrl" value="/memberform/deleteCoop?id=${member.id}&coopMemberId=${coopMemberList.id}"/>
                <a class="btn btn-danger btn-mini" href="${editUrl}" title="${viewEdit}">
                    <i class="fa fa-trash"></i>
                </a>
            </display:column>
            <display:column property="coop.name" sortable="true" titleKey="coop.name"/>
            <display:column property="coop.coopCode" sortable="true" titleKey="coop.coopCode"/>
            <display:column property="membershipAknNo" sortable="true" titleKey="coopMember.membershipAknNo"/>

            <display:setProperty name="paging.banner.item_name"><fmt:message
                    key="coopMemberList.coopMember"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message
                    key="coopMemberList.coopMembers"/></display:setProperty>

            <display:setProperty name="export.excel.filename"><fmt:message
                    key="coopMemberList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message
                    key="coopMemberList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message
                    key="coopMemberList.title"/>.pdf</display:setProperty>
        </display:table>

    </div>
</c:if>
</div>
<br/>

<div class="form-group">
    <button type="submit" class="btn btn-primary" name="save" id="savbtn" onclick="bCancel=false">
        <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
    </button>
    <c:if test="${not empty member.id && currentUser.adminUser}">
        <button type="submit" id="delbtn" class="btn btn-warning" name="delete"
                onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
            <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
        </button>
    </c:if>

    <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
        <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
    </button>
</div>
</div>


</form:form>
</div>

<v:javascript formName="member" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
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
    $(document).ready(function () {

        $("input[type='text']:visible:enabled:first", document.forms['memberForm']).focus();
        $('#dateOfBirth').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
