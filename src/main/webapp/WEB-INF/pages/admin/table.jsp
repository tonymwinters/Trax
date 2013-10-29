<jsp:include page="../includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">

    <h1 id="page-title-header"></h1>
   <br/><br/>
    <div id="side" style="display: inline-block; width: 200px;">
    <ul id="allModels" class="nav nav-tabs nav-stacked">
        <li class="active model" id="usersTab" ><a href="#" >Users</a></li>
        <li id="venuesTab" class="model"><a href="#">Venues</a></li>
        <li id="sessionsTab" class="model" ><a href="#">Sessions</a></li>
    </ul>
    </div>

    <table id="main-admin-table" class="table table-hover" style="width: 800px; display: inline-block; float: right;">
    </table>
      <br/><br/><br/>
    <p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</div>

<script src="<c:url value='/resources/ui/trax.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/ui/admin/admin.js'/>" type="text/javascript"></script>
<jsp:include page="../includes/footer.jsp" />