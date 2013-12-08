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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//    @Autowired
//    private SessionService sessionService;

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
//                if (Alfred.notNull(session)) {
//                    attendee.setSession(sessionService.deserializeSession(session.getAsString()));
//                }
                if (Alfred.notNull(arrival)) {
                    attendee.setArrival(Alfred.gsonDeserializer.fromJson(arrival, Date.class));
                }
                if (Alfred.notNull(id)) {
                    attendee.setIsOwner(Alfred.gsonDeserializer.fromJson(isOwner, Boolean.class));
                }

                return attendee;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Attendee.");
        }
    };

    private JsonDeserializer<ArrayList<Attendee>> attendeesJsonDeserializer = new JsonDeserializer<ArrayList<Attendee>>() {
        @Override
        public ArrayList<Attendee> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                ArrayList<Attendee> attendees = new ArrayList<Attendee>();
                for (JsonElement jsonAttendee : jsonElement.getAsJsonArray()) {
                    final Attendee attendee = deserializeAttendee(jsonAttendee.getAsString());
                    attendees.add(attendee);
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

    public Attendee deserializeAttendee(String json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Attendee.class, getAttendeeJsonDeserializer())
                .create();

        return gson.fromJson(json, Attendee.class);
    }

    @Override
    public ArrayList deserializeAttendees(String json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(ArrayList.class, getAttendeesJsonDeserializer())
                .create();

        return gson.fromJson(json, ArrayList.class);
    }

    public JsonDeserializer<Attendee> getAttendeeJsonDeserializer(){
        return attendeeJsonDeserializer;
    }

    @Override
    public JsonDeserializer<ArrayList<Attendee>> getAttendeesJsonDeserializer() {
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
