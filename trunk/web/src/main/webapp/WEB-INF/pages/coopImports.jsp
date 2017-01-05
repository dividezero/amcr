<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="coopImportList.title"/></title>
    <meta name="menu" content="CoopImportMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="col-sm-10">
    <h2><fmt:message key="coopImportList.heading"/></h2>

    <form method="get" action="${ctx}/coopImports" id="searchForm" class="form-inline">
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

    <fmt:message key="coopImportList.message"/>
<br/><br/>
    <div class="well">
        <form:form commandName="coopImport" method="post" enctype="multipart/form-data" action="coopImportform"
                   id="coopImportForm" onsubmit="return validateCoopImport(this)">
            <spring:bind path="coopImport.name">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="coopImport.name" styleClass="control-label"/>
            <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
            <form:errors path="name" cssClass="help-block"/>
            </div>
            <spring:bind path="coopImport.file">
                <fieldset class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
            </spring:bind>
            <appfuse:label key="coopImport.file" styleClass="control-label" />
            <div class="controls">
                <input type="file" name="file" id="file" />
                <form:errors path="file" cssClass="help-inline" />
            </div>
            </fieldset>
            <br/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" data-loading-text="Importing..." >
                    <i class="icon-ok icon-white"></i> <fmt:message key="button.import"/>
                </button>
                <a href='<c:url value="/coops"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
            </div>
        </form:form>
    </div>
    <div id="actions" class="btn-group">
        <%--<a href='<c:url value="/coopImportform"/>' class="btn btn-primary">--%>
            <%--<i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>--%>
        <%--<a href='<c:url value="/coops"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
    </div>

<display:table name="coopImportList" class="table table-condensed table-striped table-hover" requestURI="" id="coopImportList" export="true" pagesize="25">
    <display:column titleKey="title.actions" style="width: 5%" media="html" >
        <fmt:message key="view.or.edit" var="viewEdit"/>
        <c:url var="editUrl" value="/coopImportform?id=${coopImportList.id}"/>
        <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
            <i class="fa fa-pencil-square-o"></i>
        </a>
    </display:column>
    <display:column property="name" sortable="true" titleKey="coopImport.name"/>
    <display:column property="status" sortable="true" titleKey="coopImport.status"/>
    <display:column property="createdBy" sortable="true" titleKey="coopImport.createdBy"/>
    <display:column property="createdTime" sortable="true" titleKey="coopImport.createdTime"/>
    <display:column property="modifiedBy" sortable="true" titleKey="coopImport.modifiedBy"/>
    <display:column property="modifiedTime" sortable="true" titleKey="coopImport.modifiedTime"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="coopImportList.coopImport"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="coopImportList.coopImports"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="coopImportList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="coopImportList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="coopImportList.title"/>.pdf</display:setProperty>
</display:table>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#save').button();
            $('#save').click(function () {
                var $btn = $(this).button('loading');
            })
        });
    </script>