package com.trax.services.owner;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.*;
import com.trax.dao.owner.OwnerDAO;
import com.trax.models.Owner;
import com.trax.models.User;
import com.trax.models.Venue;
import com.trax.services.user.UserService;
import com.trax.services.venue.VenueService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
                if (Alfred.isNull(id)) {
                    return deserializeOwner(json.toString());
                } else {
                    return getOwner(id.getAsLong());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Owner.");
        }
    };

    public void addOwner(Owner owner) {
        ownerDAO.addOwner(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDAO.updateOwner(owner);
    }

    public Owner getOwner(Long id) {
        return ownerDAO.getOwner(id);
    }

    public Owner deserializeOwner(String json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Venue.class, venueService.getVenueJsonDeserializer())
                .registerTypeAdapter(User.class, userService.getUserJsonDeserializer())
                .create();
        return gson.fromJson(json, Owner.class);
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
