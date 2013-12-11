package com.trax.dao.user;

import com.trax.models.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO {

    public void saveUser(User user);
    public User getUser(Long id);
    public User getUser(String username);
    public void deleteUser(Long id);
    public List getUsers();

}
