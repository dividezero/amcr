<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberProductList.title"/></title>
    <meta name="menu" content="MemberProductMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="memberProductList.heading"/></h2>

    <form method="get" action="${ctx}/memberProducts" id="searchForm" class="form-inline">
    <div id="search" class="text-right">
        <span class="col-sm-9">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
        </span>
        <button id="button.search" class="btn btn-default" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="memberProductList.message"/>

    <div id="actions" class="btn-group">
        <a href='<c:url value="/memberProductform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="memberProductList" class="table table-condensed table-striped table-hover" requestURI="" id="memberProductList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="memberProductform" media="html"
        paramId="id" paramProperty="id" titleKey="memberProduct.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="memberProduct.id"/>
    <display:column property="createdBy" sortable="true" titleKey="memberProduct.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="memberProduct.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="memberProduct.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="memberProduct.modifiedTime"/>
    <display:column property="commencementDate" sortable="true" titleKey="memberProduct.commencementDate"/>
    <display:column property="registerDate" sortable="true" titleKey="memberProduct.registerDate"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="memberProductList.memberProduct"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="memberProductList.memberProducts"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="memberProductList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="memberProductList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="memberProductList.title"/>.pdf</display:setProperty>
</display:table>
