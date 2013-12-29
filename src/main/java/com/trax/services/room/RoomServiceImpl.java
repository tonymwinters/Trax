package com.trax.services.room;

import com.google.gson.*;
import com.trax.dao.room.RoomDAO;
import com.trax.models.Room;
import com.trax.services.session.SessionService;
import com.trax.services.venue.VenueService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                JsonElement name = json.getAsJsonObject().get("name");
                JsonElement description = json.getAsJsonObject().get("description");
                JsonElement venue = json.getAsJsonObject().get("venue");
                JsonElement sessions = json.getAsJsonObject().get("sessions");
                Room room = new Room();
                if (Alfred.notNull(id)) {
                    room = getRoom(id.getAsLong());
                }
                if (Alfred.notNull(name)) {
                    room.setName(name.getAsString());
                }
                if (Alfred.notNull(description)) {
                    room.setDescription(description.getAsString());
                }
                if (Alfred.notNull(venue)) {
                    room.setVenue(venueService.saveVenue(venue));
                }
                if (Alfred.notNull(sessions)) {
                    room.setSessions(sessionService.saveSessions(sessions));
                }

                return room;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Room.");
        }
    };

    private JsonDeserializer<Set<Room>> roomsJsonDeserializer = new JsonDeserializer<Set<Room>>() {
        @Override
        public Set<Room> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                Set<Room> rooms = new HashSet<Room>();
                for (JsonElement jsonRoom : jsonElement.getAsJsonArray()) {
                    rooms.add(saveRoom(jsonRoom));
                }
                return rooms;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Rooms.");
        }
    };

    public Room saveRoom(Room room){
        roomDAO.saveRoom(room);
        return room;
    }

    public Room getRoom(Long id){
        return roomDAO.getRoom(id);
    }

    public Room saveRoom(String json){
        return saveRoom(new Gson().fromJson(json, JsonElement.class));
    }

    public Room saveRoom(JsonElement json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Room.class, getRoomJsonDeserializer())
                .create();

        return saveRoom(gson.fromJson(json, Room.class));
    }

    public Set saveRooms(String json) {
        return saveRooms(new Gson().fromJson(json, JsonElement.class));
    }

    public Set saveRooms(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Set.class, getRoomsJsonDeserializer())
                .create();

        return gson.fromJson(json, Set.class);
    }

    public JsonDeserializer<Room> getRoomJsonDeserializer(){
        return roomJsonDeserializer;
    }

    public JsonDeserializer<Set<Room>> getRoomsJsonDeserializer() {
        return roomsJsonDeserializer;
    }

    public void deleteRoom(Long id){
        Room room = getRoom(id);
        room.getVenue().getRooms().remove(room);
        room.setVenue(null);
        roomDAO.deleteRoom(id);
    }

    public List<Room> getRooms(){
        return roomDAO.getRooms();
    }
}
