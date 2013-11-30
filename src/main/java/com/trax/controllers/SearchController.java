package com.trax.controllers;

import com.trax.models.Attendee;
import com.trax.models.Session;
import com.trax.models.Venue;
import com.trax.services.attendee.AttendeeService;
import com.trax.services.contact.ContactService;
import com.trax.services.owner.OwnerService;
import com.trax.services.role.RoleService;
import com.trax.services.room.RoomService;
import com.trax.services.session.SessionService;
import com.trax.services.user.UserService;
import com.trax.services.venue.VenueService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/29/13
 * Time: 8:31 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping(value="/search")
public class SearchController {

    //region Object Services
    @Autowired
    SessionService sessionService;

    @Autowired
    AttendeeService attendeeService;
    //endregion

    //region Session
    @ResponseBody
    @RequestMapping(value="/session/byName&q={query}", method= RequestMethod.GET)
    public String sessionByName(@PathVariable String query, Principal principal){
        String response;
        try{
            response = Alfred.renderSuccess(sessionService.byName(query));
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }
    //endregion

    //region Attendee
    @ResponseBody
    @RequestMapping(value="/attendee/bySessionAndFullName&session_id={sessionId}&q={query}", method= RequestMethod.GET)
    public String getAttendee(@PathVariable Long sessionId, @PathVariable String query, Principal principal){
        String response;
        try{
            response = Alfred.renderSuccess(attendeeService.bySessionAndFullName(sessionId, query));
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }
    //endregion
}
