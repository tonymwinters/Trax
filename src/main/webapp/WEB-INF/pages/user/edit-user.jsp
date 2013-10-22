<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Edit User page</title>

    <link rel="stylesheet" type="text/css" href='<c:url value="../../resources/reset.css" />' media="all" />
    <link rel="stylesheet" type="text/css" href='<c:url value="../../resources/bootstrap/css/bootstrap.css" />' media="all" />
    <link rel="stylesheet" type="text/css" href='<c:url value="../../resources/style.css" />' media="all" />
</head>
<body>

<div id="top-strip"></div>
<div id="top-bar">
    <div id="logo"><img src="../../resources/images/logo-small.png" /></div>
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

<h1>Edit User page</h1>
<p>Here you can edit the existing User.</p>
<p>${message}</p>
<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/user/edit/${user.id}.html"  cssClass="form-horizontal" role="form">
    <table>
        <tbody>
        <tr>
            <td class="col-lg-2 control-label">First Name:</td>
            <td><form:input path="firstName" cssClass="form-control"/></td>
        </tr>

        <tr>
            <td class="col-lg-2 control-label">Middle Name:</td>
            <td><form:input path="middleName" cssClass="form-control"/></td>
        </tr>

        <tr>
            <td class="col-lg-2 control-label">Last Name:</td>
            <td><form:input path="lastName" cssClass="form-control"/></td>
        </tr>


        <tr>
            <td class="col-lg-2 control-label">Phone Number</td>
            <td><form:input path="contact.phoneNumber" cssClass="form-control"/><form:hidden path="contact.name" value="tony"/></td>
        </tr>

        <tr>
            <td class="col-lg-2 control-label">Email Address</td>
            <td><form:input path="contact.emailAddress" cssClass="form-control"/></td>
        </tr>

        <tr>
            <td class="col-lg-2 control-label">Username:</td>
            <td><form:input path="username" cssClass="form-control"/></td>
        </tr>

        <tr>
            <td class="col-lg-2 control-label">Password:</td>
            <td><form:input path="password" cssClass="form-control"/></td>
        </tr>

        <tr>
            <td class="col-lg-2 control-label">Owner:</td>
            <td><select name="ownerId" class="form-control">
                <c:forEach items="${owners}" var="owner">
                    <option value="${owner.id}">${owner}</option>
                </c:forEach>
            </select></td>
        </tr>

        <tr>
            <td class="col-lg-2 control-label">Role:</td>
            <td><c:forEach items="${roles}" var="role">
                    <input type="checkbox" name="swag" class="checkbox-inline" value="${role.id}" <c:if test="${usersRoles.contains(role.id)}">checked</c:if> />${role.name}
                </c:forEach>
            </td>
        </tr>


        <tr>
            <td><input type="submit" value="Add" /></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</form:form>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
</div>
</div>
</body>
</html>