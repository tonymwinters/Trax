package com.trax.dao.room;

import com.trax.models.Room;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/23/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoomDAO {
    public void saveRoom(Room room);
    public Room getRoom(Long id);
    public void deleteRoom(Long id);
    public List<Room> getRooms();
}
