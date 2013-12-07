package com.trax.services.session;

import com.google.gson.*;
import com.trax.dao.session.SessionDAO;
import com.trax.models.Room;
import com.trax.models.Session;
import com.trax.models.Venue;
import com.trax.services.room.RoomService;
import com.trax.services.venue.VenueService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
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

    @Autowired
    private RoomService roomService;

    @Autowired
    private VenueService venueService;

    private JsonDeserializer<Session> sessionJsonDeserializer = new JsonDeserializer<Session>() {
        @Override
        public Session deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                if (Alfred.isNull(id)) {
                    return deserializeSession(json.toString());
                } else {
                    return getSession(id.getAsLong());
                }
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
        .registerTypeAdapter(Room.class, roomService.getRoomJsonDeserializer())
        .registerTypeAdapter(Venue.class, venueService.getVenueJsonDeserializer())
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
