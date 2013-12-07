<jsp:include page="../includes/header.jsp" />

<div class="session_page_header">
    Sessions >> Crossfit Spartanburg
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


<div class="session_info_container">
    <div class="session_info_header">
        <div class="name_and_time_container">
            <div class="name">Aerobics</div>
            <div class="time">3:00PM - 4:00PM</div>
        </div>

    </div>

    <div class="session_info_mainarea">
        <div class="description">This is a session. I love this session. In fact, if I could put this session in my will, I probably would. I probably will. Get it?</div>
    </div>

    <div class="session_info_comments">
        <div class="comment_container">
            <div class="comment_contents">
                <div class="comment_picture"></div>
                <div class="comment_text">
                    <div class="comment_user">Tony Winters</div>
                    <div class="comment_comment">I really like this session.</div>
                </div>
            </div>
        </div>
        <div class="comment_container">
            <div class="comment_contents">
                <div class="comment_picture"></div>
                <div class="comment_text">
                    <div class="comment_user">Matt Garkusha</div>
                    <div class="comment_comment">Yea its pretty sweet. I especially like the sessiony part.</div>
                </div>
            </div>
        </div>
        <div class="comment_container">
            <div class="comment_contents">
                <div class="comment_picture"></div>
                <div class="comment_text">
                    <div class="comment_user">Holden Cribb</div>
                    <div class="comment_comment">I like soccer. It is soooooo cool. </div>
                </div>
            </div>
        </div>
        <div class="comment_container">
            <div class="comment_contents">
                <div class="comment_picture"></div>
                <div class="comment_text">
                    <div class="comment_user">Teduardo</div>
                    <div class="comment_comment">Ruff ruff, bark bark! I like food. ok bye.</div>
                </div>
            </div>
        </div>
        <div class="comment_container">
            <div class="comment_contents">
                <div class="comment_picture"></div>
                <div class="comment_text">
                    <div class="comment_user">Billy Cringolaction</div>
                    <div class="comment_comment">That is so crazy; I too love to work out!</div>
                </div>
            </div>
        </div>
    </div>
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

<jsp:include page="../includes/footer.jsp" />