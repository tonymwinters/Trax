<jsp:include page="../includes/header.jsp" />

<div class="session_page_header">
    Sessions >> Crossfit Spartanburg
</div>

<div class="sessions_left">
    <div class="sessions_column_header">Current Sessions</div>
    <a href="#">
        <div class="new_session">
            <div class="icon icon_calendar_plus"></div>
            <span>New Session</span>
        </div>
    </a>
    <input type="text" name="sessions_search" id="session_search" class="form-control form_shadow_clear" placeholder="Seach Sessions">
    <div class="single_session_container">
        <h2>Yoga Class</h2>
        <h3>1:00PM to 2:00PM</h3>
    </div>
    <div class="single_session_container active_session">
        <h2>Aerobics</h2>
        <h3>2:00PM to 3:00PM</h3>
    </div>
    <div class="single_session_container">
        <h2>Crossfit</h2>
        <h3>5:00PM to 6:00PM</h3>
    </div>
</div>

<div class="users_right">
</div>

<jsp:include page="../includes/footer.jsp" />