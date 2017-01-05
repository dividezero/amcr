<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="eventDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='eventDetail.heading'/>"/>
    <script src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script type="text/javascript" src="<c:url value='/scripts/gmaps/jquery-gmaps-latlon-picker.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles/gmaps/jquery-gmaps-latlon-picker.css'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="eventList.event"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="eventDetail.heading"/></h2>
    <fmt:message key="eventDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="event" method="post" action="eventform"
           id="eventForm" onsubmit="return validateEvent(this)">
<form:hidden path="id"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdTime"/>
<form:hidden path="modifiedBy"/>
<form:hidden path="modifiedTime"/>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist" name="navtabs" id="navtabs">
        <li role="presentation" class="active"><a href="#eventDetails" role="tab" data-toggle="tab"><i
                class="fa fa-bullhorn"></i> &nbsp;<fmt:message key="event.eventDetails"/></a></li>
        <li role="presentation" ><a href="#memberDetails" role="tab" data-toggle="tab"><i
                class="fa fa-users"></i> &nbsp;<fmt:message key="event.memberDetails"/></a></li>
    </ul>

    <div class="well">
        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="eventDetails">
                <spring:bind path="event.eventCode">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind>
                    <appfuse:label key="event.eventCode" styleClass="control-label"/>
                    <form:input cssClass="form-control" path="eventCode" id="eventCode"  maxlength="50"/>
                    <form:errors path="eventCode" cssClass="help-block"/>
                </div>
                <spring:bind path="event.name">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind>
                    <appfuse:label key="event.name" styleClass="control-label"/>
                    <form:input cssClass="form-control" path="name" id="name"  maxlength="200"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
                <spring:bind path="event.description">
                <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind>
                    <appfuse:label key="event.description" styleClass="control-label"/>
                    <form:textarea cssClass="form-control" path="description" id="description" rows="5" cols="30" />
                    <form:errors path="description" cssClass="help-block"/>
                </div>

                <div class="row">
                    <div class="col-sm-6">
                        <spring:bind path="event.startTime">
                        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            </spring:bind>
                            <appfuse:label key="event.startTime" styleClass="control-label"/>
                            <form:input cssClass="form-control" path="startTime" id="startTime"  maxlength="255"/>
                            <form:errors path="startTime" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <spring:bind path="event.endTime">
                        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            </spring:bind>
                            <appfuse:label key="event.endTime" styleClass="control-label"/>
                            <form:input cssClass="form-control" path="endTime" id="endTime"  maxlength="255"/>
                            <form:errors path="endTime" cssClass="help-block"/>
                        </div>
                    </div>
                </div>

                <appfuse:label key="event.location" styleClass="control-label"/>
                <fieldset class="gllpLatlonPicker">
                    <div class="row">
                        <div class="col-sm-10">
                            <input type="text" class="gllpSearchField form-control">
                        </div>
                        <div class="col-sm-2">
                            <input type="button" class="gllpSearchButton btn btn-default" value="search">

                        </div>
                    </div>
                    <br/>
                    <div class="gllpMap">Google Maps</div>

                        <%--<form:hidden path="geoLat" class="gllpLatitude" value="20"/>--%>
                        <%--<form:hidden path="geoLong" class="gllpLatitude" value="20"/>--%>
                        <%--<input name="geoLat" type="hidden" class="gllpLatitude" value="20"/>--%>
                        <%--<input name="geoLong" type="hidden" class="gllpLongitude" value="20"/>--%>
                    <form:input cssClass="form-control gllpLatitude hidden" path="geoLat" id="geoLat"  maxlength="255"/>
                    <form:input cssClass="form-control gllpLongitude hidden" path="geoLong" id="geoLong"  maxlength="255"/>
                    <input type="hidden" class="gllpZoom" value="3"/>
                </fieldset>

                <br/>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                        <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
                    </button>
                    <c:if test="${currentUser.adminUser}">
                        <c:if test="${not empty event.id}">
                            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                            </button>
                        </c:if>
                    </c:if>

                    <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
                        <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
                    </button>
                </div>

            </div>
            <div role="tabpanel" class="tab-pane" id="memberDetails">

                <c:if test="${not empty event.id}">
                    <spring:bind path="event.drawResult">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label key="event.drawResult" styleClass="control-label"/>
                        <form:textarea cssClass="form-control" path="drawResult" id="drawResult" rows="5" cols="30" />
                        <form:errors path="drawResult" cssClass="help-block"/>
                    </div>

                    <div id="actions" class="btn-group">
                        <a href='<c:url value="/eventMemberform?eventId=${event.id}"/>' class="btn btn-primary">
                            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
                        <button type="submit" class="btn btn-default" name="randomdraw" onclick="bCancel=true">
                            <i class="icon-ok"></i> <fmt:message key="button.randomdraw"/>
                        </button>
                    </div>

                    <display:table name="eventMemberList" class="table table-condensed table-striped table-hover" requestURI="" id="eventMemberList" export="false" pagesize="25">

                        <display:column titleKey="title.actions" style="width: 5%" media="html" >
                            <fmt:message key="view.or.edit" var="viewEdit"/>
                            <c:url var="editUrl" value="/eventMemberform?id=${eventMemberList.id}&eventId=${event.id}"/>
                            <a class="btn btn-primary btn-mini" href="${editUrl}" title="${viewEdit}">
                                <i class="fa fa-pencil-square-o"></i>
                            </a>
                        </display:column>
                        <display:column property="icNumber" sortable="true" titleKey="eventMember.icNumber"/>
                        <display:column property="name" sortable="true" titleKey="eventMember.name"/>
                        <display:column property="email" sortable="true" titleKey="eventMember.email"/>
                        <display:column property="phoneNo" sortable="true" titleKey="eventMember.phoneNo"/>

                        <display:setProperty name="paging.banner.item_name"><fmt:message key="eventMemberList.eventMember"/></display:setProperty>
                        <display:setProperty name="paging.banner.items_name"><fmt:message key="eventMemberList.eventMembers"/></display:setProperty>

                        <display:setProperty name="export.excel.filename"><fmt:message key="eventMemberList.title"/>.xls</display:setProperty>
                        <display:setProperty name="export.csv.filename"><fmt:message key="eventMemberList.title"/>.csv</display:setProperty>
                        <display:setProperty name="export.pdf.filename"><fmt:message key="eventMemberList.title"/>.pdf</display:setProperty>
                    </display:table>
                </c:if>

            </div>
        </div>
    </div>


</form:form>
</div>

<v:javascript formName="event" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
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
        $("input[type='text']:visible:enabled:first", document.forms['eventForm']).focus();

        <c:if test="${param.tab == 'member'}">
            $('#navtabs a[href="#memberDetails"]').tab('show')
        </c:if>

        $('#startTime').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
        $('#endTime').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
