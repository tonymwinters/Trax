package com.trax.services.attendee;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.trax.models.Attendee;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/24/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AttendeeService {

    public Attendee saveAttendee(Attendee attendee);
    public Attendee getAttendee(Long id);
    public Attendee saveAttendee(String json);
    public Attendee saveAttendee(JsonElement json);
    public Set saveAttendees(String json);
    public Set saveAttendees(JsonElement json);
    public JsonDeserializer<Attendee> getAttendeeJsonDeserializer();
    public JsonDeserializer<Set<Attendee>> getAttendeesJsonDeserializer();
    public void deleteAttendee(Long id);
    public List getAttendees();
    public List bySessionAndFullName(Long id, String query);
}
