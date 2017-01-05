<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberProductTransactionDeductionDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='memberProductTransactionDeductionDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="memberProductTransactionDeductionList.memberProductTransactionDeduction"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="memberProductTransactionDeductionDetail.heading"/></h2>
    <fmt:message key="memberProductTransactionDeductionDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="memberProductTransactionDeduction" method="post" action="memberProductTransactionDeductionform" cssClass="well"
           id="memberProductTransactionDeductionForm" onsubmit="return validateMemberProductTransactionDeduction(this)">
<form:hidden path="id"/>
    <spring:bind path="memberProductTransactionDeduction.createdBy">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProductTransactionDeduction.createdBy" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createdBy" id="createdBy"  maxlength="50"/>
        <form:errors path="createdBy" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProductTransactionDeduction.createdTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProductTransactionDeduction.createdTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createdTime" id="createdTime"  maxlength="255"/>
        <form:errors path="createdTime" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProductTransactionDeduction.modifiedBy">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProductTransactionDeduction.modifiedBy" styleClass="control-label"/>
        <form:input cssClass="form-control" path="modifiedBy" id="modifiedBy"  maxlength="50"/>
        <form:errors path="modifiedBy" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProductTransactionDeduction.modifiedTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProductTransactionDeduction.modifiedTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="modifiedTime" id="modifiedTime"  maxlength="255"/>
        <form:errors path="modifiedTime" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProductTransactionDeduction.batchId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProductTransactionDeduction.batchId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="batchId" id="batchId"  maxlength="255"/>
        <form:errors path="batchId" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProductTransactionDeduction.deductionAmount">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProductTransactionDeduction.deductionAmount" styleClass="control-label"/>
        <form:input cssClass="form-control" path="deductionAmount" id="deductionAmount"  maxlength="255"/>
        <form:errors path="deductionAmount" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProductTransactionDeduction.deductionDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProductTransactionDeduction.deductionDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="deductionDate" id="deductionDate"  maxlength="255"/>
        <form:errors path="deductionDate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="memberProduct" items="memberProductList" itemLabel="label" itemValue="value"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="productTransaction" items="productTransactionList" itemLabel="label" itemValue="value"/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty memberProductTransactionDeduction.id}">
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

<v:javascript formName="memberProductTransactionDeduction" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['memberProductTransactionDeductionForm']).focus();
    });
</script>
