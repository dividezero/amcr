<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="systemSettingList.title"/></title>
    <meta name="menu" content="SystemSettingMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="systemSettingList.heading"/></h2>

    <form method="get" action="${ctx}/systemSettings" id="searchForm" class="form-inline">
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

    <fmt:message key="systemSettingList.message"/>
<br/><br/>
    <div id="actions" class="btn-group">
        <%--<a href='<c:url value="/systemSettingform"/>' class="btn btn-primary">--%>
            <%--<i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>--%>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="systemSettingList" class="table table-condensed table-striped table-hover" requestURI="" id="systemSettingList" export="false" pagesize="25">
    <%--<display:column property="id" sortable="true" href="systemSettingform" media="html"--%>
        <%--paramId="id" paramProperty="id" titleKey="systemSetting.id"/>--%>
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/systemSettingform?id=${systemSettingList.id}"/>
    <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
        <i class="fa fa-pencil-square-o"></i>
    </a>
    </display:column>
    <display:column property="id" media="csv excel xml pdf" titleKey="systemSetting.id"/>
    <display:column property="displayName" sortable="true" titleKey="systemSetting.displayName"/>
    <display:column property="description" sortable="true" titleKey="systemSetting.description"/>
    <display:column property="propertyValue" sortable="true" titleKey="systemSetting.propertyValue"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="systemSettingList.systemSetting"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="systemSettingList.systemSettings"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="systemSettingList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="systemSettingList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="systemSettingList.title"/>.pdf</display:setProperty>
</display:table>
