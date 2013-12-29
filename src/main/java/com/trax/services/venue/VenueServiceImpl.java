package com.trax.services.venue;

import com.google.gson.*;
import com.trax.dao.venue.VenueDAO;
import com.trax.models.*;
import com.trax.services.owner.OwnerService;
import com.trax.services.room.RoomService;
import com.trax.services.session.SessionService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/19/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional
public class VenueServiceImpl implements VenueService{

    @Autowired
    private VenueDAO venueDAO;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private OwnerService ownerService;

    private JsonDeserializer<Venue> venueJsonDeserializer = new JsonDeserializer<Venue>() {
        @Override
        public Venue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                JsonElement name = json.getAsJsonObject().get("name");
                JsonElement owner = json.getAsJsonObject().get("owner");
                JsonElement rooms = json.getAsJsonObject().get("rooms");
                JsonElement sessions = json.getAsJsonObject().get("sessions");
                JsonElement contact = json.getAsJsonObject().get("contact");
                JsonElement location = json.getAsJsonObject().get("location");
                Venue venue = new Venue();
                if (Alfred.notNull(id)) {
                    venue = getVenue(id.getAsLong());
                }
                if (Alfred.notNull(name)) {
                    venue.setName(name.getAsString());
                }
                if (Alfred.notNull(owner)) {
                    venue.setOwner(ownerService.saveOwner(owner));
                }
                if (Alfred.notNull(rooms)) {
                    venue.setRooms(roomService.saveRooms(rooms));
                }
                if (Alfred.notNull(sessions)) {
                    venue.setSessions(sessionService.saveSessions(sessions));
                }
                if (Alfred.notNull(contact)) {
                    venue.setContact(Alfred.gsonDeserializer.fromJson(contact, Contact.class));
                }
                if (Alfred.notNull(location)) {
                    venue.setLocation(Alfred.gsonDeserializer.fromJson(location, Location.class));
                }

                return venue;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Venue.");
        }
    };

    private JsonDeserializer<Set<Venue>> venuesJsonDeserializer = new JsonDeserializer<Set<Venue>>() {
        @Override
        public Set<Venue> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                Set<Venue> sessions = new HashSet<Venue>();
                for (JsonElement jsonVenue : jsonElement.getAsJsonArray()) {
                    sessions.add(saveVenue(jsonVenue));
                }
                return sessions;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Venues.");
        }
    };

    public Venue saveVenue(Venue venue) {
        venueDAO.saveVenue(venue);
        return venue;
    }

    public Venue getVenue(Long id) {
        return venueDAO.getVenue(id);
    }

    public Venue saveVenue(String json){
        return saveVenue(new Gson().fromJson(json, JsonElement.class));
    }

    public Venue saveVenue(JsonElement json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Venue.class, getVenueJsonDeserializer())
                .create();

        return saveVenue(gson.fromJson(json, Venue.class));
    }

    public Set saveVenues(String json){
        return saveVenues(new Gson().fromJson(json, JsonElement.class));
    }

    public Set saveVenues(JsonElement json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Set.class, getVenuesJsonDeserializer())
                .create();

        return gson.fromJson(json, Set.class);
    }

    public JsonDeserializer<Venue> getVenueJsonDeserializer(){
        return venueJsonDeserializer;
    }

    public JsonDeserializer<Set<Venue>> getVenuesJsonDeserializer(){
        return venuesJsonDeserializer;
    }

    public void deleteVenue(Long id) {
        Venue venue = getVenue(id);
        venue.getOwner().getVenues().remove(venue);
        venue.setOwner(null);
        venueDAO.deleteVenue(id);
    }

    public List<Venue> getVenues() {
        return venueDAO.getVenues();
    }
}
