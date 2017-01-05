<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberImportList.title"/></title>
    <meta name="menu" content="MemberImportMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="memberImportList.heading"/></h2>

    <form method="get" action="${ctx}/memberImports" id="searchForm" class="form-inline">
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

    <fmt:message key="memberImportList.message"/>
    <br/><br/>
    <div class="well">
        <form:form commandName="memberImport" method="post" enctype="multipart/form-data" action="memberImportform"
                   id="memberImportForm" onsubmit="return validateMemberImport(this)">
            <spring:bind path="memberImport.name">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="memberImport.name" styleClass="control-label"/>
                <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
            <spring:bind path="memberImport.coopMatchUsing">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="memberImport.coopMatchUsing" styleClass="control-label"/>
            <form:select cssClass="form-control" path="coopMatchUsing" items="${memberImportTypeList}"/>
            <form:errors path="coopMatchUsing" cssClass="help-block"/>
            </div>

            <spring:bind path="memberImport.file">
            <fieldset class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
                </spring:bind>
                <appfuse:label key="memberImport.file" styleClass="control-label" />
                <div class="controls">
                    <input type="file" name="file" id="file" />
                    <form:errors path="file" cssClass="help-inline" />
                </div>
            </fieldset>

            <br/><br/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                    <i class="icon-ok icon-white"></i> <fmt:message key="button.import"/>
                </button>
                <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
            </div>
        </form:form>
    </div>

    <%--<div id="actions" class="btn-group">--%>
        <%--<a href='<c:url value="/memberImportform"/>' class="btn btn-primary">--%>
            <%--<i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>--%>
        <%--<a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
    <%--</div>--%>

<display:table name="memberImportList" class="table table-condensed table-striped table-hover" requestURI="" id="memberImportList" export="false" pagesize="25">
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/memberImportform?id=${memberImportList.id}"/>
    <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
        <i class="fa fa-pencil-square-o"></i>
    </a>
    </display:column>
    <display:column property="name" sortable="true" titleKey="memberImport.name"/>
    <display:column property="status" sortable="true" titleKey="memberImport.status"/>
    <display:column property="createdBy" sortable="true" titleKey="memberImport.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="memberImport.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="memberImport.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="memberImport.modifiedTime"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="memberImportList.memberImport"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="memberImportList.memberImports"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="memberImportList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="memberImportList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="memberImportList.title"/>.pdf</display:setProperty>
</display:table>
