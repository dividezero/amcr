<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="coopList.title"/></title>
    <meta name="menu" content="CoopMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-12">
    <h2><i class="fa fa-university"></i> &nbsp;<fmt:message key="coopList.heading"/></h2>

    <form method="get" action="${ctx}/coops" id="searchForm" class="form-inline">
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

    <fmt:message key="coopList.message"/>
	<br/><br/>
    <div id="actions" class="btn-group">
        <a href='<c:url value="/coopform?edit=true"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/coopImports"/>' class="btn btn-success"><i class="fa fa-file-excel-o"></i> &nbsp; <fmt:message key="coopImportList.title"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
    <div id="actions" class="btn-group pull-right">
        <a href='<c:url value="/coops"/>' class="btn <c:if test="${empty view}" >btn-primary</c:if>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.coop.viewAll"/></a>
        <a href='<c:url value="/coops?view=new"/>' class="btn <c:if test="${view == 'new'}" >btn-primary</c:if>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.coop.viewNew"/></a>
        <a href='<c:url value="/coops?view=preloaded"/>' class="btn <c:if test="${view == 'preloaded'}" >btn-primary</c:if>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.coop.viewPreloaded"/></a>
    </div>

<display:table name="coopList" class="table table-condensed table-striped table-hover" requestURI="" id="coopList" export="false" pagesize="50">
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/coopform?id=${coopList.id}"/>
        <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
            <i class="fa fa-pencil-square-o"></i>
        </a>
    </display:column>
    <display:column property="name" sortable="true" titleKey="coop.name"/>
    <display:column property="amcrCode" sortable="true" titleKey="coop.amcrCode"/>
    <display:column property="bpaCode" sortable="true" titleKey="coop.bpaCode"/>
    <display:column property="coopCode" sortable="true" titleKey="coop.coopCode"/>
    <display:column property="phoneNo" sortable="true" titleKey="coop.phoneNo"/>
    <display:column titleKey="coop.modifiedTime" media="html" sortable="true" >
        <c:choose>
            <c:when test="${empty coopList.modifiedTime}">${coopList.createdTime}</c:when>
            <c:otherwise>${coopList.modifiedTime}</c:otherwise>
        </c:choose>
    </display:column>


    <%--<display:column property="createdBy" sortable="true" titleKey="amcrLookup.createdBy"/>--%>
    <%--<display:column property="createdTime" sortable="true" titleKey="amcrLookup.createdTime"/>--%>
    <%--<display:column property="modifiedBy" sortable="true" titleKey="amcrLookup.modifiedBy"/>--%>
    <%--<display:column property="modifiedTime" sortable="true" titleKey="amcrLookup.modifiedTime"/>--%>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="coopList.coop"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="coopList.coops"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="coopList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="coopList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="coopList.title"/>.pdf</display:setProperty>
</display:table>
