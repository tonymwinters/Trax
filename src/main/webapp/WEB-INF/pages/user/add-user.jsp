<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Add User page</title>
</head>
<body>
<h1>Add A New User</h1>
<p>Here you can add a new User.</p>
<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/user/add.html">
    <table>
        <tbody>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName" /></td>
        </tr>

        <tr>
            <td>Middle Name:</td>
            <td><form:input path="middleName" /></td>
        </tr>

        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName" /></td>
        </tr>


        <tr>
            <td>Phone Number</td>
            <td><form:input path="contact.phoneNumber" /><form:hidden path="contact.name" value="tony"/></td>
        </tr>

        <tr>
            <td>Email Address</td>
            <td><form:input path="contact.emailAddress" /></td>
        </tr>

        <tr>
            <td>Username:</td>
            <td><form:input path="username" /></td>
        </tr>

        <tr>
            <td>Password:</td>
            <td><form:input path="password" /></td>
        </tr>

        <tr>
            <td>Owner:</td>
            <td><select name="ownerId">
                <c:forEach items="${owners}" var="owner">
                    <option value="${owner.id}">${owner}</option>
                </c:forEach>
            </select></td>
        </tr>

        <tr>
            <td>Role:</td>
            <td><select name="roleId">
                <c:forEach items="${roles}" var="role">
                    <option value="${role.id}">${role}</option>
                </c:forEach>
            </select></td>
        </tr>


        <tr>
            <td><input type="submit" value="Add" /></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</form:form>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
</body>
</html>