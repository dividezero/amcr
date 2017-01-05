<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="surveyList.title"/></title>
    <meta name="menu" content="SurveyMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="surveyList.heading"/></h2>

    <form method="get" action="${ctx}/surveys" id="searchForm" class="form-inline">
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

    <fmt:message key="surveyList.message"/>
<br/><br/>
    <div id="actions" class="btn-group">
        <a href='<c:url value="/surveyform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="surveyList" class="table table-condensed table-striped table-hover" requestURI="" id="surveyList" export="false" pagesize="25">
    <display:column property="id" sortable="true" href="surveyform" media="html"
        paramId="id" paramProperty="id" titleKey="survey.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="survey.id"/>
    <display:column property="createdBy" sortable="true" titleKey="survey.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="survey.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="survey.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="survey.modifiedTime"/>
    <display:column property="description" sortable="true" titleKey="survey.description"/>
    <display:column sortProperty="endDate" sortable="true" titleKey="survey.endDate">
         <fmt:formatDate value="${surveyList.endDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="name" sortable="true" titleKey="survey.name"/>
    <display:column sortProperty="startDate" sortable="true" titleKey="survey.startDate">
         <fmt:formatDate value="${surveyList.startDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="version" sortable="true" titleKey="survey.version"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="surveyList.survey"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="surveyList.surveys"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="surveyList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="surveyList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="surveyList.title"/>.pdf</display:setProperty>
</display:table>
