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

    public void saveAttendee(Attendee attendee){
        getCurrentSession().merge(attendee);
    }

    public Attendee getAttendee(Long id){
        return (Attendee) getCurrentSession().get(Attendee.class, id);
    }

    public void deleteAttendee(Long id){
        Attendee attendee = getAttendee(id);
        if(Alfred.notNull(attendee))
            getCurrentSession().delete(attendee);
    }

    public List getAttendees(){
        return getCurrentSession().createQuery("from Attendee").list();
    }

    public List bySessionAndFullName(Long id, String query){
        return getCurrentSession().createQuery("from Attendee a " +
                "where (lower(a.user.firstName) like '%"+query+"%' " +
                "or lower(a.user.lastName) like '%"+query+"%') " +
                "and a.session.id = " + id).list();
    }
}
