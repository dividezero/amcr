<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="collapse navbar-collapse" id="navbar">
<ul class="nav navbar-nav">
    <c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    <menu:displayMenu name="Home"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="MemberFormMenu"/>
    <menu:displayMenu name="CoopFormMenu"/>
    <menu:displayMenu name="CoopMemberMenu"/>
    <menu:displayMenu name="CoopMenu"/>
    <menu:displayMenu name="MemberMenu"/>

    <menu:displayMenu name="EventMenu"/>
    <%--<menu:displayMenu name="EventMemberMenu"/>--%>

<%--<menu:displayMenu name="SurveysMenu"/>--%>
    <%--<menu:displayMenu name="ProductMenu"/>--%>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>

</ul>
</div>
</menu:useMenuDisplayer>
