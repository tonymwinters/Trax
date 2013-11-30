package com.trax.services.attendee;

import com.trax.dao.attendee.AttendeeDAO;
import com.trax.models.Attendee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/24/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private AttendeeDAO attendeeDAO;

    public void addAttendee(Attendee attendee){
        attendeeDAO.addAttendee(attendee);
    }

    public void updateAttendee(Attendee attendee){
        attendeeDAO.updateAttendee(attendee);
    }

    public Attendee getAttendee(Long id){
        return attendeeDAO.getAttendee(id);
    }

    public void deleteAttendee(Long id){
        attendeeDAO.deleteAttendee(id);
    }

    public List getAttendees(){
        return attendeeDAO.getAttendees();
    }

    public List bySessionAndFullName(Long id, String query){
        return attendeeDAO.bySessionAndFullName(id, query);
    }
}
