<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="productList.title"/></title>
    <meta name="menu" content="ProductMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><i class="fa fa-rocket"></i>&nbsp;<fmt:message key="productList.heading"/></h2>

    <form method="get" action="${ctx}/products" id="searchForm" class="form-inline">
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

    <fmt:message key="productList.message"/>
    <br/><br/>
    <div id="actions" class="btn-group">
        <a href='<c:url value="/productform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="productList" class="table table-condensed table-striped table-hover" requestURI="" id="productList" export="true" pagesize="25">
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/productform?id=${productList.id}"/>
        <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
            <i class="fa fa-pencil-square-o"></i>
        </a>
    </display:column>
    <%--<display:column property="bankId" sortable="true" titleKey="product.bankId"/>--%>
    <display:column property="code" sortable="true" titleKey="product.code"/>
    <display:column property="name" sortable="true" titleKey="product.name"/>
    <%--<display:column property="description" sortable="true" titleKey="product.description"/>--%>
    <%--<display:column property="period" sortable="true" titleKey="product.period"/>--%>
    <%--<display:column property="periodDaysNo" sortable="true" titleKey="product.periodDaysNo"/>--%>
    <display:column property="totalAmount" sortable="true" titleKey="product.totalAmount"/>
    <display:column property="transactionType" sortable="true" titleKey="product.transactionType"/>
    <display:column titleKey="product.modifiedTime" media="html" sortable="true" >
        <c:choose>
            <c:when test="${empty productList.modifiedTime}">${productList.createdTime}</c:when>
            <c:otherwise>${productList.modifiedTime}</c:otherwise>
        </c:choose>
    </display:column>
    <display:setProperty name="paging.banner.item_name"><fmt:message key="productList.product"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="productList.products"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="productList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="productList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="productList.title"/>.pdf</display:setProperty>
</display:table>
