package com.trax.services.user;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.trax.models.Owner;
import com.trax.models.User;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    public User saveUser(User user);
    public User getUser(Long id);
    public User getUser(String username);
    public User saveUser(String json);
    public User saveUser(JsonElement json);
    public Set saveUsers(String json);
    public Set saveUsers(JsonElement json);
    public JsonDeserializer<User> getUserJsonDeserializer();
    public JsonDeserializer<Set<User>> getUsersJsonDeserializer();
    public void deleteUser(Long id);
    public List getUsers();

}

