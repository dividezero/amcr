<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="eventMemberList.title"/></title>
    <meta name="menu" content="EventMemberMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-12">
    <h2><fmt:message key="eventMemberList.heading"/></h2>

    <form method="get" action="${ctx}/eventMembers" id="searchForm" class="form-inline">
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

    <fmt:message key="eventMemberList.message"/>

    <br/><br/>
    <div id="actions" class="btn-group">
        <a href='<c:url value="/eventMemberform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="eventMemberList" class="table table-condensed table-striped table-hover" requestURI="" id="eventMemberList" export="false" pagesize="25">

    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/eventMemberform?id=${memberList.id}"/>
        <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
            <i class="fa fa-pencil-square-o"></i>
        </a>
    </display:column>
    <display:column property="icNumber" sortable="true" titleKey="eventMember.icNumber"/>
    <display:column property="name" sortable="true" titleKey="eventMember.name"/>
    <display:column property="email" sortable="true" titleKey="eventMember.email"/>
    <display:column property="phoneNo" sortable="true" titleKey="eventMember.phoneNo"/>
    <display:column property="dateOfBirth" sortable="true" titleKey="eventMember.dateOfBirth"/>
    <display:column property="createdTime" sortable="true" titleKey="eventMember.createdTime"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="eventMemberList.eventMember"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="eventMemberList.eventMembers"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="eventMemberList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="eventMemberList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="eventMemberList.title"/>.pdf</display:setProperty>
</display:table>
