package com.trax.services.owner;

import com.google.gson.*;
import com.trax.dao.owner.OwnerDAO;
import com.trax.models.Contact;
import com.trax.models.Location;
import com.trax.models.Owner;
import com.trax.services.user.UserService;
import com.trax.services.venue.VenueService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */


@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerDAO ownerDAO;

    @Autowired
    private VenueService venueService;

    @Autowired
    private UserService userService;

    private JsonDeserializer<Owner> ownerJsonDeserializer = new JsonDeserializer<Owner>() {
        @Override
        public Owner deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                JsonElement name = json.getAsJsonObject().get("name");
                JsonElement contact = json.getAsJsonObject().get("contact");
                JsonElement location = json.getAsJsonObject().get("location");
                JsonElement venues = json.getAsJsonObject().get("venues");
                JsonElement users = json.getAsJsonObject().get("users");
                Owner owner = new Owner();
                if (Alfred.notNull(id)) {
                    owner = getOwner(id.getAsLong());
                }
                if (Alfred.notNull(name)) {
                    owner.setName(name.getAsString());
                }
                if (Alfred.notNull(contact)) {
                    owner.setContact(Alfred.gsonDeserializer.fromJson(contact, Contact.class));
                }
                if (Alfred.notNull(location)) {
                    owner.setLocation(Alfred.gsonDeserializer.fromJson(location, Location.class));
                }
                if (Alfred.notNull(venues)) {
                    owner.setVenues(venueService.saveVenues(venues));
                }
                if (Alfred.notNull(users)) {
                    owner.setUsers(userService.saveUsers(users));
                }
                return owner;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Owner.");
        }
    };

    public Owner saveOwner(Owner owner) {
        ownerDAO.saveOwner(owner);
        return owner;
    }

    public Owner getOwner(Long id) {
        return ownerDAO.getOwner(id);
    }

    public Owner saveOwner(String json) {
        return saveOwner(new Gson().fromJson(json, JsonElement.class));
    }

    public Owner saveOwner(JsonElement json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Owner.class, getOwnerJsonDeserializer())
                .create();
        return saveOwner(gson.fromJson(json, Owner.class));
    }

    public JsonDeserializer<Owner> getOwnerJsonDeserializer() {
        return ownerJsonDeserializer;
    }

    public void deleteOwner(Long id) {
        ownerDAO.deleteOwner(id);
    }

    public List<Owner> getOwners() {
        return ownerDAO.getOwners();
    }

}
