<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="document.store.title"/></title>
    <meta name="menu" content="ViewDocumentStoreMenu"/>
</head>

<c:if test="${not empty search_error}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${search_error}"/>
    </div>
</c:if>

<div class="span12">
    <h2><fmt:message key="document.store.title"/></h2>

    <form method="get" action="${ctx}/documentStore" id="searchForm" >

    <div id="search">
        <div class="row">
            <div class="col-sm-4">
                <appfuse:label styleClass="control-label" key="document.moduleName"/>
            </div>
            <div class="col-sm-8">

                <fmt:message var="selectModuleName" key="default.select"/>
                <select class="form-control" id="moduleName" name="moduleName" onChange="$('#searchForm').submit()">
                    <option value="">${selectModuleName}</option>
                    <c:forEach items="${documentModules}" var="documentModuleName">
                        <option value="${documentModuleName}"
                                <c:if test="${documentModuleName == param.moduleName}">
                                    selected="selected"
                                </c:if>
                                >
                                ${documentModuleName}
                        </option>
                    </c:forEach>
                </select>
            </div>

        </div>

        <div class="row hidden">
            <div class="col-sm-6">
                    <input class="form-control input-sm" var="fromDate" type="text" name="fromDate" readonly="true"
                           id="fromDateId" value="${param.fromDate}" data-date-format="${datePattern}"
                           placeholder="From">

            </div>
            <div class="col-sm-6">
                <input class="form-control input-sm" var="toDate" type="text" name="toDate" id="toDateId"
                       readonly="true" value="${param.toDate}" data-date-format="${datePattern}" placeholder="To">
            </div>
        </div>
        <div class="row hidden">
            <span class="col-sm-12">
                <input type="text" size="20" name="q" id="query" value="${param.q}"
                       placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
                <button id="button.search" class="btn" type="submit">
                    <i class="icon-search"></i> <fmt:message key="button.search"/>
                </button>
                <button type="button" class="btn" onclick="resetDates();">
                    <fmt:message key="button.reset"/>
                </button>
            </span>
            <%--<span class="col-sm-2">--%>

            <%--</span>--%>

        </div>

    </div>

    </form>
    <fmt:message key="document.store.message"/>

    <br/><br/>


    <div class="row-fluid" id="actions">
        <div class="span12">
            <hr/>
        </div>
        <div class="span12">
            <display:table name="documentList" cellspacing="0" cellpadding="0" requestURI="" defaultsort="1"
                           id="documents" class="table table-condensed table-striped table-hover" export="false"
                           pagesize="25"
                           keepStatus="true" clearStatus="${param.clrStt != null && param.clrStt == true}">
                <display:column titleKey="title.actions" media="html">
                    <fmt:message key="download" var="viewEdit"/>
                    <c:url var="editUrl" value="/documents/get/${documents.id}"/>
                    <a class="btn icon-download-alt" href="${editUrl}" title="${viewEdit}"></a>
                </display:column>
                <display:column sortable="true" titleKey="document.filename">
                    ${documents.filename}
                </display:column>
                <display:column property="moduleName" titleKey="document.moduleName"/>
                <display:column property="modulePrimKey" titleKey="document.modulePrimKey"/>
                <display:column property="createdBy" sortable="true" titleKey="document.createdBy"/>
                <display:column sortProperty="createdTime" sortable="true" titleKey="document.createdTime">
                    <fmt:formatDate value="${documents.createdTime}" pattern="${datePattern}"/>
                </display:column>

                <display:setProperty name="paging.banner.item_name"><fmt:message
                        key="documentList.document"/></display:setProperty>
                <display:setProperty name="paging.banner.items_name"><fmt:message
                        key="documentList.documents"/></display:setProperty>

                <display:setProperty name="export.excel.filename"><fmt:message
                        key="document.store.title"/>.xls</display:setProperty>
                <display:setProperty name="export.csv.filename"><fmt:message
                        key="document.store.title"/>.csv</display:setProperty>
                <display:setProperty name="export.pdf.filename"><fmt:message
                        key="document.store.title"/>.pdf</display:setProperty>
            </display:table>

        </div>
    </div>
</div>

<link rel="stylesheet" type="text/css" media="all"
      href="<c:url value='/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css'/>"/>
<script type="text/javascript"
        src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
    <script type="text/javascript"
            src="<c:url value='/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>


<script type="text/javascript">

    $(document).ready(function () {

        $('#fromDateId').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
        $('#toDateId').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});

        $('.input-append.date').datepicker({
            autoclose: true, format: "<fmt:message key='calendar.format'/>",
            startView: "decade", minViewMode: "days", language: '${pageContext.request.locale.language}'
        }).on('changeDate', function (ev) {
            $(this).parent().submit();
        });
    });

    function toggle(content) {
        var $wcontent = content.parent().parent().next('.box-content');
        if ($wcontent.is(':visible')) {
            content.children('i').removeClass('icon-chevron-up');
            content.children('i').addClass('icon-chevron-down');
        } else {
            content.children('i').removeClass('icon-chevron-down');
            content.children('i').addClass('icon-chevron-up');
        }
        $wcontent.toggle(500);

    }

    $('.boxminimize').click(function () {

        toggle($(this));
    });

    function resetDates() {
        $("#fromDateId").val('');
        $("#toDateId").val('');
        $("#query").val('');
    }

</script>