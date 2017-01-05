<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberList.title"/></title>
    <meta name="menu" content="MemberMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-12">
    <h2><c:if test="${not empty coop}">${coop.name}(${coop.amcrCode})</c:if>&nbsp;<fmt:message key="memberList.heading"/></h2>

    <form method="get" action="${ctx}/members" id="searchForm" class="form-inline">
    <div id="search" class="text-right">
        <span class="col-sm-8">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
        </span>
        <button id="button.search" class="btn btn-default btn-sm" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="memberList.message"/>

	<br/><br/>
    <div id="actions" class="btn-group">
        <a href='<c:url value="/memberform"/><c:if test="${not empty param.coopId}">?coopId=${param.coopId}</c:if>' class="btn btn-primary">
            <i class="fa fa-plus"></i> &nbsp;<fmt:message key="button.add"/></a>

        <a href='<c:url value="/coopMemberform"/><c:if test="${not empty param.coopId}">?coopId=${param.coopId}</c:if>' class="btn btn-info">
            <i class="fa fa-database"></i> &nbsp; <fmt:message key="button.add.from.preloaded"/></a>


        <c:if test="${currentUser.adminUser}">
            <a href='<c:url value="/members/exporttsv"/>' class="btn btn-success"><i class="fa fa-database"></i> &nbsp;<fmt:message key="button.export.to.tsv"/></a>
        </c:if>
        <a href='<c:url value="/memberImports"/>' class="btn btn-success"><i class="fa fa-file-excel-o"></i> &nbsp;<fmt:message key="memberImportList.title"/></a>

        <%--<a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
    </div>

<display:table name="memberList" class="table table-condensed table-striped table-hover" requestURI="" id="memberList" export="false" pagesize="25">
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/memberform?id=${memberList.id}"/>
        <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
            <i class="fa fa-pencil-square-o"></i>
        </a>
    </display:column>
    
    <display:column property="name" sortable="true" titleKey="member.name"/>
    <display:column property="amcrCode" sortable="true" titleKey="member.amcrCode"/>
    <display:column property="membershipNo" sortable="true" titleKey="member.membershipNo"/>
    <display:column property="phoneNo" sortable="true" titleKey="member.phoneNo"/>
    <display:column sortProperty="enabled" sortable="true" titleKey="member.enabled">
        <input type="checkbox" disabled="disabled" <c:if test="${memberList.enabled}">checked="checked"</c:if>/>
    </display:column>
    <display:column sortProperty="preloaded" sortable="true" titleKey="member.preloaded">
    <input type="checkbox" disabled="disabled" <c:if test="${memberList.preloaded}">checked="checked"</c:if>/>
    </display:column>
        <display:column property="createdBy" sortable="true" titleKey="member.createdBy"/>
        <display:column property="createdTime" sortable="true" titleKey="member.createdTime"/>
        <display:column property="modifiedBy" sortable="true" titleKey="member.modifiedBy"/>
        <display:column property="modifiedTime" sortable="true" titleKey="member.modifiedTime"/>
    

    <display:setProperty name="paging.banner.item_name"><fmt:message key="memberList.member"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="memberList.members"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="memberList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="memberList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="memberList.title"/>.pdf</display:setProperty>
</display:table>
