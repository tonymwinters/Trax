<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/style.css" />' media="all" />
</head>
<body>
<h3>Swag! You're in!</h3>

<table>
    <tr>
        <td>Welcome To Home Page Of Trax - we like you.</td>
    </tr>
</table>

<p>
    ${message}<br/>
    <a href="${pageContext.request.contextPath}/owner/add.html">Add new Owner</a><br/>
    <a href="${pageContext.request.contextPath}/owner/list.html">Owner list</a><br/> <br/><br/>

        <a href="${pageContext.request.contextPath}/user/add.html">Add new User</a><br/>
        <a href="${pageContext.request.contextPath}/user/list.html">User list</a><br/>



        <c:url value="/logout" var="logoutUrl"/>
        <form:form name="f" action="${logoutUrl}" method="post">
    <div class="form-actions">
        <button type="submit" class="btn">Log Out</button>
    </div>
</form:form>
</p>
</body>
</html>