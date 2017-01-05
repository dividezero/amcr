\<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="employerList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="employerList.heading"/></h2>

    <form method="get" action="${ctx}/employers" id="searchForm" class="form-inline">
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

    <fmt:message key="employerList.message"/>
<br/><br/>
    <div id="actions" class="btn-group">
        <a href='<c:url value="/employerform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <%--<security:authorize access="hasAnyRole('ROLE_ADMIN')">--%>
            <a href='<c:url value="/employerImports"/>' class="btn btn-success"><i class="fa fa-file-excel-o"></i> <fmt:message key="employerImportList.title"/></a>
        <%--</security:authorize>--%>
        <%--<a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
    </div>

<display:table name="employerList" class="table table-condensed table-striped table-hover" requestURI="" id="employerList" export="false" pagesize="25">
    <%--<display:column property="id" sortable="true" href="employerform" media="html"--%>
        <%--paramId="id" paramProperty="id" titleKey="employer.id"/>--%>
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/employerform?id=${employerList.id}"/>
        <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
            <i class="fa fa-pencil-square-o"></i>
        </a>
    </display:column>
    <display:column property="id" media="csv excel xml pdf" titleKey="employer.id"/>
    <display:column property="employerCode" sortable="true" titleKey="employer.employerCode"/>
    <display:column property="name" sortable="true" titleKey="employer.name"/>
    <display:column property="phoneNo" sortable="true" titleKey="employer.phoneNo"/>
    <display:column property="employerNo" sortable="true" titleKey="employer.employerNo"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="employerList.employer"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="employerList.employers"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="employerList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="employerList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="employerList.title"/>.pdf</display:setProperty>
</display:table>
