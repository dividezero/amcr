<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberProductTransactionDeductionList.title"/></title>
    <meta name="menu" content="MemberProductTransactionDeductionMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="memberProductTransactionDeductionList.heading"/></h2>

    <form method="get" action="${ctx}/memberProductTransactionDeductions" id="searchForm" class="form-inline">
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

    <fmt:message key="memberProductTransactionDeductionList.message"/>

    <div id="actions" class="btn-group">
        <a href='<c:url value="/memberProductTransactionDeductionform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="memberProductTransactionDeductionList" class="table table-condensed table-striped table-hover" requestURI="" id="memberProductTransactionDeductionList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="memberProductTransactionDeductionform" media="html"
        paramId="id" paramProperty="id" titleKey="memberProductTransactionDeduction.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="memberProductTransactionDeduction.id"/>
    <display:column property="createdBy" sortable="true" titleKey="memberProductTransactionDeduction.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="memberProductTransactionDeduction.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="memberProductTransactionDeduction.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="memberProductTransactionDeduction.modifiedTime"/>
    <display:column property="batchId" sortable="true" titleKey="memberProductTransactionDeduction.batchId"/>
    <display:column property="deductionAmount" sortable="true" titleKey="memberProductTransactionDeduction.deductionAmount"/>
    <display:column property="deductionDate" sortable="true" titleKey="memberProductTransactionDeduction.deductionDate"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="memberProductTransactionDeductionList.memberProductTransactionDeduction"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="memberProductTransactionDeductionList.memberProductTransactionDeductions"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="memberProductTransactionDeductionList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="memberProductTransactionDeductionList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="memberProductTransactionDeductionList.title"/>.pdf</display:setProperty>
</display:table>
