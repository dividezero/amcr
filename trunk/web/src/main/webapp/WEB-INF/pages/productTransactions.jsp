<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="productTransactionList.title"/></title>
    <meta name="menu" content="ProductTransactionMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="productTransactionList.heading"/></h2>

    <form method="get" action="${ctx}/productTransactions" id="searchForm" class="form-inline">
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

    <fmt:message key="productTransactionList.message"/>

    <div id="actions" class="btn-group">
        <a href='<c:url value="/productTransactionform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="productTransactionList" class="table table-condensed table-striped table-hover" requestURI="" id="productTransactionList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="productTransactionform" media="html"
        paramId="id" paramProperty="id" titleKey="productTransaction.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="productTransaction.id"/>
    <display:column property="createdBy" sortable="true" titleKey="productTransaction.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="productTransaction.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="productTransaction.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="productTransaction.modifiedTime"/>
    <display:column property="amount" sortable="true" titleKey="productTransaction.amount"/>
    <display:column property="transNo" sortable="true" titleKey="productTransaction.transNo"/>
    <display:column property="transactionType" sortable="true" titleKey="productTransaction.transactionType"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="productTransactionList.productTransaction"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="productTransactionList.productTransactions"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="productTransactionList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="productTransactionList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="productTransactionList.title"/>.pdf</display:setProperty>
</display:table>
