package com.trax.services.room;

import com.google.gson.*;
import com.trax.dao.room.RoomDAO;
import com.trax.models.Room;
import com.trax.models.Session;
import com.trax.models.Venue;
import com.trax.services.session.SessionService;
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
 * Date: 11/23/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private VenueService venueService;

    @Autowired
    private SessionService sessionService;

    private JsonDeserializer<Room> roomJsonDeserializer = new JsonDeserializer<Room>() {
        @Override
        public Room deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                if (Alfred.isNull(id)) {
                    return deserializeRoom(json.toString());
                } else {
                    return getRoom(id.getAsLong());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Room.");
        }
    };

    public void addRoom(Room room){
        roomDAO.addRoom(room);
    }

    public void updateRoom(Room room){
        roomDAO.updateRoom(room);
    }

    public Room getRoom(Long id){
        return roomDAO.getRoom(id);
    }

    public Room deserializeRoom(String json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Session.class, sessionService.getSessionJsonDeserializer())
                .registerTypeAdapter(Venue.class, venueService.getVenueJsonDeserializer())
                .create();

        return gson.fromJson(json, Room.class);
    }

    public JsonDeserializer<Room> getRoomJsonDeserializer(){
        return roomJsonDeserializer;
    }

    public void deleteRoom(Long id){
        roomDAO.deleteRoom(id);
    }

    public List<Room> getRooms(){
        return roomDAO.getRooms();
    }
}
