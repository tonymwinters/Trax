package com.trax.services.room;

import com.trax.models.Room;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/23/13
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoomService {

    public void addRoom(Room room);
    public void updateRoom(Room room);
    public Room getRoom(Long id);
    public void deleteRoom(Long id);
    public List<Room> getRooms();
}
