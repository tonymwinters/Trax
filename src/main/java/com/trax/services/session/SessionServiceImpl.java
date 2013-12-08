package com.trax.services.session;

import com.google.gson.*;
import com.trax.dao.session.SessionDAO;
import com.trax.models.*;
import com.trax.services.attendee.AttendeeService;
import com.trax.services.room.RoomService;
import com.trax.services.venue.VenueService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/19/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional
public class SessionServiceImpl implements SessionService{

    @Autowired
    private SessionDAO sessionDAO;

//    @Autowired
//    private AttendeeService attendeeService;

    @Autowired
    private VenueService venueService;

    private JsonDeserializer<Session> sessionJsonDeserializer = new JsonDeserializer<Session>() {
        @Override
        public Session deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                JsonElement name = json.getAsJsonObject().get("name");
                JsonElement description = json.getAsJsonObject().get("description");
                JsonElement startTime = json.getAsJsonObject().get("startTime");
                JsonElement endTime = json.getAsJsonObject().get("endTime");
                JsonElement venue = json.getAsJsonObject().get("venue");
                JsonElement attendees = json.getAsJsonObject().get("attendees");
                JsonElement capacity = json.getAsJsonObject().get("capacity");
                JsonElement comments = json.getAsJsonObject().get("comments");
                Session session = new Session();
                if (Alfred.notNull(id)) {
                    session = getSession(id.getAsLong());
                }
                if (Alfred.notNull(name)) {
                    session.setName(name.getAsString());
                }
                if (Alfred.notNull(description)) {
                    session.setDescription(description.getAsString());
                }
                if (Alfred.notNull(startTime)) {
                    session.setStartTime(Alfred.gsonDeserializer.fromJson(startTime, Date.class));
                }
                if (Alfred.notNull(endTime)) {
                    session.setEndTime(Alfred.gsonDeserializer.fromJson(endTime, Date.class));
                }
                if (Alfred.notNull(venue)) {
                    session.setVenue(venueService.deserializeVenue(venue.toString()));
                }
//                if (Alfred.notNull(attendees)) {
//                    session.setAttendees(attendeeService.deserializeAttendees(attendees.getAsString()));
//                }
                if (Alfred.notNull(capacity)) {
                    session.setCapacity(capacity.getAsInt());
                }
//                if (Alfred.notNull(comments)) {
//                    session.setComments(Alfred.gsonDeserializer.fromJson(comments, List.class));
//                }
                return session;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Session.");
        }
    };

    public void addSession(Session session){
        sessionDAO.addSession(session);
    }

    public void updateSession(Session session){
        sessionDAO.updateSession(session);
    }

    public Session getSession(Long id){
        return sessionDAO.getSession(id);
    }

    public Session deserializeSession(String json){

        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Session.class, getSessionJsonDeserializer())
                .create();

        return gson.fromJson(json, Session.class);
    }

    public JsonDeserializer<Session> getSessionJsonDeserializer(){
        return sessionJsonDeserializer;
    }

    public void deleteSession(Long id){
        sessionDAO.deleteSession(id);
    }

    public List<Session> getSessions(){
        return sessionDAO.getSessions();
    }

    public List<Session> byName(String query){
        return sessionDAO.byName(query);
    }
}
