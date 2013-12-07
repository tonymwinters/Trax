package com.trax.services.user;

import com.google.gson.*;
import com.trax.dao.user.UserDAO;
import com.trax.models.Contact;
import com.trax.models.Owner;
import com.trax.models.Role;
import com.trax.models.User;
import com.trax.services.owner.OwnerService;
import com.trax.services.role.RoleService;
import com.trax.utilities.Alfred;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private RoleService roleService;

    ShaPasswordEncoder sha = new ShaPasswordEncoder(512);

    private JsonDeserializer<User> userJsonDeserializer = new JsonDeserializer<User>() {
        @Override
        public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                JsonElement username = json.getAsJsonObject().get("username");
                JsonElement password = json.getAsJsonObject().get("password");
                JsonElement firstName = json.getAsJsonObject().get("firstName");
                JsonElement lastName = json.getAsJsonObject().get("lastName");
                JsonElement middleName = json.getAsJsonObject().get("middleName");
                JsonElement roles = json.getAsJsonObject().get("roles");
                JsonElement contact = json.getAsJsonObject().get("contact");
                User user = new User();
                if (Alfred.notNull(id)) {
                    user = getUser(id.getAsLong());
                }
                if (Alfred.notNull(username)) {
                    user.setUsername(username.getAsString());
                }
                if (Alfred.notNull(password)) {
                    user.setPassword(sha.encodePassword(password.getAsString(), null));
                }
                if (Alfred.notNull(firstName)) {
                    user.setFirstName(firstName.getAsString());
                }
                if (Alfred.notNull(middleName)) {
                    user.setMiddleName(middleName.getAsString());
                }
                if (Alfred.notNull(lastName)) {
                    user.setLastName(lastName.getAsString());
                }
                if (Alfred.notNull(roles)) {
                    user.setRoles(roleService.deserializeRoles(roles));
                }
                if (Alfred.notNull(contact)) {
                    user.setContact(Alfred.gsonDeserializer.fromJson(contact, Contact.class));
                }
                return user;
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
                .registerTypeAdapter(User.class, getUserJsonDeserializer())
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
