<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="employerImportDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='employerImportDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="employerImportList.employerImport"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="employerImportDetail.heading"/></h2>
    <fmt:message key="employerImportDetail.message"/>
</div>

<div class="col-sm-10">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="employerImport" method="post" enctype="multipart/form-data" action="employerImportform" cssClass="well"
           id="employerImportForm" onsubmit="return validateEmployerImport(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>


    <c:choose>
        <c:when test="${not empty employerImport.id}">
            <form:hidden path="name"/>
            <form:hidden path="documentId"/>
            <form:hidden path="employerJson"/>

            <appfuse:label key="employerImport.name" styleClass="control-label"/>
            <div class="form-control">${employerImport.name}</div>

            <appfuse:label key="employerImport.documentId" styleClass="control-label"/>
            <div class="form-control">${employerImport.documentId}</div>

            <br/><br/>
            <div>

                <display:table name="empList" class="table table-condensed table-striped table-hover" requestURI="" id="employerList" export="true" pagesize="50">
                    <%--<display:column property="id" sortable="true" href="employerform" media="html"--%>
                                    <%--paramId="id" paramProperty="id" titleKey="employer.id"/>--%>
                    <%--<display:column property="id" media="csv excel xml pdf" titleKey="employer.id"/>--%>

                    <display:column property="employerCode" sortable="true" titleKey="employer.employerCode"/>
                    <display:column property="name" sortable="true" titleKey="employer.name"/>
                    <display:column property="employerNo" sortable="true" titleKey="employer.employerNo"/>
                    <display:column property="importStatus" sortable="true" titleKey="employerimport.importStatus"/>
                    <display:column property="importRemark" sortable="true" titleKey="employerImport.importRemark"/>

                    <display:setProperty name="paging.banner.item_name"><fmt:message key="employerList.employer"/></display:setProperty>
                    <display:setProperty name="paging.banner.items_name"><fmt:message key="employerList.employers"/></display:setProperty>

                    <display:setProperty name="export.excel.filename"><fmt:message key="employerList.title"/>.xls</display:setProperty>
                    <display:setProperty name="export.csv.filename"><fmt:message key="employerList.title"/>.csv</display:setProperty>
                    <display:setProperty name="export.pdf.filename"><fmt:message key="employerList.title"/>.pdf</display:setProperty>
                </display:table>
            </div>

        </c:when>
        <c:otherwise>
            <spring:bind path="employerImport.name">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="employerImport.name" styleClass="control-label"/>
            <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
            <form:errors path="name" cssClass="help-block"/>
            </div>
            <spring:bind path="employerImport.file">
                <fieldset class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
            </spring:bind>
            <appfuse:label key="employerImport.file" styleClass="control-label" />
            <div class="controls">
                <input type="file" name="file" id="file" />
                <form:errors path="file" cssClass="help-inline" />
            </div>
            </fieldset>

        </c:otherwise>
    </c:choose>
    <br/>
    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty employerImport.id}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="employerImport" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['employerImportForm']).focus();
    });
</script>
