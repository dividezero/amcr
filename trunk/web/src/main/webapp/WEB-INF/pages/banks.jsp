<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="bankList.title"/></title>
    <meta name="menu" content="BankMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="bankList.heading"/></h2>

    <form method="get" action="${ctx}/banks" id="searchForm" class="form-inline">
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

    <fmt:message key="bankList.message"/>
<br/><br/>
    <div id="actions" class="btn-group">
        <a href='<c:url value="/bankform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="bankList" class="table table-condensed table-striped table-hover" requestURI="" id="bankList" export="false" pagesize="25">
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/bankform?id=${bankList.id}"/>
    <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
        <i class="fa fa-pencil-square-o"></i>
    </a>
    </display:column>
    <display:column property="code" sortable="true" titleKey="bank.code"/>
    <display:column property="name" sortable="true" titleKey="bank.name"/>
    <display:column property="createdBy" sortable="true" titleKey="bank.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="bank.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="bank.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="bank.modifiedTime"/>


    <display:setProperty name="paging.banner.item_name"><fmt:message key="bankList.bank"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="bankList.banks"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="bankList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="bankList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="bankList.title"/>.pdf</display:setProperty>
</display:table>
