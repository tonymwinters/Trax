package com.trax.dao.attendee;

import com.trax.models.Attendee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/24/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AttendeeDAO {

    public void saveAttendee(Attendee attendee);
    public Attendee getAttendee(Long id);
    public void deleteAttendee(Long id);
    public List getAttendees();
    public List bySessionAndFullName(Long id, String query);
}
