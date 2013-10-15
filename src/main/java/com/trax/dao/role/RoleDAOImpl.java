package com.trax.dao.role;

import com.trax.models.Owner;
import com.trax.models.Role;
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
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addRole(Role role) {
        getCurrentSession().save(role);
    }

    public void updateRole(Role role) {
        Role roleToUpdate = getRole(role.getId());
        roleToUpdate.setName(role.getName());
        roleToUpdate.setCode(role.getCode());
        getCurrentSession().update(roleToUpdate);

    }

    public Role getRole(Long id) {
        return (Role) getCurrentSession().get(Role.class, id);
    }

    public void deleteRole(Long id) {
        Role role = getRole(id);
        if (role != null)
            getCurrentSession().delete(role);
    }

    @SuppressWarnings("unchecked")
    public List<Owner> getRoles() {
        return getCurrentSession().createQuery("from Role").list();
    }
}
