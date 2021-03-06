<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="coopDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='coopDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="coopList.coop"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="coopDetail.heading"/></h2>
    <fmt:message key="coopDetail.message"/>
</div>

<div class="col-sm-8">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="coop" method="post" action="coopform" id="coopForm" onsubmit="return validateCoop(this)">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdTime"/>
<form:hidden path="modifiedBy"/>
<form:hidden path="modifiedTime"/>
<form:hidden path="memberTotal"/>
<form:hidden path="memberMale"/>
<form:hidden path="memberFemale"/>

<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#coopDetails" role="tab" data-toggle="tab"><i
            class="fa fa-university"></i> &nbsp;<fmt:message key="coop.coopDetails"/></a></li>
    <li role="presentation"><a href="#contactDetails" role="tab" data-toggle="tab"><i class="fa fa-phone"></i>
        &nbsp;<fmt:message key="coop.contactDetails"/></a></li>
    <c:if test="${not empty coop.user.id}">
        <li role="presentation"><a href="#loginDetails" role="tab" data-toggle="tab"><i class="fa fa-key"></i>
            &nbsp;<fmt:message key="coop.loginDetails"/></a></li>
    </c:if>
    <c:if test="${not empty coop.id}">
        <li role="presentation"><a href="#coopMembers" role="tab" data-toggle="tab"><i class="fa fa-users"></i>
            &nbsp;<fmt:message key="coop.coopMembers"/></a></li>
    </c:if>
</ul>
<!-- Tab panes -->
<div class="well">
<div class="tab-content">
<div role="tabpanel" class="tab-pane active" id="coopDetails">


    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="coop.amcrCode">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.amcrCode" styleClass="control-label"/>
                <form:input cssClass="form-control" path="amcrCode" id="amcrCode" maxlength="20" readonly="true"/>
                <form:errors path="amcrCode" cssClass="help-block"/>
                <span class="help-block"><fmt:message key="help.amcrCode"/></span>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="coop.coopCode">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.coopCode" styleClass="control-label"/>
                <form:input cssClass="form-control" path="coopCode" id="coopCode" maxlength="8"/>
                <form:errors path="coopCode" cssClass="help-block"/>
            </div>
        </div>
    </div>


    <spring:bind path="coop.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name" maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.function">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.function" styleClass="control-label"/>
        <form:input cssClass="form-control" path="function" id="function" maxlength="200"/>
        <form:errors path="function" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description" maxlength="500"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="coop.employerCode">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.employerCode" styleClass="control-label"/>
                <form:input cssClass="form-control" path="employerCode" id="employerCode" maxlength="20"/>
                <form:errors path="employerCode" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="coop.website">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.website" styleClass="control-label"/>
                <form:input cssClass="form-control" path="website" id="website" maxlength="100"/>
                <form:errors path="website" cssClass="help-block"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="coop.incorporatedDate">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.incorporatedDate" styleClass="control-label"/>
                <form:input cssClass="form-control" path="incorporatedDate" id="incorporatedDate" size="11" title="date"
                            datepicker="true"/>
                <form:errors path="incorporatedDate" cssClass="help-block"/>
                <span class="help-block"><fmt:message key="help.datepicker"/></span>
            </div>
        </div>
        <div class="col-sm-6">

        </div>
    </div>


    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="coop.skmRegistrationNo">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.skmRegistrationNo" styleClass="control-label"/>
                <form:input cssClass="form-control" path="skmRegistrationNo" id="skmRegistrationNo"/>
                <form:errors path="skmRegistrationNo" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="coop.skmJoinDate">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.skmJoinDate" styleClass="control-label"/>
                <form:input cssClass="form-control" path="skmJoinDate" id="skmJoinDate" size="11" title="date"
                            datepicker="true"/>
                <form:errors path="skmJoinDate" cssClass="help-block"/>
                <span class="help-block"><fmt:message key="help.datepicker"/></span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="coop.bpaCode">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.bpaCode" styleClass="control-label"/>
                <form:input cssClass="form-control" path="bpaCode" id="bpaCode"/>
                <form:errors path="bpaCode" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="coop.angkasaJoinDate">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.angkasaJoinDate" styleClass="control-label"/>
                <form:input cssClass="form-control" path="angkasaJoinDate" id="angkasaJoinDate" size="11" title="date"
                            datepicker="true"/>
                <form:errors path="angkasaJoinDate" cssClass="help-block"/>
                <span class="help-block"><fmt:message key="help.datepicker"/></span>
            </div>
        </div>
    </div>

    <spring:bind path="coop.coopBusinessType">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.coopBusinessType" styleClass="control-label"/>
        <form:select cssClass="form-control" path="coopBusinessTypeId" items="${coopBusinessTypeList}"/>
        <form:errors path="coopBusinessType" cssClass="help-block"/>
    </div>

    <spring:bind path="coop.coopBusinessType2">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.coopBusinessType2" styleClass="control-label"/>
        <form:select cssClass="form-control" path="coopBusinessType2Id" items="${coopBusinessTypeList}"/>
        <form:errors path="coopBusinessType2" cssClass="help-block"/>
    </div>

    <spring:bind path="coop.coopBusinessType3">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.coopBusinessType3" styleClass="control-label"/>
        <form:select cssClass="form-control" path="coopBusinessType3Id" items="${coopBusinessTypeList}"/>
        <form:errors path="coopBusinessType3" cssClass="help-block"/>
    </div>


    <spring:bind path="coop.memberHexRunningNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.memberHexRunningNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="memberHexRunningNo" id="memberHexRunningNo" maxlength="4"/>
        <form:errors path="memberHexRunningNo" cssClass="help-block"/>
        <span class="help-block"><fmt:message key="help.memberHexRunningNo"/></span>
    </div>

    <spring:bind path="coop.typeFlag">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.typeFlag" styleClass="control-label"/>
        <c:choose>
            <c:when test="${currentUser.adminUser}">
                <form:select cssClass="form-control" path="typeFlag" items="${typeFlagList}"/>
            </c:when>
            <c:otherwise>
                <div>${member.typeFlag}</div>
                <form:hidden path="typeFlag"/>
            </c:otherwise>
        </c:choose>
        <span class="help-block"><fmt:message key="help.typeFlag"/></span>
    </div>
    <div class="row">
        <div class="col-sm-2">
            <appfuse:label key="coop.enabled" styleClass="control-label"/>
            <form:checkbox path="enabled" id="enabled" cssClass="checkbox"/>
        </div>
        <div class="col-sm-2">
            <appfuse:label key="coop.preloaded" styleClass="control-label"/>
            <form:checkbox path="preloaded" id="preloaded" cssClass="checkbox"/>
        </div>
    </div>


</div>
<div role="tabpanel" class="tab-pane" id="contactDetails">
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="coop.email">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.email" styleClass="control-label"/>
                <form:input cssClass="form-control" path="email" id="email" maxlength="50"/>
                <form:errors path="email" cssClass="help-block"/>
                <span class="help-block"><fmt:message key="help.email"/></span>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="coop.email2">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.email2" styleClass="control-label"/>
                <form:input cssClass="form-control" path="email2" id="email2" maxlength="50"/>
                <form:errors path="email2" cssClass="help-block"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="coop.phoneNo">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.phoneNo" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNo" id="phoneNo" maxlength="20"/>
                <form:errors path="phoneNo" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="coop.phoneNo2">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.phoneNo2" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNo2" id="phoneNo2" maxlength="20"/>
                <form:errors path="phoneNo2" cssClass="help-block"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="coop.phoneNo3">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.phoneNo3" styleClass="control-label"/>
                <form:input cssClass="form-control" path="phoneNo3" id="phoneNo3" maxlength="20"/>
                <form:errors path="phoneNo3" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="coop.faxNo">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="coop.faxNo" styleClass="control-label"/>
                <form:input cssClass="form-control" path="faxNo" id="faxNo" maxlength="20"/>
                <form:errors path="faxNo" cssClass="help-block"/>
            </div>
        </div>
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
<div role="tabpanel" class="tab-pane" id="loginDetails">
    <form:hidden path="userId"/>
    <form:hidden path="user.id"/>
    <form:hidden path="user.version"/>
    <form:hidden path="user.createdBy"/>
    <form:hidden path="user.createdTime"/>
    <form:hidden path="user.modifiedBy"/>
    <form:hidden path="user.modifiedTime"/>

    <spring:bind path="user.username">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="user.username"/>
        <form:input cssClass="form-control" path="user.username" id="user.username" readonly="true"/>
        <form:errors path="user.username" cssClass="help-block"/>
    </div>

    <%--<c:if test="${empty coop.user.id}">--%>
        <%--<spring:bind path="user.password">--%>
            <%--<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">--%>
        <%--</spring:bind>--%>
        <%--<appfuse:label styleClass="control-label" key="user.password"/>--%>
        <%--<form:password cssClass="form-control" path="user.password" id="user.password"/>--%>
        <%--<form:errors path="user.password" cssClass="help-block"/>--%>
        <%--</div>--%>
    <%--</c:if>--%>
    <c:if test="${coop.user.id == currentUser.id}">
            <span class="help-block">
            <a href="<c:url value="/updatePassword" />"><fmt:message key='updatePassword.changePasswordLink'/></a>
        </span>
    </c:if>

    <spring:bind path="user.passwordHint">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="user.passwordHint"/>
        <form:input cssClass="form-control" path="user.passwordHint" id="user.passwordHint"/>
        <form:errors path="user.passwordHint" cssClass="help-block"/>
    </div>
        <%--<div class="row">--%>
        <%--<spring:bind path="user.firstName">--%>
        <%--<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">--%>
        <%--</spring:bind>--%>
        <%--<appfuse:label styleClass="control-label" key="user.firstName"/>--%>
        <%--<form:input cssClass="form-control" path="user.firstName" id="user.firstName" maxlength="50"/>--%>
        <%--<form:errors path="user.firstName" cssClass="help-block"/>--%>
        <%--</div>--%>
        <%--<spring:bind path="user.lastName">--%>
        <%--<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">--%>
        <%--</spring:bind>--%>
        <%--<appfuse:label styleClass="control-label" key="user.lastName"/>--%>
        <%--<form:input cssClass="form-control" path="user.lastName" id="user.lastName" maxlength="50"/>--%>
        <%--<form:errors path="user.lastName" cssClass="help-block"/>--%>
        <%--</div>--%>
        <%--</div>--%>
    <div class="row" hidden>
        <spring:bind path="user.email">
        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label styleClass="control-label" key="user.email"/>
            <form:input cssClass="form-control" path="user.email" id="user.email" readonly="true"/>
            <form:errors path="user.email" cssClass="help-block"/>
        </div>
        <div class="col-sm-6 form-group">
                <%--<appfuse:label styleClass="control-label" key="user.phoneNumber"/>--%>
                <%--<form:input cssClass="form-control" path="user.phoneNumber" id="user.phoneNumber"/>--%>
            <appfuse:label styleClass="control-label" key="user.website"/>
            <form:input cssClass="form-control" path="user.website" id="user.website"/>
        </div>
    </div>
        <%--<div class="form-group">--%>
        <%--<appfuse:label styleClass="control-label" key="user.website"/>--%>
        <%--<form:input cssClass="form-control" path="user.website" id="user.website"/>--%>
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
<div role="tabpanel" class="tab-pane" id="coopMembers">

    <div id="actions" class="btn-group">
        <%--<c:if test="${not empty param.edit}">--%>
            <a href='<c:url value="/memberform"/><c:if test="${not empty coop.id}">?coopId=${coop.id}</c:if>' class="btn btn-primary">
                <i class="fa fa-plus"></i> &nbsp;<fmt:message key="button.add"/></a>

            <a href='<c:url value="/coopMemberform?coopId=${coop.id}"/><c:if test="${not empty param.coopId}">?coopId=${param.coopId}</c:if>' class="btn btn-info">
                <i class="fa fa-database"></i> &nbsp; <fmt:message key="button.add.from.preloaded"/></a>
        <%--</c:if>--%>


        <c:if test="${currentUser.adminUser}">
            <a href='<c:url value="/members/exporttsv?coopId=${coop.id}"/>' class="btn btn-success"><i class="fa fa-database"></i> &nbsp;<fmt:message key="button.export.to.tsv"/></a>
        </c:if>
        <%--<a href='<c:url value="/memberImports"/>' class="btn btn-success"><i class="fa fa-file-excel-o"></i> &nbsp;<fmt:message key="memberImportList.title"/></a>--%>

            <%--<a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
    </div>

    <display:table name="memberList" class="table table-condensed table-striped table-hover" requestURI="" id="memberList" export="false" pagesize="25">
        <c:if test="${not empty param.edit}">
            <display:column titleKey="title.actions" style="width: 20%" media="html" >
                <fmt:message key="view.or.edit" var="viewEdit"/>
                <c:url var="editUrl" value="/memberform?id=${memberList.id}"/>
                <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
                    <i class="fa fa-pencil-square-o"></i>
                </a>
                <fmt:message key="button.delete" var="viewEdit"/>
                <c:url var="editUrl" value="/coopform/deleteCoopMember?id=${member.id}&coopMemberId=${memberList.id}"/>
                <a class="btn btn-danger btn-mini" href="${editUrl}" title="${viewEdit}">
                    <i class="fa fa-trash"></i>
                </a>
            </display:column>
        </c:if>
        <display:column property="member.name" sortable="true" titleKey="member.name"/>
        <display:column property="member.icNumber" sortable="true" titleKey="member.icNumber"/>
        <display:column property="member.amcrCode" sortable="true" titleKey="member.amcrCode"/>
        <display:column property="membershipAknNo" sortable="true" titleKey="coopMember.membershipAknNo"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="memberList.member"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="memberList.members"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="memberList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="memberList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="memberList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
</div>



<br/>

<div class="form-group">
    <c:choose>
        <c:when test="${empty param.edit}">
            <a class="btn btn-warning" name="edit" href="${ctx}/coopform?id=${coop.id}&edit=true">
                <i class="icon-ok"></i> <fmt:message key="button.edit"/>
            </a>
            <%--<button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">--%>
                <%--<i class="icon-remove"></i> <fmt:message key="button.cancel"/>--%>
            <%--</button>--%>
        </c:when>
        <c:otherwise>
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" data-loading-text="Saving..." >
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty coop.id}">
                <button type="submit" class="btn btn-warning" name="delete"
                        onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>
            <%--<a class="btn btn-default" name="cancel" href="${ctx}/coopform?id=${coop.id}">--%>
                <%--<i class="icon-remove"></i> <fmt:message key="button.cancel"/>--%>
            <%--</a>--%>
        </c:otherwise>
    </c:choose>
    <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
        <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
    </button>
</div>
</div>
</form:form>
</div>

<v:javascript formName="coop" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
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
//        $("#collapse-user").hide();

        $('#save').button();
        $('#save').click(function () {
            var $btn = $(this).button('loading');
        })


        $('#coopDetails a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        })

        <c:if test="${currentUser.memberUser}">
        $(':input:not(:disabled)').prop('disabled', true)
        </c:if>

        <c:if test="${empty param.edit}">
            $('#coopForm input').attr('disabled', 'disabled');
            $('#coopForm select').attr('disabled', 'disabled');
        </c:if>

        $("#createUser").click(function () {
            $("#createUser").val($(this).is(':checked') ? $("#collapse-user").show() : $("#collapse-user").hide());
        });

        $("input[type='text']:visible:enabled:first", document.forms['coopForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
        $('#incorporatedDate').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
        $('#skmJoinDate').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
        $('#angkasaJoinDate').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
