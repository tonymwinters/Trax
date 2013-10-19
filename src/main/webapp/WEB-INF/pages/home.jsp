<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href='<c:url value="resources/reset.css" />' media="all" />
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
                <%= (request.getUserPrincipal() != null) ? "<button class='default-button' type='submit'>Log Out</button>": "" %>

            </div>
        </form:form>
    </div>
</div>

<div class="body-wrapper">

        <p>
        ${message}<br/>
        <a href="${pageContext.request.contextPath}/owner/add.html">Add new Owner</a><br/>
        <a href="${pageContext.request.contextPath}/owner/list.html">Owner list</a><br/> <br/><br/>

        <a href="${pageContext.request.contextPath}/user/add.html">Add new User</a><br/>
        <a href="${pageContext.request.contextPath}/user/list.html">User list</a><br/><br/>

        <a href="${pageContext.request.contextPath}/user/add.html">Add new Venue</a><br/>
        <a href="${pageContext.request.contextPath}/user/list.html">Venue list</a><br/>

        </p>

</div>
</body>
</html>