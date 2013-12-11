package com.trax.services.room;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.trax.models.Room;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/23/13
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoomService {

    public Room saveRoom(Room room);
    public Room getRoom(Long id);
    public Room saveRoom(String json);
    public Room saveRoom(JsonElement json);
    public Set saveRooms(String json);
    public Set saveRooms(JsonElement json);
    public JsonDeserializer<Room> getRoomJsonDeserializer();
    public JsonDeserializer<Set<Room>> getRoomsJsonDeserializer();
    public void deleteRoom(Long id);
    public List<Room> getRooms();
}
