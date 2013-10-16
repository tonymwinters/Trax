package com.trax.dao.user;

import com.trax.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addUser(User user) {
        getCurrentSession().save(user);
    }

    public void updateUser(User user) {
        User userToUpdate = getUser(user.getId());

        // Update All Attributes, even if not all changed
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setMiddleName(user.getMiddleName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setContact(user.getContact());

        // Update
        getCurrentSession().update(userToUpdate);

    }

    public User getUser(Long id) {
        User user = (User) getCurrentSession().get(User.class, id);
        return user;
    }

    @SuppressWarnings("unchecked")
    public User getUser(String username){
            List<User> userList = new ArrayList<User>();
            Query query = getCurrentSession().createQuery("from User u where u.username = :username");
            query.setParameter("username", username);
            userList = query.list();
            if (userList.size() > 0)
                return userList.get(0);
            else
                return null;

    }

    public void deleteUser(Long id) {
        User user = getUser(id);
        if (user != null)
            getCurrentSession().delete(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return getCurrentSession().createQuery("from User").list();
    }
}
