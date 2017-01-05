<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberImportDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='memberImportDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="memberImportList.memberImport"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="memberImportDetail.heading"/></h2>
    <fmt:message key="memberImportDetail.message"/>
</div>

<div class="col-sm-10">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="memberImport" method="post" enctype="multipart/form-data" action="memberImportform" cssClass="well"
           id="memberImportForm" onsubmit="return validateMemberImport(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>

    <c:choose>
        <c:when test="${not empty memberImport.id}">
            <form:hidden path="name"/>
            <form:hidden path="documentId"/>
            <form:hidden path="coopId"/>
            <form:hidden path="memberJson"/>

            <appfuse:label key="memberImport.name" styleClass="control-label"/>
            <div class="form-control">${memberImport.name}</div>

            <appfuse:label key="memberImport.documentId" styleClass="control-label"/>
            <div class="form-control">${memberImport.documentId}</div>

            <br/><br/>
            <div class="form-group">
                <%--<button type="submit" class="btn btn-primary" name="validateAll" onclick="bCancel=false">--%>
                    <%--<i class="fa fa-check"></i> <fmt:message key="button.validateAll"/>--%>
                <%--</button>--%>
                <%--<button type="submit" class="btn btn-primary" name="saveAllToMembers" onclick="bCancel=false">--%>
                    <%--<i class="fa fa-plus"></i> <fmt:message key="button.saveAllToMembers"/>--%>
                <%--</button>--%>
                <%--<c:if test="${not empty memberImport.id}">--%>
                    <%--<button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">--%>
                        <%--<i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>--%>
                    <%--</button>--%>
                <%--</c:if>--%>

                <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
                    <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
                </button>
            </div>
            <div class="well">

                <div id="actions" class="btn-group pull-right">
                    <a href='<c:url value="/memberImportform?id=${memberImport.id}"/>' class="btn <c:if test="${empty viewStatus}" >btn-primary</c:if>">
                        <i class="icon-plus icon-white"></i> <fmt:message key="button.view.all"/></a>
                    <a href='<c:url value="/memberImportform?id=${memberImport.id}&viewStatus=VALID"/>' class="btn <c:if test="${viewStatus == 'VALID'}" >btn-primary</c:if>">
                        <i class="icon-plus icon-white"></i> <fmt:message key="button.view.valid"/></a>
                    <a href='<c:url value="/memberImportform?id=${memberImport.id}&viewStatus=INVALID"/>' class="btn <c:if test="${viewStatus == 'INVALID'}" >btn-primary</c:if>">
                        <i class="icon-plus icon-white"></i> <fmt:message key="button.view.invalid"/></a>
                    <a href='<c:url value="/memberImportform?id=${memberImport.id}&viewStatus=SUCCESS"/>' class="btn <c:if test="${viewStatus == 'SUCCESS'}" >btn-primary</c:if>">
                        <i class="icon-plus icon-white"></i> <fmt:message key="button.view.success"/></a>
                    <a href='<c:url value="/memberImportform?id=${memberImport.id}&viewStatus=FAILED"/>' class="btn <c:if test="${viewStatus == 'FAILED'}" >btn-primary</c:if>">
                        <i class="icon-plus icon-white"></i> <fmt:message key="button.view.failed"/></a>
                </div>
                <display:table name="memList" class="table table-condensed table-striped table-hover" requestURI="" id="memList" export="false" pagesize="50">

                    <%--<display:column titleKey="title.actions" style="width: 5%" media="html" >--%>
                        <%--<fmt:message key="view.or.edit" var="viewEdit"/>--%>
                        <%--<c:url var="editUrl" value="/memberImportEntryform?memberImportId=${memberImport.id}&importId=${memList.importId}"/>--%>
                        <%--<a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">--%>
                            <%--<i class="fa fa-eye"></i>--%>
                        <%--</a>--%>
                    <%--</display:column>--%>
                    <display:column property="name" sortable="true" titleKey="member.name"/>
                    <display:column property="icNumber" sortable="true" titleKey="member.icNumber"/>
                    <display:column property="phoneNo" sortable="true" titleKey="member.phoneNo"/>
                    <display:column property="importStatus" sortable="true" titleKey="memberImport.importStatus"/>
                    <display:column property="importRemark" sortable="true" titleKey="memberImport.importRemark"/>

                    <display:setProperty name="paging.banner.item_name"><fmt:message key="memberList.member"/></display:setProperty>
                    <display:setProperty name="paging.banner.items_name"><fmt:message key="memberList.members"/></display:setProperty>

                    <display:setProperty name="export.excel.filename"><fmt:message key="memberList.title"/>.xls</display:setProperty>
                    <display:setProperty name="export.csv.filename"><fmt:message key="memberList.title"/>.csv</display:setProperty>
                    <display:setProperty name="export.pdf.filename"><fmt:message key="memberList.title"/>.pdf</display:setProperty>
                </display:table>
            </div>

        </c:when>
        <c:otherwise>
            <spring:bind path="memberImport.name">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="memberImport.name" styleClass="control-label"/>
            <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
            <form:errors path="name" cssClass="help-block"/>
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
        </c:otherwise>
    </c:choose>
<br/>

</form:form>
</div>

<v:javascript formName="memberImport" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['memberImportForm']).focus();
    });
</script>
