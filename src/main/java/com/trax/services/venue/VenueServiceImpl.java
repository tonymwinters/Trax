package com.trax.services.venue;

import com.google.gson.*;
import com.trax.dao.venue.VenueDAO;
import com.trax.models.Room;
import com.trax.models.Session;
import com.trax.models.Venue;
import com.trax.services.room.RoomService;
import com.trax.services.session.SessionService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

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
    SessionService sessionService;

    @Autowired
    RoomService roomService;

    private JsonDeserializer<Venue> venueJsonDeserializer = new JsonDeserializer<Venue>() {
        @Override
        public Venue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                if (Alfred.isNull(id)) {
                    return deserializeVenue(json.toString());
                } else {
                    return getVenue(id.getAsLong());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Venue.");
        }
    };

    public void addVenue(Venue venue) {
        venueDAO.addVenue(venue);
    }

    public void updateVenue(Venue venue) {
        venueDAO.updateVenue(venue);
    }

    public Venue getVenue(Long id) {
        return venueDAO.getVenue(id);
    }

    public Venue deserializeVenue(String json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Venue.class, getVenueJsonDeserializer())
                .create();

        return gson.fromJson(json, Venue.class);
    }

    public JsonDeserializer<Venue> getVenueJsonDeserializer(){
        return venueJsonDeserializer;
    }

    public void deleteVenue(Long id) {
        venueDAO.deleteVenue(id);
    }

    public List<Venue> getVenues() {
        return venueDAO.getVenues();
    }
}
