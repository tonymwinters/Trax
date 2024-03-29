<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Edit Venue page</title>

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

<h1>Edit Venue page</h1>
<p>Here you can edit the existing Venue.</p>
<p>${message}</p>
<form:form method="POST" commandName="venue" action="${pageContext.request.contextPath}/venue/edit/${venue.id}.html">
    <table>
        <tbody>
        <tr>
            <td>Name</td>
            <td><form:input path="name" /></td>
        </tr>

        <tr>
            <td>Phone Number</td>
            <td><form:input path="contact.phoneNumber" /></td>
        </tr>

        <tr>
            <td>Email Address</td>
            <td><form:input path="contact.emailAddress" /></td>
        </tr>

        <tr>
            <td>Address</td>
            <td><form:input path="location.addressLine1" /></td>
        </tr>

        <tr>
            <td>City</td>
            <td><form:input path="location.city" /></td>
        </tr>

        <tr>
            <td>State</td>
            <td><form:input path="location.regionCode" /></td>
        </tr>

        <tr>
            <td>Zip</td>
            <td><form:input path="location.postalCode" /></td>
        </tr>

        <tr>
            <td>Country</td>
            <td><form:input path="location.countryCode" /></td>
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