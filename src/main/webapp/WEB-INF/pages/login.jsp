<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <title>Please Login</title>
        <link rel="stylesheet" href="resources/reset.css" media="all" />
        <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.css" media="all" />
        <link rel="stylesheet" href="resources/style.css" media="all" />
        <link rel="stylesheet" href="resources/bootstrap/css/signin.css" media="all" />

        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>

    <div id="top-strip"></div>


    <div id='logo-holder'><img src="resources/images/logo-large.png" /></div>


    <div class="container">

        <c:url value="/login" var="loginUrl"/>
        <form:form name="f" action="${loginUrl}" method="post" cssClass="form-signin">
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="text" name="username" id="username" class="form-control" placeholder="Email address" autofocus>
            <input type="password" name="password" id="password" class="form-control" placeholder="Password">
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
            </label>
            <button class="btn btn-lg btn-primary" type="submit">Sign in</button>
        </form:form>

    </div>



    <%--<div id='login-container'>--%>
        <%--<div id="login-form-holder">--%>

        <%----%>
        <%--</div>--%>


    <%--</div>--%>
    </body>
</html>


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