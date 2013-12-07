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
import java.text.ParseException;
import java.util.Date;

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

    private Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .setPrettyPrinting()
            .create();

    private Gson gsonDeserializer = new GsonBuilder()
            //Date
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        return json == null ? null : Alfred.dateFormat.parse(json.getAsString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    throw new JsonParseException("Could not parse date.");
                }
            })

                    //Attendee
            .registerTypeAdapter(Attendee.class, new JsonDeserializer<Attendee>() {
                @Override
                public Attendee deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        JsonElement id = json.getAsJsonObject().get("id");
                        if (Alfred.isNull(id)) {
                            return gson.fromJson(json, Attendee.class);
                        } else {
                            return attendeeService.getAttendee(id.getAsLong());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    throw new JsonParseException("Could not deserialize User.");
                }
            })

                    //Room
            .registerTypeAdapter(Room.class, new JsonDeserializer<Room>() {
                @Override
                public Room deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        JsonElement id = json.getAsJsonObject().get("id");
                        if (Alfred.isNull(id)) {
                            return gson.fromJson(json, Room.class);
                        } else {
                            return roomService.getRoom(id.getAsLong());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    throw new JsonParseException("Could not deserialize User.");
                }
            })

                    //Venue
            .registerTypeAdapter(Venue.class, new JsonDeserializer<Venue>() {
                @Override
                public Venue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        JsonElement id = json.getAsJsonObject().get("id");
                        if (Alfred.isNull(id)) {
                            return gson.fromJson(json, Venue.class);
                        } else {
                            return venueService.getVenue(id.getAsLong());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    throw new JsonParseException("Could not deserialize User.");
                }
            })

                    //Owner
            .registerTypeAdapter(Owner.class, new JsonDeserializer<Owner>() {
                @Override
                public Owner deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        JsonElement id = json.getAsJsonObject().get("id");
                        if (Alfred.isNull(id)) {
                            return gson.fromJson(json, Owner.class);
                        } else {
                            return ownerService.getOwner(id.getAsLong());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    throw new JsonParseException("Could not deserialize User.");
                }
            })

                    //Users
            .registerTypeAdapter(User.class, new JsonDeserializer<User>() {
                @Override
                public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        JsonElement id = json.getAsJsonObject().get("id");
                        if (Alfred.isNull(id)) {
                            return gson.fromJson(json, User.class);
                        } else {
                            return userService.getUser(id.getAsLong());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    throw new JsonParseException("Could not deserialize User.");
                }
            })

                    //Session
            .registerTypeAdapter(Session.class, new JsonDeserializer<Session>() {
                @Override
                public Session deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        JsonElement id = json.getAsJsonObject().get("id");
                        if (Alfred.isNull(id)) {
                            return gsonDeserializer.fromJson(json, Session.class);
                        } else {
                            return sessionService.getSession(id.getAsLong());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    throw new JsonParseException("Could not deserialize Session.");
                }
            })
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .setPrettyPrinting()
            .create();

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
    @RequestMapping(value="/owner/add", method= RequestMethod.POST)
    public String addOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Owner newOwner = ownerService.deserializeOwner(requestJson);
            ownerService.addOwner(newOwner);
            response = Alfred.renderSuccess(ownerService.getOwner(newOwner.getId()));
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/owner/update", method= RequestMethod.POST)
    public String updateOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Owner owner = gsonDeserializer.fromJson(requestJson, Owner.class);
            ownerService.updateOwner(owner);
            response = Alfred.renderSuccess(owner);
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
            Owner owner = gsonDeserializer.fromJson(requestJson, Owner.class);
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
    @RequestMapping(value="/room/add", method= RequestMethod.POST)
    public String addRoom(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Room newRoom = gsonDeserializer.fromJson(requestJson, Room.class);
            roomService.addRoom(newRoom);
            response = Alfred.renderSuccess(roomService.getRoom(newRoom.getId()));
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/room/update", method= RequestMethod.POST)
    public String updateRoom(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Room room  = gsonDeserializer.fromJson(requestJson, Room.class);
            roomService.updateRoom(room);
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
            Room room = gsonDeserializer.fromJson(requestJson, Room.class);
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
    @RequestMapping(value="/session/add", method= RequestMethod.POST)
    public String addSession(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Session newSession = sessionService.deserializeSession(requestJson);
            sessionService.addSession(newSession);
            response = Alfred.renderSuccess(sessionService.getSession(newSession.getId()));
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/session/update", method= RequestMethod.POST)
    public String updateSession(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Session session = sessionService.deserializeSession(requestJson);
            sessionService.updateSession(session);
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
            Session session = gsonDeserializer.fromJson(requestJson, Session.class);
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
    @RequestMapping(value="/attendee/add", method= RequestMethod.POST)
    public String addAttendee(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Attendee newAttendee = gsonDeserializer.fromJson(requestJson, Attendee.class);
            attendeeService.addAttendee(newAttendee);
            response = Alfred.renderSuccess(attendeeService.getAttendee(newAttendee.getId()));
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/attendee/update", method= RequestMethod.POST)
    public String updateAttendee(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Attendee attendee  = gsonDeserializer.fromJson(requestJson, Attendee.class);
            if(Alfred.isNull(attendee.getId()))
                throw new Exception("Object doesn't exist. Add it first.");

            attendeeService.updateAttendee(attendee);
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
            Attendee attendee = gsonDeserializer.fromJson(requestJson, Attendee.class);
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

    //region User
    @ResponseBody
    @RequestMapping(value="/user/list", method= RequestMethod.GET)
    public String listUsers(@RequestBody String requestJson, Principal principal){
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
    @RequestMapping(value="/user/add", method= RequestMethod.POST)
    public String addUser(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            User newUser = gsonDeserializer.fromJson(requestJson, User.class);
            userService.addUser(newUser);
            response = Alfred.renderSuccess(userService.getUser(newUser.getId()));
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/user/update", method= RequestMethod.POST)
    public String updateUser(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            User newUser = gsonDeserializer.fromJson(requestJson, User.class);
            userService.updateUser(newUser);
            response = Alfred.renderSuccess(newUser);
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/user/delete", method= RequestMethod.POST)
    public String deleteUser(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            User user = gsonDeserializer.fromJson(requestJson, User.class);
            if(Alfred.isNull(user)){
                throw new Exception("Object doesn't exist.");
            }
            userService.deleteUser(user.getId());
            response = Alfred.renderSuccess();
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/user/delete/{id}", method= RequestMethod.POST)
    public String deleteUser(@RequestParam Long id, Principal principal){
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
    @RequestMapping(value="/venue/add", method= RequestMethod.POST)
    public String addVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            Venue newVenue = gsonDeserializer.fromJson(requestJson, Venue.class);
            venueService.addVenue(newVenue);
            response = Alfred.renderSuccess(venueService.getVenue(newVenue.getId()));
        } catch (Exception ex){
            response = Alfred.renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/venue/update", method= RequestMethod.POST)
    public String updateVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{

            Venue venue = gsonDeserializer.fromJson(requestJson, Venue.class);
            venueService.updateVenue(venue);
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
            Venue venue = gsonDeserializer.fromJson(requestJson, Venue.class);
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

    //region Activity
//    @Autowired
//    ActivityService activityService;
//    @ResponseBody
//    @RequestMapping(value="/activity/list", method= RequestMethod.GET)
//    public String listActivities(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = Alfred.renderSuccess(new Object());
//        } catch (Exception ex){
//            response = Alfred.renderError(ex.getMessage());
//        }
//        return response;
//    }
//
//    @ResponseBody
//    @RequestMapping(value="/activity/add", method= RequestMethod.POST)
//    public String addActivity(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = Alfred.renderSuccess(new Object());
//        } catch (Exception ex){
//            response = Alfred.renderError(ex.getMessage());
//        }
//        return response;}
//
//    @ResponseBody
//    @RequestMapping(value="/activity/update", method= RequestMethod.POST)
//    public String updateActivity(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = Alfred.renderSuccess(new Object());
//        } catch (Exception ex){
//            response = Alfred.renderError(ex.getMessage());
//        }
//        return response;}
//
//    @ResponseBody
//    @RequestMapping(value="/activity/delete", method= RequestMethod.POST)
//    public String deleteActivity(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = Alfred.renderSuccess(new Object());
//        } catch (Exception ex){
//            response = Alfred.renderError(ex.getMessage());
//        }
//        return response;}
    //endregion


    //region default Crud Methods
//    @ResponseBody
//    @RequestMapping(value="/list", method= RequestMethod.GET)
//    public String list(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = Alfred.renderSuccess(new Object());
//        } catch (Exception ex){
//            response = Alfred.renderError(ex.getMessage());
//        }
//        return response;
//    }
//
//    @ResponseBody
//    @RequestMapping(value="/add", method= RequestMethod.POST)
//    public String add(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = Alfred.renderSuccess(new Object());
//        } catch (Exception ex){
//            response = Alfred.renderError(ex.getMessage());
//        }
//        return response;}
//
//    @ResponseBody
//    @RequestMapping(value="/update", method= RequestMethod.POST)
//    public String update(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = Alfred.renderSuccess(new Object());
//        } catch (Exception ex){
//            response = Alfred.renderError(ex.getMessage());
//        }
//        return response;}
//
//    @ResponseBody
//    @RequestMapping(value="/delete", method= RequestMethod.POST)
//    public String delete(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = Alfred.renderSuccess(new Object());
//        } catch (Exception ex){
//            response = Alfred.renderError(ex.getMessage());
//        }
//        return response;}
    //endregion
}
