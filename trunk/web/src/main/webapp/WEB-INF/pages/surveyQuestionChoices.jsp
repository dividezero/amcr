<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="surveyQuestionChoiceList.title"/></title>
    <meta name="menu" content="SurveyQuestionChoiceMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="surveyQuestionChoiceList.heading"/></h2>

    <form method="get" action="${ctx}/surveyQuestionChoices" id="searchForm" class="form-inline">
    <div id="search" class="text-right">
        <span class="col-sm-8">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
        </span>
        <button id="button.search" class="btn btn-default" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="surveyQuestionChoiceList.message"/>

    <div id="actions" class="btn-group">
        <a href='<c:url value="/surveyQuestionChoiceform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="surveyQuestionChoiceList" class="table table-condensed table-striped table-hover" requestURI="" id="surveyQuestionChoiceList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="surveyQuestionChoiceform" media="html"
        paramId="id" paramProperty="id" titleKey="surveyQuestionChoice.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="surveyQuestionChoice.id"/>
    <display:column property="createdBy" sortable="true" titleKey="surveyQuestionChoice.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="surveyQuestionChoice.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="surveyQuestionChoice.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="surveyQuestionChoice.modifiedTime"/>
    <display:column property="description" sortable="true" titleKey="surveyQuestionChoice.description"/>
    <display:column property="sequence" sortable="true" titleKey="surveyQuestionChoice.sequence"/>
    <display:column property="surveyQuestionId" sortable="true" titleKey="surveyQuestionChoice.surveyQuestionId"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="surveyQuestionChoiceList.surveyQuestionChoice"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="surveyQuestionChoiceList.surveyQuestionChoices"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="surveyQuestionChoiceList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="surveyQuestionChoiceList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="surveyQuestionChoiceList.title"/>.pdf</display:setProperty>
</display:table>
