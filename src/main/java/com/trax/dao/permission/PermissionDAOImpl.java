package com.trax.dao.permission;

import com.trax.models.Permission;
import com.trax.utilities.Alfred;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class PermissionDAOImpl implements PermissionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void savePermission(Permission permission) {
        getCurrentSession().saveOrUpdate(permission);
    }

    public Permission getPermission(Long id) {
        return (Permission) getCurrentSession().get(Permission.class, id);
    }

    public void deletePermission(Long id) {
        Permission permission = getPermission(id);
        if (Alfred.notNull(permission))
            getCurrentSession().delete(permission);
    }

    public List<Permission> getPermissions() {
        return getCurrentSession().createQuery("from Permission").list();
    }
}
