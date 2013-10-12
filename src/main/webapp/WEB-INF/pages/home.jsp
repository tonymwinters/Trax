<%--
  Created by IntelliJ IDEA.
  User: tonywinters
  Date: 10/8/13
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <a href="${pageContext.request.contextPath}/owner/list.html">Owner list</a><br/>
</p>
</body>
</html>