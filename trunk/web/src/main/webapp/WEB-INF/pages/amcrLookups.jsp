<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="amcrLookupList.title"/></title>
    <meta name="menu" content="AmcrLookupMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="amcrLookupList.heading"/></h2>

    <form method="get" action="${ctx}/amcrLookups" id="searchForm" class="form-inline">
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

    <fmt:message key="amcrLookupList.message"/>
<br/><br/>
    <div id="actions" class="btn-group">
        <%--<a href='<c:url value="/amcrLookupform"/>' class="btn btn-primary">--%>
            <%--<i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>--%>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="amcrLookupList" class="table table-condensed table-striped table-hover" requestURI="" id="amcrLookupList" export="false" pagesize="25">
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/amcrLookupform?id=${amcrLookupList.id}"/>
    <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
        <i class="fa fa-pencil-square-o"></i>
    </a>
    </display:column>
    <display:column property="head" sortable="true" titleKey="amcrLookup.head"/>
    <display:column property="tableName" sortable="true" titleKey="amcrLookup.tableName"/>
    <display:column property="tail" sortable="true" titleKey="amcrLookup.tail"/>
    <display:column property="tailLength" sortable="true" titleKey="amcrLookup.tailLength"/>
    <display:column property="tailPad" sortable="true" titleKey="amcrLookup.tailPad"/>
    <display:column property="createdBy" sortable="true" titleKey="amcrLookup.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="amcrLookup.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="amcrLookup.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="amcrLookup.modifiedTime"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="amcrLookupList.amcrLookup"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="amcrLookupList.amcrLookups"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="amcrLookupList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="amcrLookupList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="amcrLookupList.title"/>.pdf</display:setProperty>
</display:table>
