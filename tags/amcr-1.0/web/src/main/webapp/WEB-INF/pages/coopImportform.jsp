<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="coopImportDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='coopImportDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="coopImportList.coopImport"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="coopImportDetail.heading"/></h2>
    <fmt:message key="coopImportDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="coopImport" method="post" action="coopImportform" cssClass="well"
           id="coopImportForm" onsubmit="return validateCoopImport(this)">
<form:hidden path="id"/>
    <form:hidden path="createdBy"/>
    <form:hidden path="createdTime"/>
    <form:hidden path="modifiedBy"/>
    <form:hidden path="modifiedTime"/>

    <c:choose>
        <c:when test="${not empty coopImport.id}">
            <form:hidden path="name"/>
            <form:hidden path="documentId"/>
            <form:hidden path="coopJson"/>

            <appfuse:label key="coopImport.name" styleClass="control-label"/>
            <div class="form-control">${coopImport.name}</div>

            <appfuse:label key="coopImport.documentId" styleClass="control-label"/>
            <div class="form-control">${coopImport.documentId}</div>

            <br/><br/>
            <div>

                <display:table name="coopList" class="table table-condensed table-striped table-hover" requestURI="" id="memList" export="false" pagesize="50">

                    <display:column titleKey="title.actions" style="width: 5%" media="html" >
                        <fmt:message key="view.or.edit" var="viewEdit"/>
                        <c:url var="editUrl" value="/coopImportEntryform/${coopImport.id}/${memList.importId}"/>
                        <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
                            <i class="fa fa-pencil-square-o"></i>
                        </a>
                    </display:column>
                    <display:column property="name" sortable="true" titleKey="coop.name"/>
                    <display:column property="amcrCode" sortable="true" titleKey="coop.amcrCode"/>
                    <display:column property="coopCode" sortable="true" titleKey="coop.coopCode"/>
                    <display:column sortProperty="incorporatedDate" sortable="true" titleKey="coop.incorporatedDate">
                        <display:column property="phoneNo" sortable="true" titleKey="coop.phoneNo"/>
                        <fmt:formatDate value="${coopList.incorporatedDate}" pattern="${datePattern}"/>
                    </display:column>
                    <display:column property="importStatus" sortable="true" titleKey="coopImport.importStatus"/>
                    <display:column property="importRemark" sortable="true" titleKey="coopImport.importRemark"/>

                    <display:setProperty name="paging.banner.item_name"><fmt:message key="coopList.coop"/></display:setProperty>
                    <display:setProperty name="paging.banner.items_name"><fmt:message key="coopList.coops"/></display:setProperty>

                    <display:setProperty name="export.excel.filename"><fmt:message key="coopList.title"/>.xls</display:setProperty>
                    <display:setProperty name="export.csv.filename"><fmt:message key="coopList.title"/>.csv</display:setProperty>
                    <display:setProperty name="export.pdf.filename"><fmt:message key="coopList.title"/>.pdf</display:setProperty>
                </display:table>
            </div>

        </c:when>
        <c:otherwise>
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
        </c:otherwise>
    </c:choose>
    <br/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty coopImport.id}">
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

<v:javascript formName="coopImport" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['coopImportForm']).focus();
    });
</script>
