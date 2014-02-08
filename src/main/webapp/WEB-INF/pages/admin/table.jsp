<jsp:include page="../includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="admin-table" class="container">

    <h1 id="page-title"></h1>
   <br/><br/>
    <div id="side" style="display: inline-block; width: 200px; vertical-align: top;">
    <ul id="allModels" class="nav nav-tabs nav-stacked">
        <li id="usersTab" class="active model" ><a href="#" >Users</a></li>
        <li id="rolesTab" class="model" ><a href="#">Roles</a></li>
        <li id="venuesTab" class="model"><a href="#">Venues</a></li>
    </ul>
    </div>

    <div id="main-admin-table"  style="width: 800px;">
    </div>
      <br/><br/><br/>
    <p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</div>

<script src="<c:url value='/resources/ui/pages/admin/admin.js'/>" type="text/javascript"></script>
<jsp:include page="../includes/footer.jsp" />