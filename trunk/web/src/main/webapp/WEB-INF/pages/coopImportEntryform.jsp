<%@ include file="/common/taglibs.jsp"%>

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

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="coop" method="post" action="coopImportEntryform" cssClass="well"
           id="coopForm" onsubmit="return validateCoop(this)">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdTime"/>
<form:hidden path="modifiedBy"/>
<form:hidden path="modifiedTime"/>
<form:hidden path="coopImportId"/>
<form:hidden path="importId"/>

<h4><fmt:message key="coop.coopDetails"/></h4>
    <spring:bind path="coop.coopCode">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coop.coopCode" styleClass="control-label"/>
        <form:input cssClass="form-control" path="coopCode" id="coopCode"  maxlength="8"/>
        <form:errors path="coopCode" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.amcrCode">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.amcrCode" styleClass="control-label"/>
        <form:input cssClass="form-control" path="amcrCode" id="amcrCode"  maxlength="20"/>
        <form:errors path="amcrCode" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.employerCode">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.employerCode" styleClass="control-label"/>
        <form:input cssClass="form-control" path="employerCode" id="employerCode"  maxlength="20"/>
        <form:errors path="employerCode" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coop.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="50"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coop.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description"  maxlength="200"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.memberHexRunningNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="coop.memberHexRunningNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="memberHexRunningNo" id="memberHexRunningNo"  maxlength="4"/>
    <form:errors path="memberHexRunningNo" cssClass="help-block"/>
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

    <spring:bind path="coop.email">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="coop.email" styleClass="control-label"/>
    <form:input cssClass="form-control" path="email" id="email"  maxlength="50"/>
    <form:errors path="email" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.email2">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="coop.email2" styleClass="control-label"/>
    <form:input cssClass="form-control" path="email2" id="email2"  maxlength="50"/>
    <form:errors path="email2" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.phoneNo">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coop.phoneNo" styleClass="control-label"/>
        <form:input cssClass="form-control" path="phoneNo" id="phoneNo"  maxlength="12"/>
        <form:errors path="phoneNo" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.phoneNo2">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coop.phoneNo2" styleClass="control-label"/>
        <form:input cssClass="form-control" path="phoneNo2" id="phoneNo2"  maxlength="12"/>
        <form:errors path="phoneNo2" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.phoneNo3">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coop.phoneNo3" styleClass="control-label"/>
        <form:input cssClass="form-control" path="phoneNo3" id="phoneNo3"  maxlength="12"/>
        <form:errors path="phoneNo3" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.website">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.website" styleClass="control-label"/>
        <form:input cssClass="form-control" path="website" id="website"  maxlength="100"/>
        <form:errors path="website" cssClass="help-block"/>
    </div>
	<spring:bind path="coop.incorporatedDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coop.incorporatedDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="incorporatedDate" id="incorporatedDate" size="11" title="date" datepicker="true"/>
        <form:errors path="incorporatedDate" cssClass="help-block"/>
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

    <spring:bind path="coop.enabled">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="coop.enabled" styleClass="control-label"/>
        <form:checkbox path="enabled" id="enabled" cssClass="checkbox"/>
        <form:errors path="enabled" cssClass="help-block"/>
    </div>
    <spring:bind path="coop.preloaded">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="coop.preloaded" styleClass="control-label"/>
        <form:checkbox path="preloaded" id="preloaded" cssClass="checkbox"/>
        <form:errors path="preloaded" cssClass="help-block"/>
    </div>


	<c:if test="${not empty coop.id}">
        <!-- Coop Business Type START -->
        <div><b><fmt:message key="coopBusinessTypeDetail.heading"/></b></div>
        <security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_COOP">

        </security:authorize>
        <div class="well">
            <display:table name="${coop.coopBusinessTypes}" class="table table-condensed table-striped table-hover" requestURI="" id="coopBusinessTypes" pagesize="20">
                <display:column property="name" sortable="true" titleKey="coopBusinessType.name"/>
                <display:column property="description" sortable="true" titleKey="coopBusinessType.description"/>
                <display:column property="code" sortable="true" titleKey="coopBusinessType.code"/>
                <display:setProperty name="paging.banner.item_name"><fmt:message key="coopBusinessTypeList.coopBusinessType"/></display:setProperty>
                <display:setProperty name="paging.banner.items_name"><fmt:message key="coopBusinessTypeList.coopBusinessTypes"/></display:setProperty>

            </display:table>
        </div>

        <!-- ContactPersons START -->
	    <%--<div><b><fmt:message key="contactPersonList.heading"/></b></div>--%>
        <%--<security:authorize ifAnyGranted="ROLE_ADMIN">--%>
		   <%--<div class="btn-toolbar">--%>
		       <%--<div align="right">--%>
		           <%--<a href="/contactPersonform?coopId=${coop.id}" class="btn btn-primary" id="btnAddContactPerson">--%>
		               <%--<i class="fa fa-plus"></i> <fmt:message key="button.add"/>--%>
		           <%--</a>--%>
		       <%--</div>--%>
		   <%--</div>--%>
        <%--</security:authorize>--%>
	    <%--<div class="well">--%>
	        <%--<display:table name="contactPersonsList" class="table table-condensed table-striped table-hover" requestURI="" id="contactPersonsList" pagesize="5">--%>
			    <%--<display:column titleKey="title.actions" style="width: 5%" media="html" >--%>
                    <%--<fmt:message key="view.or.edit" var="viewEdit"/>--%>
                    <%--<c:url var="editUrl" value="/contactPersonform?id=${contactPersonsList.id}&coopId=${coop.id}"/>--%>
                    <%--<a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">--%>
                        <%--<i class="fa fa-pencil-square-o"></i>--%>
                    <%--</a>--%>
                <%--</display:column>--%>
			    <%--<display:column property="name" sortable="true" titleKey="contactPerson.name"/>--%>
			    <%--<display:column property="workPhoneNo" sortable="true" titleKey="contactPerson.workPhoneNo"/>--%>
			    <%--<display:column property="email" sortable="true" titleKey="contactPerson.email"/>--%>
			    <%--<display:setProperty name="paging.banner.item_name"><fmt:message key="contactPersonList.contactPerson"/></display:setProperty>--%>
			    <%--<display:setProperty name="paging.banner.items_name"><fmt:message key="contactPersonList.contactPersons"/></display:setProperty>--%>

			<%--</display:table>--%>
	    <%--</div>--%>

        <!-- Coop Users START -->
        <div class="well">
            <display:table name="coopUserList" cellspacing="0" cellpadding="0" requestURI="" defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="false">
                <display:column property="username" escapeXml="true" sortable="true" titleKey="user.username" style="width: 25%"/>
                <display:column property="fullName" escapeXml="true" sortable="true" titleKey="activeUsers.fullName"
                                style="width: 34%"/>
                <display:column property="email" sortable="true" titleKey="user.email" style="width: 25%" autolink="true"
                                media="html"/>
                <display:column property="email" titleKey="user.email" media="csv xml excel pdf"/>
                <display:column sortProperty="enabled" sortable="true" titleKey="user.enabled"
                                style="width: 16%; padding-left: 15px" media="html">
                    <input type="checkbox" disabled="disabled" <c:if test="${users.enabled}">checked="checked"</c:if>/>
                </display:column>
                <display:column property="enabled" titleKey="user.enabled" media="csv xml excel pdf"/>

                <display:setProperty name="paging.banner.item_name"><fmt:message key="userList.user"/></display:setProperty>
                <display:setProperty name="paging.banner.items_name"><fmt:message key="userList.users"/></display:setProperty>

            </display:table>
        </div>
        <div class="form-group">
            <a href="/members?coopId=${coop.id}" class="btn btn-info"><i class="icon-white icon-user"></i> View Coop Members</a>
        </div>
    </c:if>
    <!-- ContactPersons END -->

    <%--<c:if test="${empty coop.id || not empty coop.user.id}">--%>

    <%--USER DETAILS--%>
    <h4><fmt:message key="coop.loginDetails"/></h4>
    <div id="collapse-user">
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
            <form:input cssClass="form-control" path="user.username" id="user.username"/>
            <form:errors path="user.username" cssClass="help-block"/>
        </div>

        <spring:bind path="user.password">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label styleClass="control-label" key="user.password"/>
            <form:password cssClass="form-control" path="user.password" id="user.password"/>
            <form:errors path="user.password" cssClass="help-block"/>
        </div>
        <spring:bind path="user.passwordHint">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label styleClass="control-label" key="user.passwordHint"/>
            <form:input cssClass="form-control" path="user.passwordHint" id="user.passwordHint"/>
            <form:errors path="user.passwordHint" cssClass="help-block"/>
        </div>
        <div class="row">
            <spring:bind path="user.firstName">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label styleClass="control-label" key="user.firstName"/>
                <form:input cssClass="form-control" path="user.firstName" id="user.firstName" maxlength="50"/>
                <form:errors path="user.firstName" cssClass="help-block"/>
            </div>
            <spring:bind path="user.lastName">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label styleClass="control-label" key="user.lastName"/>
                <form:input cssClass="form-control" path="user.lastName" id="user.lastName" maxlength="50"/>
                <form:errors path="user.lastName" cssClass="help-block"/>
            </div>
        </div>
        <div class="row">
            <spring:bind path="user.email">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label styleClass="control-label" key="user.email"/>
                <form:input cssClass="form-control" path="user.email" id="user.email"/>
                <form:errors path="user.email" cssClass="help-block"/>
            </div>
            <div class="col-sm-6 form-group">
                <appfuse:label styleClass="control-label" key="user.phoneNumber"/>
                <form:input cssClass="form-control" path="user.phoneNumber" id="user.phoneNumber"/>
            </div>
        </div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.website"/>
            <form:input cssClass="form-control" path="user.website" id="user.website"/>
        </div>

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
    <%--USER DETAILS - END--%>
    <%--</c:if>--%>

<br/><br/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
            <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
        </button>
        <a href="/coopImportform?id=${coop.coopImportId}" class="btn btn-default" id="btnCancel">
            <fmt:message key="button.cancel"/></a>
        <%--<button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">--%>
            <%--<i class="icon-remove"></i> <fmt:message key="button.cancel"/>--%>
        <%--</button>--%>
    </div>
</form:form>
</div>

<v:javascript formName="coop" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
//        $("#collapse-user").hide();

        $("#createUser").click(function(){
            $("#createUser").val( $(this).is(':checked') ? $("#collapse-user").show() : $("#collapse-user").hide() );
        });

        $("input[type='text']:visible:enabled:first", document.forms['coopForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
        $('#incorporatedDate').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
