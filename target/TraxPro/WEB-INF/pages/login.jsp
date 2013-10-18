<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <title>Please Login</title>
        <link rel="stylesheet" href="resources/reset.css" media="all" />
        <link rel="stylesheet" href="resources/style.css" media="all" />
    </head>
    <body>

    <div id="top-strip"></div>


    <div id='logo-holder'><img src="resources/images/logo-large.png" /></div>
    <div id='login-container'>
        <div id="login-form-holder">
        <c:url value="/login" var="loginUrl"/>
        <form:form name="f" action="${loginUrl}" method="post">
                <c:if test="${error != null}">
                    <div class="alert alert-error">
                        Invalid username and password.
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        You have been logged out.
                    </div>
                </c:if>
                <label for="username">Username</label>
                <input type="text" id="username" name="username"/> <br/><br/>
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>
                <div class="form-actions">
                    <button class="default-button" type="submit">Log In</button>
                </div>
        </form:form>
        </div>


    </div>
    </body>
</html>