<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="dmsMemberUpdateList.title"/></title>
    <meta name="menu" content="DmsMemberUpdateMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-12">
    <h2><fmt:message key="dmsMemberUpdateList.heading"/></h2>

    <form method="get" action="${ctx}/dmsMemberUpdates" id="searchForm" class="form-inline">
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

    <fmt:message key="dmsMemberUpdateList.message"/>
<br/><br/>
    <div id="actions" class="btn-group">
        <a href='<c:url value="/dmsMemberUpdateform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="dmsMemberUpdateList" class="table table-condensed table-striped table-hover" requestURI="" id="dmsMemberUpdateList" export="false" pagesize="25">
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/dmsMemberUpdateform?id=${dmsMemberUpdateList.id}"/>
    <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
        <i class="fa fa-pencil-square-o"></i>
    </a>
    </display:column>
    <display:column property="icNumber" sortable="true" titleKey="dmsMemberUpdate.icNumber"/>
    <display:column property="phoneNum" sortable="true" titleKey="dmsMemberUpdate.phoneNum"/>
    <display:column property="status" sortable="true" titleKey="dmsMemberUpdate.status"/>
    <display:column property="utilityField1" sortable="true" titleKey="dmsMemberUpdate.utilityField1"/>
    <display:column property="utilityField2" sortable="true" titleKey="dmsMemberUpdate.utilityField2"/>
    <display:column property="utilityField3" sortable="true" titleKey="dmsMemberUpdate.utilityField3"/>
    <display:column property="updatedMemberDate" sortable="true" titleKey="dmsMemberUpdate.updatedMemberDate"/>
    <%--<display:column property="createdBy" sortable="true" titleKey="dmsMemberUpdate.createdBy"/>--%>
    <display:column property="createdTime" sortable="true" titleKey="dmsMemberUpdate.createdTime"/>
    <%--<display:column property="modifiedBy" sortable="true" titleKey="dmsMemberUpdate.modifiedBy"/>--%>
    <%--<display:column property="modifiedTime" sortable="true" titleKey="dmsMemberUpdate.modifiedTime"/>--%>


<display:setProperty name="paging.banner.item_name"><fmt:message key="dmsMemberUpdateList.dmsMemberUpdate"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="dmsMemberUpdateList.dmsMemberUpdates"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="dmsMemberUpdateList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="dmsMemberUpdateList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="dmsMemberUpdateList.title"/>.pdf</display:setProperty>
</display:table>
