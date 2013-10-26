<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href='<c:url value="resources/reset.css" />' media="all" />
    <link rel="stylesheet" type="text/css" href='<c:url value="resources/bootstrap/css/bootstrap.css" />' media="all" />
    <link rel="stylesheet" type="text/css" href='<c:url value="resources/style.css" />' media="all" />
</head>

<div id="top-strip"></div>
<div id="top-bar">
    <div id="logo"><img src="resources/images/logo-small.png" /></div>
    <div id="user-actions">
        <c:url value="/logout" var="logoutUrl"/>
        <form:form name="f" action="${logoutUrl}" method="post">
            <div class="form-actions">
                <h3 id="logged-in-user"><%= (request.getUserPrincipal() != null) ? request.getUserPrincipal().getName() : "" %></h3>
                <a href="#" class="default-button">Settings</a>
                <a href="#" class="default-button">Account</a>
                <a href="${logoutUrl}" class="default-button">Logout</a>



            </div>
        </form:form>
    </div>
</div>

<div class="body-wrapper">
    <div class="container">

        ${message}<br/>
        <a href="${pageContext.request.contextPath}/owner/add.html">Add new Owner</a><br/>
        <a href="${pageContext.request.contextPath}/owner/list.html">Owner list</a><br/> <br/><br/>

        <a href="${pageContext.request.contextPath}/user/add.html">Add new User</a><br/>
        <a href="${pageContext.request.contextPath}/user/list.html">User list</a><br/><br/>

        <a href="${pageContext.request.contextPath}/venue/add.html">Add new Venue</a><br/>
        <a href="${pageContext.request.contextPath}/venue/list.html">Venue list</a><br/>


    </div>

</div>
</body>
</html>