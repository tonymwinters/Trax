package com.trax.controllers;

import com.trax.models.*;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Location for the frontend to access crud operations
 */

@Controller
@RequestMapping(value="/resources")
public class ResourceController {

    //region Object Services
    @Autowired
    ContactService contactService;

    @Autowired
    OwnerService ownerService;

    @Autowired
    RoleService roleService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Autowired
    VenueService venueService;

    @Autowired
    RoomService roomService;

    @Autowired
    AttendeeService attendeeService;
    //endregion

    //region Owner
    @ResponseBody
    @RequestMapping(value="/owner/list", method= RequestMethod.GET)
    public String listOwners(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = Alfred.renderSuccess(ownerService.getOwners());
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/owner/{id}", method= RequestMethod.GET)
    public String listOwners(@PathVariable Long id, Principal principal){
        String response;
        try{
            Owner owner = ownerService.getOwner(id);
            if(Alfred.isNull(owner)){
                throw new Exception("Object doesn't exist.");
            }
            response = Alfred.renderSuccess(owner);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/owner/save", method= RequestMethod.POST)
    public String saveOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Owner newOwner = ownerService.saveOwner(requestJson);
            response = Alfred.renderSuccess(ownerService.getOwner(newOwner.getId()));
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/owner/delete", method= RequestMethod.POST)
    public String deleteOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Owner owner = ownerService.saveOwner(requestJson);
            if(Alfred.isNull(owner)){
                throw new Exception("Object doesn't exist.");
            }
            ownerService.deleteOwner(owner.getId());
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/owner/delete/{id}", method= RequestMethod.GET)
    public String deleteOwner(@PathVariable Long id, Principal principal){
        String response;
        try{
            Owner owner = ownerService.getOwner(id);
            if(Alfred.isNull(owner)){
                throw new Exception("Object doesn't exist.");
            }
            ownerService.deleteOwner(id);
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }
    //endregion

    //region User
    @ResponseBody
    @RequestMapping(value="/user/list", method= RequestMethod.GET)
    public String listUsers( Principal principal){
        String response;
        try{
            response = Alfred.renderSuccess(userService.getUsers());
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/user/{id}", method= RequestMethod.GET)
    public String getUser(@PathVariable Long id, Principal principal){
        String response;
        try{
            User user = userService.getUser(id);
            if(Alfred.isNull(user)){
                throw new Exception("Object doesn't exist.");
            }
            response = Alfred.renderSuccess(user);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/user/save", method= RequestMethod.POST)
    public String saveUser(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            User newUser = userService.saveUser(requestJson);
            response = Alfred.renderSuccess(newUser);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/user/delete/{id}", method= RequestMethod.POST)
    public String deleteUser(@PathVariable Long id, Principal principal){
        String response;
        try{
            User user = userService.getUser(id);
            if(Alfred.isNull(user)){
                throw new Exception("Object doesn't exist.");
            }
            userService.deleteUser(id);
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }
    //endregion

    //region User
    @ResponseBody
    @RequestMapping(value="/role/list", method= RequestMethod.GET)
    public String listRoles(Principal principal){
        String response;
        try{
            response = Alfred.renderSuccess(roleService.getRoles());
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/role/{id}", method= RequestMethod.GET)
    public String getRole(@PathVariable Long id, Principal principal){
        String response;
        try{
            User user = userService.getUser(id);
            if(Alfred.isNull(user)){
                throw new Exception("Object doesn't exist.");
            }
            response = Alfred.renderSuccess(user);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/role/save", method= RequestMethod.POST)
    public String saveRole(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Role newRole = roleService.saveRole(requestJson);
            response = Alfred.renderSuccess(newRole);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/role/delete/{id}", method= RequestMethod.POST)
    public String deleteRole(@PathVariable Long id, Principal principal){
        String response;
        try{
            Role role = roleService.getRole(id);
            if(Alfred.isNull(role)){
                throw new Exception("Object doesn't exist.");
            }
            roleService.deleteRole(id);
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }
    //endregion

    //region Venue
    @ResponseBody
    @RequestMapping(value="/venue/list", method= RequestMethod.GET)
    public String listVenues(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = Alfred.renderSuccess(venueService.getVenues());
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/venue/{id}", method= RequestMethod.GET)
    public String getVenue(@PathVariable Long id, Principal principal){
        String response;
        try{
            Venue venue = venueService.getVenue(id);
            if(Alfred.isNull(venue)){
                throw new Exception("Object doesn't exist.");
            }
            response = Alfred.renderSuccess(venue);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/venue/save", method= RequestMethod.POST)
    public String saveVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{

            Venue venue = venueService.saveVenue(requestJson);
            response = Alfred.renderSuccess(venue);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/venue/delete", method= RequestMethod.POST)
    public String deleteVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Venue venue = venueService.saveVenue(requestJson);
            if(Alfred.isNull(venue)){
                throw new Exception("Object doesn't exist.");
            }
            venueService.deleteVenue(venue.getId());
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/venue/delete/{id}", method= RequestMethod.POST)
    public String deleteVenue(@PathVariable Long id, Principal principal){
        String response;
        try{
            Venue venue = venueService.getVenue(id);
            if(Alfred.isNull(venue)){
                throw new Exception("Object doesn't exist.");
            }
            venueService.deleteVenue(venue.getId());
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Room
    @ResponseBody
    @RequestMapping(value="/room/list", method= RequestMethod.GET)
    public String listRooms(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = Alfred.renderSuccess(roomService.getRooms());
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/room/{id}", method= RequestMethod.GET)
    public String getRoom(@PathVariable Long id, Principal principal){
        String response;
        try{
            Room room = roomService.getRoom(id);
            if(Alfred.isNull(room)){
                throw new Exception("Object doesn't exist.");
            }
            response = Alfred.renderSuccess(room);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/room/save", method= RequestMethod.POST)
    public String saveRoom(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Room room  = roomService.saveRoom(requestJson);
            response = Alfred.renderSuccess(room);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/room/delete", method= RequestMethod.POST)
    public String deleteRoom(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Room room = roomService.saveRoom(requestJson);
            if(Alfred.isNull(room)){
                throw new Exception("Object doesn't exist.");
            }
            roomService.deleteRoom(room.getId());
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/room/delete/{id}", method= RequestMethod.GET)
    public String deleteRoom(@PathVariable Long id, Principal principal){
        String response;
        try{
            Room room = roomService.getRoom(id);
            if(Alfred.isNull(room)){
                throw new Exception("Object doesn't exist.");
            }
            roomService.deleteRoom(id);
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }
    //endregion

    //region Session
    @ResponseBody
    @RequestMapping(value="/session/list", method= RequestMethod.GET)
    public String listSessions(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = Alfred.renderSuccess(sessionService.getSessions());
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/session/{id}", method= RequestMethod.GET)
    public String getSession(@PathVariable Long id, Principal principal){
        String response;
        try{
            Session session = sessionService.getSession(id);
            if(Alfred.isNull(session)){
                throw new Exception("Object doesn't exist.");
            }
            response = Alfred.renderSuccess(session);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/session/save", method= RequestMethod.POST)
    public String saveSession(@RequestBody String requestJson, Principal principal){
        String response;
        System.out.println(requestJson);
        try{
            Session session = sessionService.saveSession(requestJson);
            response = Alfred.renderSuccess(session);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(value="/session/save/{id}", method= RequestMethod.GET)
    public String saveSession(@PathVariable Long id, @RequestParam String key, @RequestParam String value){
        String response;
        Map<String, String> attr = new HashMap<String, String>();
        attr.put("id", id.toString());
        attr.put(key, value);
        try{
            Session session = sessionService.saveSession(Alfred.gsonSerializer.toJson(attr));
            response = Alfred.renderSuccess(session);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/session/delete", method= RequestMethod.POST)
    public String deleteSession(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Session session = sessionService.saveSession(requestJson);
            if(Alfred.isNull(session)){
                throw new Exception("Object doesn't exist.");
            }
            sessionService.deleteSession(session.getId());
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/session/delete/{id}", method= RequestMethod.GET)
    public String deleteSession(@PathVariable Long id, Principal principal){
        String response;
        try{
            Session session = sessionService.getSession(id);
            if(Alfred.isNull(session)){
                throw new Exception("Object doesn't exist.");
            }
            sessionService.deleteSession(id);
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }
    //endregion

    //region Attendee
    @ResponseBody
    @RequestMapping(value="/attendee/list", method= RequestMethod.GET)
    public String listAttendees(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = Alfred.renderSuccess(attendeeService.getAttendees());
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/attendee/{id}", method= RequestMethod.GET)
    public String getAttendee(@PathVariable Long id, Principal principal){
        String response;
        try{
            Attendee attendee = attendeeService.getAttendee(id);
            if(Alfred.isNull(attendee)){
                throw new Exception("Object doesn't exist.");
            }
            response = Alfred.renderSuccess(attendee);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/attendee/save", method= RequestMethod.POST)
    public String saveAttendee(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Attendee attendee  = attendeeService.saveAttendee(requestJson);
            response = Alfred.renderSuccess(attendee);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/attendee/delete", method= RequestMethod.POST)
    public String deleteAttendee(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Attendee attendee = attendeeService.saveAttendee(requestJson);
            if(Alfred.isNull(attendee)){
                throw new Exception("Object doesn't exist.");
            }
            attendeeService.deleteAttendee(attendee.getId());
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/attendee/delete/{id}", method= RequestMethod.GET)
    public String deleteAttendee(@PathVariable Long id, Principal principal){
        String response;
        try{
            Attendee attendee = attendeeService.getAttendee(id);
            if(Alfred.isNull(attendee)){
                throw new Exception("Object doesn't exist.");
            }
            attendeeService.deleteAttendee(id);
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }
    //endregion
}
