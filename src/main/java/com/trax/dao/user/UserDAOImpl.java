package com.trax.dao.user;

import com.trax.models.User;
import com.trax.utilities.Alfred;
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

    public void saveUser(User user) {
        getCurrentSession().merge(user);
        getCurrentSession().saveOrUpdate(user);
    }

    public User getUser(Long id) {
        User user = (User) getCurrentSession().get(User.class, id);
        return user;
    }

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
        if (Alfred.notNull(user))
            user.setRoles(null);
            getCurrentSession().delete(user);

    }

    public List getUsers() {
        return getCurrentSession().createQuery("from User").list();
    }
}
