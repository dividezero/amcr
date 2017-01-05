<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="surveyQuestionList.title"/></title>
    <meta name="menu" content="SurveyQuestionMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="surveyQuestionList.heading"/></h2>

    <form method="get" action="${ctx}/surveyQuestions" id="searchForm" class="form-inline">
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

    <fmt:message key="surveyQuestionList.message"/>

    <div id="actions" class="btn-group">
        <a href='<c:url value="/surveyQuestionform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="surveyQuestionList" class="table table-condensed table-striped table-hover" requestURI="" id="surveyQuestionList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="surveyQuestionform" media="html"
        paramId="id" paramProperty="id" titleKey="surveyQuestion.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="surveyQuestion.id"/>
    <display:column property="createdBy" sortable="true" titleKey="surveyQuestion.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="surveyQuestion.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="surveyQuestion.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="surveyQuestion.modifiedTime"/>
    <display:column property="description" sortable="true" titleKey="surveyQuestion.description"/>
    <display:column property="questionType" sortable="true" titleKey="surveyQuestion.questionType"/>
    <display:column property="surveyId" sortable="true" titleKey="surveyQuestion.surveyId"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="surveyQuestionList.surveyQuestion"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="surveyQuestionList.surveyQuestions"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="surveyQuestionList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="surveyQuestionList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="surveyQuestionList.title"/>.pdf</display:setProperty>
</display:table>
