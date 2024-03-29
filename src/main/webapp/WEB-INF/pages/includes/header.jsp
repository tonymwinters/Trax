<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/reset.css" />' media="all" />
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/lib/bootstrap/css/bootstrap.css" />' media="all" />
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/style.css" />' media="all" />
    <link rel="stylesheet" href='<c:url value="/resources/lib/datepicker/themes/classic.css" />' id="theme_base">
    <link rel="stylesheet" href='<c:url value="/resources/lib/datepicker/themes/classic.date.css" />' id="theme_date">
    <link rel="stylesheet" href='<c:url value="/resources/lib/datepicker/themes/classic.time.css" />' id="theme_time">
    <script type="text/javascript">var contextPath = "<%= request.getContextPath() %>";</script>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script>$.noConflict();</script>
    <script src="//ajax.googleapis.com/ajax/libs/prototype/1.7.1.0/prototype.js" type="text/javascript"></script>
    <script type="text/javascript" src="<c:url value='/resources/lib/ejs/ejs_production.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/lib/bootstrap/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/resources/ui/trax_app.js'/>" type="text/javascript"></script>
</head>
<body>
    <div id="top-strip"></div>
    <div id="top-bar">
        <a href="${pageContext.request.contextPath}/"><div id="logo"><img src="<c:url value="/resources/images/logo-small.png" />" /></div></a>
        <div id="user-actions">
            <c:url value="/j_spring_security_logout" var="logoutUrl"/>
            <form:form id="logout_form" name="f" action="${logoutUrl}" method="post">
                <div class="form-actions">
                    <h3 id="logged-in-user"><%= (request.getUserPrincipal() != null) ? request.getUserPrincipal().getName() : "" %></h3>
                    <a href="#" class="default-button">Settings</a>
                    <a href="#" class="default-button">Account</a>
                    <a href="#" onclick="document.getElementById('logout_form').submit();" class="default-button">Logout</a>
                </div>
            </form:form>
        </div>
    </div>

    <div class="body-wrapper">