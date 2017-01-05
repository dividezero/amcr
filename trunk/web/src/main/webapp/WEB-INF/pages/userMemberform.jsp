<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>

<link rel="stylesheet" type="text/css" href="/scripts/select2/select2.css">
<link rel="stylesheet" type="text/css" href="/scripts/select2/select2-bootstrap.css">
<script type="text/javascript" src="/scripts/select2/select2.js"></script>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>


<div class="col-sm-2">
    <h2><fmt:message key="userProfile.heading"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message"/></p>
        </c:otherwise>
    </c:choose>
</div>
<div class="col-sm-7">
    <spring:bind path="user.*">
        <c:if test="${not empty status.errorMessages}">
            <div class="alert alert-danger alert-dismissable">
                <a href="#" data-dismiss="alert" class="close">&times;</a>
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
        </c:if>
    </spring:bind>

    <form:form commandName="user" method="post" action="userform" id="userForm" autocomplete="off"
               cssClass="well" onsubmit="return validateUser(this)">

    <form:hidden path="member.id"/>
    <form:hidden path="member.version"/>
    <form:hidden path="member.createdBy"/>
    <form:hidden path="member.createdTime"/>
    <form:hidden path="member.modifiedBy"/>
    <form:hidden path="member.modifiedTime"/>
    <form:hidden path="member.description"/>
    <form:hidden path="member.coopId"/>

    <spring:bind path="member.designation">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.designation" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.designation" items="${designationList}"/>
    <form:errors path="member.designation" cssClass="help-block"/>
    </div>

    <spring:bind path="member.name">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.name" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.name" id="member.name"  maxlength="50"/>
    <form:errors path="member.name" cssClass="help-block"/>
    </div>
    <spring:bind path="member.icNumber">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.icNumber" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.icNumber" id="member.icNumber"  maxlength="50"/>
    <form:errors path="member.icNumber" cssClass="help-block"/>
    </div>

    <spring:bind path="member.dateOfBirth">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.dateOfBirth" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.dateOfBirth" id="member.dateOfBirth" size="11" title="date" datepicker="true"/>
    <form:errors path="member.dateOfBirth" cssClass="help-block"/>
    </div>

    <spring:bind path="member.email">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.email" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.email" id="member.email" />
    <form:errors path="member.email" cssClass="help-block"/>
    </div>

    <spring:bind path="member.email2">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.email2" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.email2" id="member.email2" />
    <form:errors path="member.email2" cssClass="help-block"/>
    </div>

    <spring:bind path="member.phoneNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.phoneNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.phoneNo" id="member.phoneNo"  maxlength="12"/>
    <form:errors path="member.phoneNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.phoneNoOffice">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.phoneNoOffice" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.phoneNoOffice" id="member.phoneNoOffice"  maxlength="12"/>
    <form:errors path="member.phoneNoOffice" cssClass="help-block"/>
    </div>

    <spring:bind path="member.phoneNoMobile">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.phoneNoMobile" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.phoneNoMobile" id="member.phoneNoMobile"  maxlength="12"/>
    <form:errors path="member.phoneNoMobile" cssClass="help-block"/>
    </div>

    <spring:bind path="member.amcrCode">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <c:if test="${not empty id}">
        <appfuse:label key="member.amcrCode" styleClass="control-label"/>
        <form:input cssClass="form-control" path="member.amcrCode" id="member.amcrCode"  readonly="true"/>
        <form:errors path="member.amcrCode" cssClass="help-block"/>
        </div>
    </c:if>

    <spring:bind path="member.membershipNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.membershipNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.membershipNo" id="member.membershipNo"  maxlength="50"/>
    <form:errors path="member.membershipNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.gender">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.gender" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.gender" items="${genderList}"/>
    <form:errors path="member.gender" cssClass="help-block"/>
    </div>
    <spring:bind path="member.maritalStatus">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.maritalStatus" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.maritalStatus" items="${maritalStatusList}"/>
    <form:errors path="member.maritalStatus" cssClass="help-block"/>
    </div>
    <spring:bind path="member.race">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.race" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.race" items="${raceList}"/>
    <form:errors path="member.race" cssClass="help-block"/>
    </div>

    <spring:bind path="member.preferredLanguage">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.preferredLanguage" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.preferredLanguage" items="${languageList}"/>
    <form:errors path="member.preferredLanguage" cssClass="help-block"/>
    </div>

    <spring:bind path="member.activeMembershipBinNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.activeMembershipBinNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.activeMembershipBinNo" id="member.activeMembershipBinNo"  maxlength="50"/>
    <form:errors path="member.activeMembershipBinNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.coinsBinNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.coinsBinNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.coinsBinNo" id="member.coinsBinNo"  maxlength="50"/>
    <form:errors path="member.coinsBinNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.coinsAccountNo">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.coinsAccountNo" styleClass="control-label"/>
    <form:input cssClass="form-control" path="member.coinsAccountNo" id="member.coinsAccountNo"  maxlength="50"/>
    <form:errors path="member.coinsAccountNo" cssClass="help-block"/>
    </div>

    <spring:bind path="member.coinsBank">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="member.coinsBank" styleClass="control-label"/>
    <form:select cssClass="form-control" path="member.coinsBankId" items="${banksList}"/>
    <form:errors path="member.coinsBank" cssClass="help-block"/>
    </div>

    <%--ADDRESS--%>
    <div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.address.address"/>
            <form:input cssClass="form-control" path="member.address.address1" id="member.address.address1"/>
            <form:input cssClass="form-control" path="member.address.address2" id="member.address.address2"/>
            <form:input cssClass="form-control" path="member.address.address3" id="member.address.address3"/>
        </div>
        <div class="row">
            <div class="col-sm-7 form-group">
                <appfuse:label styleClass="control-label" key="user.address.city"/>
                <form:input cssClass="form-control" path="member.address.city" id="member.address.city"/>
            </div>
            <div class="col-sm-2 form-group">
                <appfuse:label styleClass="control-label" key="user.address.province"/>
                <form:input cssClass="form-control" path="member.address.province" id="member.address.province"/>
            </div>
            <div class="col-sm-3 form-group">
                <appfuse:label styleClass="control-label" key="user.address.postalCode"/>
                <form:input cssClass="form-control" path="member.address.postalCode" id="member.address.postalCode"/>
            </div>
        </div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.address.country"/>
            <appfuse:country name="member.address.country" prompt="" default="${user.address.country}"/>
        </div>
    </div>

    <form:hidden path="member.typeFlag"/>
    <form:hidden path="member.enabled"/>






    <form:hidden path="id"/>
        <form:hidden path="version"/>
        <form:hidden path="createdBy"/>
        <form:hidden path="createdTime"/>
        <form:hidden path="modifiedBy"/>
        <form:hidden path="modifiedTime"/>

        <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>

        <spring:bind path="user.username">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="user.username"/>
            <form:input cssClass="form-control" path="username" id="username"/>
            <form:errors path="username" cssClass="help-block"/>
            <c:if test="${pageContext.request.remoteUser == user.username}">
                <span class="help-block">
                    <a href="<c:url value="/updatePassword" />"><fmt:message key='updatePassword.changePasswordLink'/></a>
                </span>
            </c:if>

        </div>

        <spring:bind path="user.passwordHint">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="user.passwordHint"/>
            <form:input cssClass="form-control" path="passwordHint" id="passwordHint"/>
            <form:errors path="passwordHint" cssClass="help-block"/>
        </div>
        <div class="row">
            <spring:bind path="user.firstName">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.firstName"/>
                <form:input cssClass="form-control" path="firstName" id="firstName" maxlength="50"/>
                <form:errors path="firstName" cssClass="help-block"/>
            </div>
            <spring:bind path="user.lastName">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.lastName"/>
                <form:input cssClass="form-control" path="lastName" id="lastName" maxlength="50"/>
                <form:errors path="lastName" cssClass="help-block"/>
            </div>
        </div>
        <div class="row">
            <spring:bind path="user.email">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.email"/>
                <form:input cssClass="form-control" path="email" id="email"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
            <div class="col-sm-6 form-group">
                <appfuse:label styleClass="control-label" key="user.phoneNumber"/>
                <form:input cssClass="form-control" path="phoneNumber" id="phoneNumber"/>
            </div>
        </div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.website"/>
            <form:input cssClass="form-control" path="website" id="website"/>
        </div>
        <%-- REMOVE ADDRESS --%>
        <div class="hidden">
            <legend class="accordion-heading">
                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.address.address"/></a>
            </legend>
            <div id="collapse-address" class="accordion-body collapse">
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.address"/>
                    <form:input cssClass="form-control" path="address.address" id="address.address"/>
                </div>
                <div class="row">
                    <div class="col-sm-7 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.city"/>
                        <form:input cssClass="form-control" path="address.city" id="address.city"/>
                    </div>
                    <div class="col-sm-2 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.province"/>
                        <form:input cssClass="form-control" path="address.province" id="address.province"/>
                    </div>
                    <div class="col-sm-3 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.postalCode"/>
                        <form:input cssClass="form-control" path="address.postalCode" id="address.postalCode"/>
                    </div>
                </div>
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.country"/>
                    <appfuse:country name="address.country" prompt="" default="${user.address.country}"/>
                </div>
            </div>
        </div>

<c:choose>
    <c:when test="${param.from == 'list' or param.method == 'Add'}">
        <div class="form-group">
            <label class="control-label"><fmt:message key="userProfile.accountSettings"/></label>
            <label class="checkbox-inline">
                <form:checkbox path="enabled" id="enabled"/>
                <fmt:message key="user.enabled"/>
            </label>

            <label class="checkbox-inline">
                <form:checkbox path="accountExpired" id="accountExpired"/>
                <fmt:message key="user.accountExpired"/>
            </label>

            <label class="checkbox-inline">
                <form:checkbox path="accountLocked" id="accountLocked"/>
                <fmt:message key="user.accountLocked"/>
            </label>

            <label class="checkbox-inline">
                <form:checkbox path="credentialsExpired" id="credentialsExpired"/>
                <fmt:message key="user.credentialsExpired"/>
            </label>
        </div>
        <div class="form-group">
            <label for="userRoles" class="control-label"><fmt:message key="userProfile.assignRoles"/></label>
            <select id="userRoles" name="userRoles" multiple="true" class="form-control">
                <c:forEach items="${availableRoles}" var="role">
                <option value="${role.value}" ${fn:contains(user.roles, role.label) ? 'selected' : ''}>${role.label}</option>
                </c:forEach>
            </select>
            <div id="icNumberDiv" name="icNumberDiv">
                <label for="icNumber" class="control-label"><fmt:message key="userProfile.member.ic"/></label>
                <%--<form:input cssClass="form-control" path="icNumber" id="icNumber"/>--%>
                <form:select cssClass="form-control" path="icNumber" items="${memberList}"/>
            </div>
            <div id="coopCodeDiv" name="coopCodeDiv">
                <label for="coopCode" class="control-label"><fmt:message key="coop.coopCode"/></label>
                <%--<form:input cssClass="form-control" path="coopCode" id="coopCode"/>--%>
                <form:select cssClass="form-control" path="coopCode" items="${coopList}"/>
            </div>
        </div>


    </c:when>
    <c:when test="${not empty user.username}">
        <div class="form-group">
            <label class="control-label"><fmt:message key="user.roles"/>:</label>
            <div class="readonly">
                <c:forEach var="role" items="${user.roleList}" varStatus="status">
                    <c:out value="${role.label}"/><c:if test="${!status.last}">,</c:if>
                    <input type="hidden" name="userRoles" value="<c:out value="${role.label}"/>"/>
                </c:forEach>
            </div>
            <form:hidden path="enabled"/>
            <form:hidden path="accountExpired"/>
            <form:hidden path="accountLocked"/>
            <form:hidden path="credentialsExpired"/>
        </div>
    </c:when>
</c:choose>
        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>

            <c:if test="${param.from == 'list' and param.method != 'Add'}">
              <button type="submit" class="btn btn-default" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                  <i class="icon-trash"></i> <fmt:message key="button.delete"/>
              </button>
            </c:if>

            <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>
</div>

<script type="text/javascript">

    $(document).ready(function() {
        $("#userRoles").change(function() {
            var coopSelected = false;
            var memberSelected = false;
            $( "#userRoles option:selected" ).each(function() {
                if($( this).val() === "ROLE_COOP")
                    coopSelected = true;
                if($( this).val() === "ROLE_MEMBER")
                    memberSelected = true;
            });
            if(coopSelected == false){
                $("#coopCodeDiv").hide();
            }else{
                $("#coopCodeDiv").show();
            }
            if(memberSelected == false){
                $("#icNumberDiv").hide();
            }else{
                $("#icNumberDiv").show();
            }
        });

        $("#userRoles").select2();
//        $("#coopCodeDiv").hide();
//        $("#icNumberDiv").hide();
        $("#userRoles").change();

        $("#icNumber").select2();
        $("#coopCode").select2();
    });
</script>


<c:set var="scripts" scope="request">
<script type="text/javascript">
// This is here so we can exclude the selectAll call when roles is hidden
function onFormSubmit(theForm) {
    return validateUser(theForm);
}
</script>
</c:set>

<v:javascript formName="user" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>

