package com.trax.services.user;

import com.google.gson.*;
import com.trax.dao.user.UserDAO;
import com.trax.models.Owner;
import com.trax.models.User;
import com.trax.services.owner.OwnerService;
import com.trax.utilities.Alfred;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OwnerService ownerService;

    private JsonDeserializer<User> userJsonDeserializer = new JsonDeserializer<User>() {
        @Override
        public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                if (Alfred.isNull(id)) {
                    return deserializeUser(json.toString());
                } else {
                    return getUser(id.getAsLong());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize User.");
        }
    };

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    public User getUser(String username) {
        return userDAO.getUser(username);
    }

    public User deserializeUser(String json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Owner.class, ownerService.getOwnerJsonDeserializer())
                .create();

        return gson.fromJson(json, User.class);
    }

    public JsonDeserializer<User> getUserJsonDeserializer(){
        return userJsonDeserializer;
    }

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}
