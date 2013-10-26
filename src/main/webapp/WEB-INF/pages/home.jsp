<jsp:include page="includes/header.jsp" />
    <div class="container">

        ${message}<br/>
        <a href="${pageContext.request.contextPath}/owner/add.html">Add new Owner</a><br/>
        <a href="${pageContext.request.contextPath}/owner/list.html">Owner list</a><br/> <br/><br/>

        <a href="${pageContext.request.contextPath}/user/add.html">Add new User</a><br/>
        <a href="${pageContext.request.contextPath}/user/list.html">User list</a><br/><br/>

        <a href="${pageContext.request.contextPath}/venue/add.html">Add new Venue</a><br/>
        <a href="${pageContext.request.contextPath}/venue/list.html">Venue list</a><br/>


    </div>

<jsp:include page="includes/footer.jsp" />