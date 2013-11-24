package com.trax.controllers;

import com.google.gson.*;
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

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Location for the frontend to access crud operations
 */

@Controller
@RequestMapping(value="/resources")
public class ResourceController {

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

    Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .serializeNulls()
            .setPrettyPrinting()
            // Serialize Date class
            .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
                public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
                    return new JsonPrimitive(sdf.format(date.getTime()));
                }
            })
             // Serialize SETS
            .registerTypeAdapter(Set.class, new JsonSerializer<Set>() {
                public JsonElement serialize(Set set, Type type, JsonSerializationContext context) {
                    JsonArray jsonArray = new JsonArray();
                    for (Object theObject : set) {
                        final JsonElement element = context.serialize(theObject);
                        jsonArray.add(element);
                    }
                    return jsonArray;
                }
            })
            .create();


    private String renderSuccess(Object object){
        Map<Object, Object> response =  new HashMap<Object, Object>();
        response.put("success", true);
        response.put("object", object);
        return gson.toJson(response);
    }

    private String renderSuccess(){
        Map<Object, Object> response =  new HashMap<Object, Object>();
        response.put("success", true);
        return gson.toJson(response);
    }

    private String renderError(String message){
        Map<Object, Object> response =  new HashMap<Object, Object>();
        response.put("success", false);
        response.put("message", message);
        return gson.toJson(response);
    }

    //region Contact
    @ResponseBody
    @RequestMapping(value="/contact/list", method= RequestMethod.GET)
    public String listContacts(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(contactService.getContacts());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/contact/add", method= RequestMethod.POST)
    public String addContact(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/contact/update", method= RequestMethod.POST)
    public String updateContact(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/contact/delete", method= RequestMethod.POST)
    public String deleteContact(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Location
//    @Autowired
//    LocationService locationService;
    @ResponseBody
    @RequestMapping(value="/location/list", method= RequestMethod.GET)
    public String listLocations(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/location/add", method= RequestMethod.POST)
    public String addLocation(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/location/update", method= RequestMethod.POST)
    public String updateLocation(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/location/delete", method= RequestMethod.POST)
    public String deleteLocation(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Owner
    @ResponseBody
    @RequestMapping(value="/owner/list", method= RequestMethod.GET)
    public String listOwners(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(ownerService.getOwners());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess(owner);
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/owner/add", method= RequestMethod.POST)
    public String addOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Owner newOwner = gson.fromJson(requestJson, Owner.class);
            ownerService.addOwner(newOwner);
            response = renderSuccess(ownerService.getOwner(newOwner.getId()));
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/owner/update", method= RequestMethod.POST)
    public String updateOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Owner owner = gson.fromJson(requestJson, Owner.class);
            ownerService.updateOwner(owner);
            response = renderSuccess(owner);
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/owner/delete", method= RequestMethod.POST)
    public String deleteOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Owner owner = gson.fromJson(requestJson, Owner.class);
            if(Alfred.isNull(owner)){
                throw new Exception("Object doesn't exist.");
            }
            ownerService.deleteOwner(owner.getId());
            response = renderSuccess();
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess();
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }
    //endregion

    //region Role
    @ResponseBody
    @RequestMapping(value="/role/list", method= RequestMethod.GET)
    public String listRoles(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                    .serializeNulls()
                    .setPrettyPrinting()
                    .create();
            response = renderSuccess(roleService.getRoles());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/role/add", method= RequestMethod.POST)
    public String addRole(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/role/update", method= RequestMethod.POST)
    public String updateRole(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/role/delete", method= RequestMethod.POST)
    public String deleteRole(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Room
    @ResponseBody
    @RequestMapping(value="/room/list", method= RequestMethod.GET)
    public String listRooms(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(roomService.getRooms());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess(room);
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/room/add", method= RequestMethod.POST)
    public String addRoom(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Room newRoom = gson.fromJson(requestJson, Room.class);
            roomService.addRoom(newRoom);
            response = renderSuccess(roomService.getRoom(newRoom.getId()));
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/room/update", method= RequestMethod.POST)
    public String updateRoom(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Room room  = gson.fromJson(requestJson, Room.class);
            roomService.updateRoom(room);
            response = renderSuccess(room);
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/room/delete", method= RequestMethod.POST)
    public String deleteRoom(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Room room = gson.fromJson(requestJson, Room.class);
            if(Alfred.isNull(room)){
                throw new Exception("Object doesn't exist.");
            }
            roomService.deleteRoom(room.getId());
            response = renderSuccess();
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess();
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess(sessionService.getSessions());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess(session);
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/session/add", method= RequestMethod.POST)
    public String addSession(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Session newSession = gson.fromJson(requestJson, Session.class);
            sessionService.addSession(newSession);
            response = renderSuccess(sessionService.getSession(newSession.getId()));
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/session/update", method= RequestMethod.POST)
    public String updateSession(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Session session = gson.fromJson(requestJson, Session.class);
            sessionService.updateSession(session);
            response = renderSuccess(session);
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/session/delete", method= RequestMethod.POST)
    public String deleteSession(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Session session = gson.fromJson(requestJson, Session.class);
            if(Alfred.isNull(session)){
                throw new Exception("Object doesn't exist.");
            }
            sessionService.deleteSession(session.getId());
            response = renderSuccess();
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess();
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess(attendeeService.getAttendees());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess(attendee);
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/attendee/add", method= RequestMethod.POST)
    public String addAttendee(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Attendee newAttendee = gson.fromJson(requestJson, Attendee.class);
            attendeeService.addAttendee(newAttendee);
            response = renderSuccess(attendeeService.getAttendee(newAttendee.getId()));
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/attendee/update", method= RequestMethod.POST)
    public String updateAttendee(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Attendee attendee  = gson.fromJson(requestJson, Attendee.class);
            attendeeService.updateAttendee(attendee);
            response = renderSuccess(attendee);
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/attendee/delete", method= RequestMethod.POST)
    public String deleteAttendee(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Attendee attendee = gson.fromJson(requestJson, Attendee.class);
            if(Alfred.isNull(attendee)){
                throw new Exception("Object doesn't exist.");
            }
            attendeeService.deleteAttendee(attendee.getId());
            response = renderSuccess();
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess();
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }
    //endregion

    //region User
    @ResponseBody
    @RequestMapping(value="/user/list", method= RequestMethod.GET)
    public String listUsers(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(userService.getUsers());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/user/add", method= RequestMethod.POST)
    public String addUser(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/user/update", method= RequestMethod.POST)
    public String updateUser(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/user/delete", method= RequestMethod.POST)
    public String deleteUser(@RequestParam String userId, Principal principal){
        String response;
        try{
            User user = userService.getUser(Long.parseLong(userId));
            if(Alfred.isNull(user)){
                throw new Exception("Object doesn't exist.");
            }
            userService.deleteUser(user.getId());
            response = renderSuccess();
        } catch (Exception ex){
            response = renderError(ex.getMessage());
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
            response = renderSuccess(venueService.getVenues());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/venue/add", method= RequestMethod.POST)
    public String addVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/venue/update", method= RequestMethod.POST)
    public String updateVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/venue/delete", method= RequestMethod.POST)
    public String deleteVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Activity
//    @Autowired
//    ActivityService activityService;
    @ResponseBody
    @RequestMapping(value="/activity/list", method= RequestMethod.GET)
    public String listActivities(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/activity/add", method= RequestMethod.POST)
    public String addActivity(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/activity/update", method= RequestMethod.POST)
    public String updateActivity(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/activity/delete", method= RequestMethod.POST)
    public String deleteActivity(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Participant
//    @Autowired
//    ParticipantService participantService;
    @ResponseBody
    @RequestMapping(value="/participant/list", method= RequestMethod.GET)
    public String listParticipants(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/participant/add", method= RequestMethod.POST)
    public String addParticipant(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/participant/update", method= RequestMethod.POST)
    public String updateParticipant(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/participant/delete", method= RequestMethod.POST)
    public String deleteParticipant(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

//region default Crud Methods
//    @ResponseBody
//    @RequestMapping(value="/list", method= RequestMethod.GET)
//    public String list(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = renderSuccess(new Object());
//        } catch (Exception ex){
//            response = renderError(ex.getMessage());
//        }
//        return response;
//    }
//
//    @ResponseBody
//    @RequestMapping(value="/add", method= RequestMethod.POST)
//    public String add(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = renderSuccess(new Object());
//        } catch (Exception ex){
//            response = renderError(ex.getMessage());
//        }
//        return response;}
//
//    @ResponseBody
//    @RequestMapping(value="/update", method= RequestMethod.POST)
//    public String update(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = renderSuccess(new Object());
//        } catch (Exception ex){
//            response = renderError(ex.getMessage());
//        }
//        return response;}
//
//    @ResponseBody
//    @RequestMapping(value="/delete", method= RequestMethod.POST)
//    public String delete(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = renderSuccess(new Object());
//        } catch (Exception ex){
//            response = renderError(ex.getMessage());
//        }
//        return response;}
//region
}
