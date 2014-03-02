<jsp:include page="../includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="session_page_header">

</div>

<div class="sessions_left">
    <div class="sessions_column_header">Current Sessions</div>
    <div class="new_session">
        <div class="new_session_contents">
            <div class="icon icon_calendar_plus"></div>
            <span>New Session</span>
        </div>
    </div>
    <input type="text" name="sessions_search" id="session_search" class="form-control form_shadow_clear" placeholder="Search Sessions">
    <div class="all_sessions_container" id="all_sessions_container">

    </div>
</div>


<div class="session_info_container">


</div>


<div class="users_right">
    <div class="attendees_column_header">Attendees</div>
    <div class="new_attendee">
        <div class="new_attendee_contents">
            <div class="icon icon_person_plus"></div>
            <span>New Attendee</span>
        </div>
    </div>
    <input type="text" name="attendees_search" id="attendees_search" class="form-control form_shadow_clear" placeholder="Search Attendees">
    <div class="single_attendee_container">
        <div class="icon icon_person_check"></div>
        <div class="attendee_text_container">
            <h2>Billy Idol</h2>
            <h3>Checked in: <span class="on_time">2:59PM</span></h3>
        </div>
    </div>
    <div class="single_attendee_container active_attendee">
        <div class="icon icon_person_check"></div>
        <div class="attendee_text_container">
            <h2>Tony Winters</h2>
            <h3>Checked in: <span class="late">3:02PM</span></h3>
        </div>
    </div>
    <div class="single_attendee_container">
        <div class="icon icon_person_x"></div>
        <div class="attendee_text_container">
            <h2>AJ DaNelz</h2>
            <h3>Checked in: <span class="absent">Absent</span></h3>
        </div>
    </div>
</div>

<script src="<c:url value='/resources/lib/jeditable.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/lib/date.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/ui/pages/session/session.js'/>" type="text/javascript"></script>
<jsp:include page="../includes/footer.jsp" />