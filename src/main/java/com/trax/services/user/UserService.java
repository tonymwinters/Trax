package com.trax.services.user;

import com.trax.models.Owner;
import com.trax.models.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    public void addUser(User user);
    public void updateUser(User user);
    public User getUser(Long id);
    public User getUser(String username);
    public void deleteUser(Long id);
    public List getUsers();

}

