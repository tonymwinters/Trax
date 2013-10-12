<%--
  Created by IntelliJ IDEA.
  User: tonywinters
  Date: 10/12/13
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>List of Owners</title>
</head>
<body>
<h1>List of Owners</h1>
<p>Owners of Bitches and Hoes</p>
<table border="1px" cellpadding="0" cellspacing="0" >
    <thead>
    <tr>
        <th width="10%">id</th><th width="15%">name</th><th width="10%">actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="owner" items="${owners}">
        <tr>
            <td>${owner.id}</td>
            <td>${owner.name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/owner/edit/${owner.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/owner/delete/${owner.id}.html">Delete</a><br/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>