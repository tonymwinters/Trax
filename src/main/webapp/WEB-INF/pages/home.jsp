<jsp:include page="includes/header.jsp" />
    <div class="container">

        <%--${message}<br/>--%>
        <%--<a href="${pageContext.request.contextPath}/owner/add.html">Add new Owner</a><br/>--%>
        <%--<a href="${pageContext.request.contextPath}/owner/list.html">Owner list</a><br/> <br/><br/>--%>

        <%--<a href="${pageContext.request.contextPath}/user/add.html">Add new User</a><br/>--%>
        <%--<a href="${pageContext.request.contextPath}/user/list.html">User list</a><br/><br/>--%>

        <%--<a href="${pageContext.request.contextPath}/venue/add.html">Add new Venue</a><br/>--%>
        <%--<a href="${pageContext.request.contextPath}/venue/list.html">Venue list</a><br/>--%>

        <div id="app_container">
            <a href="admin/venue"><div class="app_wrapper">
                <span>Venues</span>
                <div class="app_box app_venues"></div>
            </div></a>
            <div class="app_wrapper">
                <span>Track</span>
                <div class="app_box app_track"></div>
            </div>
            <div class="app_wrapper">
                <span>Sessions</span>
                <div class="app_box app_sessions"></div>
            </div>
            <div class="app_wrapper">
                <span>Keypad</span>
                <div class="app_box app_keypad"></div>
            </div>
            <div class="app_wrapper">
                <span>People</span>
                <div class="app_box app_people"></div>
            </div>
            <div class="app_wrapper">
                <span>Add Features</span>
                <div class="app_box app_add"></div>
            </div>
        </div>



    </div>

<jsp:include page="includes/footer.jsp" />