package com.trax.services.attendee;

import com.google.gson.*;
import com.trax.dao.attendee.AttendeeDAO;
import com.trax.models.Attendee;
import com.trax.services.session.SessionService;
import com.trax.services.user.UserService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/24/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private AttendeeDAO attendeeDAO;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    private JsonDeserializer<Attendee> attendeeJsonDeserializer = new JsonDeserializer<Attendee>() {
        @Override
        public Attendee deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                JsonElement user = json.getAsJsonObject().get("user");
                JsonElement session = json.getAsJsonObject().get("session");
                JsonElement arrival = json.getAsJsonObject().get("arrival");
                JsonElement isOwner = json.getAsJsonObject().get("isOwner");
                Attendee attendee = new Attendee();
                if (Alfred.notNull(id)) {
                    attendee = getAttendee(id.getAsLong());
                }
                if (Alfred.notNull(user)) {
                    attendee.setUser(userService.deserializeUser(user));
                }
                if (Alfred.notNull(session)) {
                    attendee.setSession(sessionService.deserializeSession(session));
                }
                if (Alfred.notNull(arrival)) {
                    attendee.setArrival(Alfred.gsonDeserializer.fromJson(arrival, Date.class));
                }
                if (Alfred.notNull(isOwner)) {
                    attendee.setIsOwner(isOwner.getAsBoolean());
                }

                return attendee;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Attendee.");
        }
    };

    private JsonDeserializer<Set<Attendee>> attendeesJsonDeserializer = new JsonDeserializer<Set<Attendee>>() {
        @Override
        public Set<Attendee> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                Set<Attendee> attendees = new HashSet<Attendee>();
                for (JsonElement jsonAttendee : jsonElement.getAsJsonArray()) {
                    attendees.add(deserializeAttendee(jsonAttendee));
                }
                return attendees;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Attendees.");
        }
    };


    public void addAttendee(Attendee attendee){
        attendeeDAO.addAttendee(attendee);
    }

    public void updateAttendee(Attendee attendee){
        attendeeDAO.updateAttendee(attendee);
    }

    public Attendee getAttendee(Long id){
        return attendeeDAO.getAttendee(id);
    }

    public Attendee deserializeAttendee(String json) {
        return deserializeAttendee(new Gson().fromJson(json, JsonElement.class));
    }

    public Attendee deserializeAttendee(JsonElement json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Attendee.class, getAttendeeJsonDeserializer())
                .create();

        return gson.fromJson(json, Attendee.class);
    }

    public Set deserializeAttendees(String json) {
        return deserializeAttendees(new Gson().fromJson(json, JsonElement.class));
    }

    public Set deserializeAttendees(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Set.class, getAttendeesJsonDeserializer())
                .create();

        return gson.fromJson(json, Set.class);
    }

    public JsonDeserializer<Attendee> getAttendeeJsonDeserializer(){
        return attendeeJsonDeserializer;
    }

    public JsonDeserializer<Set<Attendee>> getAttendeesJsonDeserializer() {
        return attendeesJsonDeserializer;
    }

    public void deleteAttendee(Long id){
        attendeeDAO.deleteAttendee(id);
    }

    public List getAttendees(){
        return attendeeDAO.getAttendees();
    }

    public List bySessionAndFullName(Long id, String query){
        return attendeeDAO.bySessionAndFullName(id, query);
    }
}
