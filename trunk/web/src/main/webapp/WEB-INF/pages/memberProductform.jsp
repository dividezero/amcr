<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="memberProductDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='memberProductDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="memberProductList.memberProduct"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="memberProductDetail.heading"/></h2>
    <fmt:message key="memberProductDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="memberProduct" method="post" action="memberProductform" cssClass="well"
           id="memberProductForm" onsubmit="return validateMemberProduct(this)">
<form:hidden path="id"/>
    <spring:bind path="memberProduct.createdBy">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProduct.createdBy" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createdBy" id="createdBy"  maxlength="50"/>
        <form:errors path="createdBy" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProduct.createdTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProduct.createdTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createdTime" id="createdTime"  maxlength="255"/>
        <form:errors path="createdTime" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProduct.modifiedBy">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProduct.modifiedBy" styleClass="control-label"/>
        <form:input cssClass="form-control" path="modifiedBy" id="modifiedBy"  maxlength="50"/>
        <form:errors path="modifiedBy" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProduct.modifiedTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProduct.modifiedTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="modifiedTime" id="modifiedTime"  maxlength="255"/>
        <form:errors path="modifiedTime" cssClass="help-block"/>
    </div>
    <spring:bind path="memberProduct.commencementDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProduct.commencementDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="commencementDate" id="commencementDate"  maxlength="255"/>
        <form:errors path="commencementDate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="member" items="memberList" itemLabel="label" itemValue="value"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="product" items="productList" itemLabel="label" itemValue="value"/>
    <spring:bind path="memberProduct.registerDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="memberProduct.registerDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="registerDate" id="registerDate"  maxlength="255"/>
        <form:errors path="registerDate" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty memberProduct.id}">
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

<v:javascript formName="memberProduct" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['memberProductForm']).focus();
    });
</script>
