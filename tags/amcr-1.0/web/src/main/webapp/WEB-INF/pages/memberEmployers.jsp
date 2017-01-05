<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberEmployerList.title"/></title>
    <meta name="menu" content="MemberEmployerMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="memberEmployerList.heading"/></h2>

    <form method="get" action="${ctx}/memberEmployers" id="searchForm" class="form-inline">
    <div id="search" class="text-right">
        <span class="col-sm-8">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
        </span>
        <button id="button.search" class="btn btn-default" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="memberEmployerList.message"/>

    <div id="actions" class="btn-group">
        <a href='<c:url value="/memberEmployerform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="memberEmployerList" class="table table-condensed table-striped table-hover" requestURI="" id="memberEmployerList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="memberEmployerform" media="html"
        paramId="id" paramProperty="id" titleKey="memberEmployer.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="memberEmployer.id"/>
    <display:column property="createdBy" sortable="true" titleKey="memberEmployer.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="memberEmployer.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="memberEmployer.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="memberEmployer.modifiedTime"/>
    <display:column property="employerId" sortable="true" titleKey="memberEmployer.employerId"/>
    <display:column property="memberId" sortable="true" titleKey="memberEmployer.memberId"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="memberEmployerList.memberEmployer"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="memberEmployerList.memberEmployers"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="memberEmployerList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="memberEmployerList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="memberEmployerList.title"/>.pdf</display:setProperty>
</display:table>
