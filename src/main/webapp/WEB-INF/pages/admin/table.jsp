<jsp:include page="../includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">

    <h1 id="page-title-header"></h1>
   <br/><br/>
    <div id="side" style="display: inline-block; width: 200px; vertical-align: top;">
    <ul id="allModels" class="nav nav-tabs nav-stacked">
        <li class="active model" id="usersTab" ><a href="#" >Users</a></li>
        <li id="venuesTab" class="model"><a href="#">Venues</a></li>
        <li id="sessionsTab" class="model" ><a href="#">Sessions</a></li>
    </ul>
    </div>

    <div class="table_wrapper">
        <h1 id="table-title-header" class="table_title_h1"></h1>
        <a id="table-href" href =""><div id="table-add-button" class="table-add-new"></div></a>
        <input id="table-search" type="text" name="fname" class="table-search" />
        <table id="main-admin-table" class="table table-hover trax_table" style="width: 800px;">
        </table>
    </div>
      <br/><br/><br/>
    <p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

    <div id="modal">
        <ul>
            <li><label for="username">username</label> <input type="text" name="username" value="<%= object.username %>" /></li>
            <li><input type="text" name="email" value="<%= object.contact.emailAddress %>"/></li>
        </ul>
    </div>

</div>

<script src="<c:url value='/resources/ui/trax_app.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/ui/admin/admin.js'/>" type="text/javascript"></script>
<jsp:include page="../includes/footer.jsp" />