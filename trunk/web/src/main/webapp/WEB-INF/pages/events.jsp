<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="eventList.title"/></title>
    <meta name="menu" content="EventMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-12">
    <h2><fmt:message key="eventList.heading"/></h2>

    <form method="get" action="${ctx}/events" id="searchForm" class="form-inline">
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

    <fmt:message key="eventList.message"/>

    <br/><br/>
    <div id="actions" class="btn-group">
        <c:if test="${currentUser.adminUser}">
            <a href='<c:url value="/eventform"/>' class="btn btn-primary">
                <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        </c:if>

        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="eventList" class="table table-condensed table-striped table-hover" requestURI="" id="eventList" export="false" pagesize="25">
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/eventform?id=${eventList.id}"/>
        <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
            <i class="fa fa-pencil-square-o"></i>
        </a>
    </display:column>

    <display:column property="eventCode" sortable="true" titleKey="event.eventCode"/>
    <display:column property="name" sortable="true" titleKey="event.name"/>
    <display:column property="description" sortable="true" titleKey="event.description"/>
    <display:column property="startTime" sortable="true" titleKey="event.startTime"/>
    <display:column property="endTime" sortable="true" titleKey="event.endTime"/>
    <display:column titleKey="member.modifiedTime" media="html" sortable="true" >
    <c:choose>
        <c:when test="${empty eventList.modifiedTime}">${eventList.createdTime}</c:when>
        <c:otherwise>${eventList.modifiedTime}</c:otherwise>
    </c:choose>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="eventList.event"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="eventList.events"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="eventList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="eventList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="eventList.title"/>.pdf</display:setProperty>
</display:table>
