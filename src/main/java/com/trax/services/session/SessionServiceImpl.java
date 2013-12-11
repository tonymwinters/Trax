package com.trax.services.session;

import com.google.gson.*;
import com.trax.dao.session.SessionDAO;
import com.trax.models.*;
import com.trax.services.attendee.AttendeeService;
import com.trax.services.comment.CommentService;
import com.trax.services.venue.VenueService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.*;

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

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    private VenueService venueService;

    @Autowired
    private CommentService commentService;

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
                    session.setVenue(venueService.saveVenue(venue));
                }
                if (Alfred.notNull(attendees)) {
                    session.setAttendees(attendeeService.saveAttendees(attendees));
                }
                if (Alfred.notNull(capacity)) {
                    session.setCapacity(capacity.getAsInt());
                }
                if (Alfred.notNull(comments)) {
                    session.setComments(commentService.saveComments(comments));
                }

                saveSession(session);
                return session;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Session.");
        }
    };

    private JsonDeserializer<Set<Session>> sessionsJsonDeserializer = new JsonDeserializer<Set<Session>>() {
        @Override
        public Set<Session> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                Set<Session> sessions = new HashSet<Session>();
                for (JsonElement jsonSession : jsonElement.getAsJsonArray()) {
                    sessions.add(saveSession(jsonSession));
                }
                return sessions;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Sessions.");
        }
    };

    public Session saveSession(Session session){
        sessionDAO.saveSession(session);
        return session;
    }

    public Session getSession(Long id){
        return sessionDAO.getSession(id);
    }

    public Session saveSession(String json){
        return saveSession(new Gson().fromJson(json, JsonElement.class));
    }

    public Session saveSession(JsonElement json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Session.class, getSessionJsonDeserializer())
                .create();

        return gson.fromJson(json, Session.class);
    }

    public Set saveSessions(String json){
        return saveSessions(new Gson().fromJson(json, JsonElement.class));
    }

    public Set saveSessions(JsonElement json){

        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Set.class, getSessionsJsonDeserializer())
                .create();

        return gson.fromJson(json, Set.class);
    }

    public JsonDeserializer<Session> getSessionJsonDeserializer(){
        return sessionJsonDeserializer;
    }

    public JsonDeserializer<Set<Session>> getSessionsJsonDeserializer(){
        return sessionsJsonDeserializer;
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
