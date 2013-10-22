<%--
  Created by IntelliJ IDEA.
  User: tonywinters
  Date: 10/12/13
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>List of Users</title>
    <link rel="stylesheet" type="text/css" href='<c:url value="../resources/reset.css" />' media="all" />
    <link rel="stylesheet" type="text/css" href='<c:url value="../resources/bootstrap/css/bootstrap.css" />' media="all" />
    <link rel="stylesheet" type="text/css" href='<c:url value="../resources/style.css" />' media="all" />

</head>
<body>

<div id="top-strip"></div>
<div id="top-bar">
    <div id="logo"><img src="../resources/images/logo-small.png" /></div>
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
<div class="container">
<h1>List of Users</h1>


    <table class="table table-hover" >
    <thead>
    <tr>
        <th width="10%">id</th><th width="15%">FirstName</th><th width="15%">LastName</th><th width="15%">Email</th><th width="10%">actions</th>
    </tr>


    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.contact.emailAddress}</td>

            <td>
                <a href="${pageContext.request.contextPath}/user/edit/${user.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/user/delete/${user.id}.html">Delete</a><br/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
</div>
</div>
</body>
</html>