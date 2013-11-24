package com.trax.services.room;

import com.trax.dao.room.RoomDAO;
import com.trax.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void addRoom(Room room){
        roomDAO.addRoom(room);
    }

    public void updateRoom(Room room){
        roomDAO.updateRoom(room);
    }

    public Room getRoom(Long id){
        return roomDAO.getRoom(id);
    }

    public void deleteRoom(Long id){
        roomDAO.deleteRoom(id);
    }

    public List<Room> getRooms(){
        return roomDAO.getRooms();
    }
}
