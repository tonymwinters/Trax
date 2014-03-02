<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <title>Please Login</title>
        <link rel="stylesheet" href="<c:url value='/resources/reset.css' />" media="all" />
        <link rel="stylesheet" href="<c:url value='/resources/lib/bootstrap/css/bootstrap.css' />" media="all" />
        <link rel="stylesheet" href="<c:url value='/resources/style.css' />" media="all" />
        <link rel="stylesheet" href="<c:url value='/resources/lib/bootstrap/css/signin.css' />" media="all" />

        <script src="<c:url value='/resources/lib/bootstrap/js/bootstrap.min.js' />" type="text/javascript"></script>
    </head>
    <body>

    <div id="top-strip"></div>


    <div id='logo-holder'><img src="<c:url value='/resources/images/logo-large.png' />" /></div>


    <div class="container">

        <c:url value="/login_processing" var="loginUrl"/>
        <form:form name="f" action="${loginUrl}" method="post" cssClass="form-signin">
            <h2 class="form-signin-heading">Please sign in</h2>


            <c:if test="${param.error != null}">
                <div class="alert alert-error login_error">
                    Invalid username and password.
                </div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="alert alert-success">
                    You have been logged out.
                </div>
            </c:if>

            <input type="text" name="username" id="username" class="form-control form_shadow_clear" placeholder="Email address" autofocus>
            <input type="password" name="password" id="password" class="form-control form_shadow_clear" placeholder="Password">
            <label class="checkbox">
                <input type="checkbox" value="remember-me" name="_spring_security_remember_me"> Remember me
            </label>
            <button class="btn btn-lg btn-primary" type="submit">Sign in</button>
        </form:form>

    </div>
    </body>
</html>
