<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="productTransactionDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='productTransactionDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="productTransactionList.productTransaction"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="productTransactionDetail.heading"/></h2>
    <fmt:message key="productTransactionDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="productTransaction" method="post" action="productTransactionform" cssClass="well"
           id="productTransactionForm" onsubmit="return validateProductTransaction(this)">
<form:hidden path="id"/>
<form:hidden path="product.id"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdTime"/>
<form:hidden path="modifiedBy"/>
<form:hidden path="modifiedTime"/>
<form:hidden path="transNo"/>

    <h2>${productTransaction.product.name}</h2>
    <h4>Transaction ${productTransaction.transNo}</h4>
    <br/>

    <div class="row">
        <div class="col-sm-6">
            <spring:bind path="product.transactionType">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="product.transactionType" styleClass="control-label"/>
                <form:select cssClass="form-control" path="transactionType" items="${transactionTypeList}"/>
                <form:errors path="transactionType" cssClass="help-block"/>
            </div>
        </div>
        <div class="col-sm-6">
            <spring:bind path="productTransaction.amount">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="productTransaction.amount" styleClass="control-label"/>
                <form:input cssClass="form-control" path="amount" id="amount"  maxlength="255"/>
                <form:errors path="amount" cssClass="help-block"/>
            </div>
        </div>
    </div>

    <br/>
    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty productTransaction.id}">
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

<v:javascript formName="productTransaction" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['productTransactionForm']).focus();
    });
</script>
