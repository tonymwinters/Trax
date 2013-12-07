package com.trax.services.attendee;

import com.google.gson.JsonDeserializer;
import com.trax.models.Attendee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/24/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AttendeeService {

    public void addAttendee(Attendee attendee);
    public void updateAttendee(Attendee attendee);
    public Attendee getAttendee(Long id);
    public Attendee deserializeAttendee(String json);
    public JsonDeserializer<Attendee> getAttendeeJsonDeserializer();
    public void deleteAttendee(Long id);
    public List getAttendees();
    public List bySessionAndFullName(Long id, String query);
}
