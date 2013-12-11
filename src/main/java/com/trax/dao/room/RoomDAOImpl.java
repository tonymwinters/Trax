package com.trax.dao.room;

import com.trax.models.Room;
import com.trax.utilities.Alfred;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/23/13
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class RoomDAOImpl implements RoomDAO{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addRoom(Room room){
        getCurrentSession().save(room);
        getCurrentSession().flush();
    }

    public void updateRoom(Room room){
        getCurrentSession().saveOrUpdate(room);
    }

    public Room getRoom(Long id){
        return (Room) getCurrentSession().get(Room.class, id);
    }

    public void deleteRoom(Long id){
        Room room = getRoom(id);
        if(Alfred.notNull(room))
            getCurrentSession().delete(room);
    }

    public List<Room> getRooms(){
        return getCurrentSession().createQuery("from Room").list();
    }
}
