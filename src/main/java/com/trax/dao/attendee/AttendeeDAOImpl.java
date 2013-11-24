package com.trax.dao.attendee;

import com.trax.models.Attendee;
import com.trax.utilities.Alfred;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/24/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class AttendeeDAOImpl implements AttendeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addAttendee(Attendee attendee){
        getCurrentSession().save(attendee);
    }

    public void updateAttendee(Attendee attendee){
        if(Alfred.notNull(attendee.getId()))
            getCurrentSession().update(attendee);
    }

    public Attendee getAttendee(Long id){
        return (Attendee) getCurrentSession().get(Attendee.class, id);
    }

    public void deleteAttendee(Long id){
        Attendee attendee = getAttendee(id);
        if(Alfred.notNull(attendee))
            getCurrentSession().delete(attendee);
    }

    public List<Attendee> getAttendees(){
        return getCurrentSession().createQuery("from Attendee").list();
    }

    public List<Attendee> getAttendeesBySession(Long sessionId){
        return getCurrentSession().createQuery("from Attendee where session_id = " + sessionId).list();
    }
}
