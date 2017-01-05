<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="productDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='productDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="productList.product"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="productDetail.heading"/></h2>
    <fmt:message key="productDetail.message"/>
</div>

<div class="col-sm-8">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="product" method="post" action="productform"
           id="productForm" onsubmit="return validateProduct(this)">
<form:hidden path="id"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdTime"/>
<form:hidden path="modifiedBy"/>
<form:hidden path="modifiedTime"/>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist" id="productTabs">
        <li role="presentation" class="active"><a href="#productDetails" role="tab" data-toggle="tab"><i
                class="fa fa-rocket"></i> &nbsp;<fmt:message key="product.productDetails"/></a></li>
        <c:if test="${not empty product.id}">
            <li role="presentation"><a href="#transactionDetails" role="tab" data-toggle="tab"><i
                    class="fa fa-money"></i> &nbsp;<fmt:message key="product.transactionDetails"/></a></li>
        </c:if>

    </ul>

    <div class="well">
    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="productDetails">
            <div class="row">
                <div class="col-sm-6">
                    <spring:bind path="product.code">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="product.code" styleClass="control-label"/>
                        <form:input cssClass="form-control" path="code" id="code"  maxlength="255"/>
                        <form:errors path="code" cssClass="help-block"/>
                    </div>
                </div>
                <div class="col-sm-6">
                    <spring:bind path="product.bankId">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="product.bankId" styleClass="control-label"/>
                        <form:select cssClass="form-control" path="bankId" items="${banksList}"/>
                        <form:errors path="bankId" cssClass="help-block"/>
                    </div>
                </div>

            </div>


            <spring:bind path="product.name">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="product.name" styleClass="control-label"/>
                <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
            <spring:bind path="product.description">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="product.description" styleClass="control-label"/>
                <form:input cssClass="form-control" path="description" id="description"  maxlength="255"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>

            <div class="row">
                <div class="col-sm-6">
                    <spring:bind path="product.startDate">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="product.startDate" styleClass="control-label"/>
                        <form:input cssClass="form-control" path="startDate" id="startDate"  maxlength="255"/>
                        <form:errors path="startDate" cssClass="help-block"/>
                    </div>
                    <span class="help-block"><fmt:message key="help.datepicker"/></span>
                </div>
                <div class="col-sm-6">
                    <spring:bind path="product.endDate">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="product.endDate" styleClass="control-label"/>
                        <form:input cssClass="form-control" path="endDate" id="endDate"  maxlength="255"/>
                        <form:errors path="endDate" cssClass="help-block"/>
                    </div>
                    <span class="help-block"><fmt:message key="help.datepicker"/></span>
                </div>
            </div>

            <br/>
            <spring:bind path="product.transactionType">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="product.transactionType" styleClass="control-label"/>
                <form:select cssClass="form-control" path="transactionType" items="${transactionTypeList}"/>
                <form:errors path="transactionType" cssClass="help-block"/>
            </div>

            <div class="row">
                <div class="col-sm-6">
                    <spring:bind path="product.noOfTransactions">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="product.noOfTransactions" styleClass="control-label"/>
                        <form:input cssClass="form-control" path="noOfTransactions" id="noOfTransactions"  maxlength="255"/>
                        <form:errors path="noOfTransactions" cssClass="help-block"/>
                    </div>
                </div>
                <div class="col-sm-6">
                    <spring:bind path="product.totalAmount">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="product.totalAmount" styleClass="control-label"/>
                        <form:input cssClass="form-control" path="totalAmount" id="totalAmount"  maxlength="255"/>
                        <form:errors path="totalAmount" cssClass="help-block"/>
                    </div>
                        <span class="help-block"><fmt:message key="help.product.totalAmount"/></span>
                </div>

            </div>

            <div class="row">
                <div class="col-sm-6">
                    <spring:bind path="product.period">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="product.period" styleClass="control-label"/>
                        <form:select cssClass="form-control" id="period" path="period" items="${productPeriodList}"/>
                        <form:errors path="period" cssClass="help-block"/>
                    </div>
                </div>
                <div id="monthlyDiv" class="col-sm-6" hidden="hidden">
                    <spring:bind path="product.periodMonthlyDays">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="product.periodMonthlyDays" styleClass="control-label"/>
                        <form:input cssClass="form-control" path="periodMonthlyDays" id="periodMonthlyDays"  maxlength="255"/>
                        <form:errors path="periodMonthlyDays" cssClass="help-block"/>
                    </div>
                    <span class="help-block"><fmt:message key="help.product.periodMonthlyDays"/></span>
                </div>
                <div id="daysDiv" class="col-sm-6" hidden="hidden">
                    <spring:bind path="product.periodDaysCount">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="product.periodDaysCount" styleClass="control-label"/>
                        <form:input cssClass="form-control" path="periodDaysCount" id="periodDaysCount"  maxlength="255"/>
                        <form:errors path="periodDaysCount" cssClass="help-block"/>
                    </div>
                    <span class="help-block"><fmt:message key="help.product.periodDaysCount"/></span>
                </div>

            </div>
        </div>
        <c:if test="${not empty product.id}">
            <div role="tabpanel" class="tab-pane" id="transactionDetails">
                <h2><fmt:message key="productTransactionList.heading"/></h2>
                <div id="actions" class="btn-group pull-right">
                    <a href='<c:url value="/productTransactionform?productId=${product.id}"/>' class="btn btn-primary">
                        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
                </div>

                <display:table name="product.productTransactions" class="table table-condensed table-striped table-hover" requestURI="" id="productTransactionList" export="false" pagesize="25">
                    <display:column property="id" sortable="true" href="productTransactionform" media="html"
                                    paramId="id" paramProperty="id" titleKey="productTransaction.id"/>
                    <display:column property="id" media="csv excel xml pdf" titleKey="productTransaction.id"/>
                    <display:column property="createdBy" sortable="true" titleKey="productTransaction.createdBy"/>
                    <display:column property="createdTime" sortable="true" titleKey="productTransaction.createdTime"/>
                    <display:column property="modifiedBy" sortable="true" titleKey="productTransaction.modifiedBy"/>
                    <display:column property="modifiedTime" sortable="true" titleKey="productTransaction.modifiedTime"/>
                    <display:column property="amount" sortable="true" titleKey="productTransaction.amount"/>
                    <display:column property="transNo" sortable="true" titleKey="productTransaction.transNo"/>
                    <display:column property="transactionType" sortable="true" titleKey="productTransaction.transactionType"/>

                    <display:setProperty name="paging.banner.item_name"><fmt:message key="productTransactionList.productTransaction"/></display:setProperty>
                    <display:setProperty name="paging.banner.items_name"><fmt:message key="productTransactionList.productTransactions"/></display:setProperty>

                    <display:setProperty name="export.excel.filename"><fmt:message key="productTransactionList.title"/>.xls</display:setProperty>
                    <display:setProperty name="export.csv.filename"><fmt:message key="productTransactionList.title"/>.csv</display:setProperty>
                    <display:setProperty name="export.pdf.filename"><fmt:message key="productTransactionList.title"/>.pdf</display:setProperty>
                </display:table>

            </div>
        </c:if>
    </div>


    <br/>
    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty product.id}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>

    </div>
</form:form>
</div>

<v:javascript formName="product" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all"
      href="<c:url value='/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css'/>"/>
<script type="text/javascript"
        src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
    <script type="text/javascript"
            src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>

<script type="text/javascript">
    $(document).ready(function() {
        <c:if test="${not empty param.tab}">
            $('#productTabs a[href="#${param.tab}"]').tab('show');
        </c:if>



        $("#period").change(function() {
            if($("#period").val() === "DAYS"){
                $('#monthlyDiv').hide();
                $('#monthlyDiv').val("");
                $('#daysDiv').show();
            }else if($("#period").val() === "MONTHLY"){
                $('#daysDiv').hide();
                $('#daysDiv').val("");
                $('#monthlyDiv').show();
            }
        });

        $("#period").change();

        $('#startDate').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
        $('#endDate').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});

        $("input[type='text']:visible:enabled:first", document.forms['productForm']).focus();
    });
</script>
