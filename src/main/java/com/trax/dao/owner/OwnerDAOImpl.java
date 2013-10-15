package com.trax.dao.owner;

import com.trax.models.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class OwnerDAOImpl implements OwnerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addOwner(Owner owner) {
        getCurrentSession().save(owner);
    }

    public void updateOwner(Owner owner) {
        Owner ownerToUpdate = getOwner(owner.getId());
        ownerToUpdate.setName(owner.getName());
        getCurrentSession().update(ownerToUpdate);

    }

    public Owner getOwner(int id) {
        return (Owner) getCurrentSession().get(Owner.class, id);
    }

    public void deleteOwner(int id) {
        Owner owner = getOwner(id);
        if (owner != null)
            getCurrentSession().delete(owner);
    }

    public List<Owner> getOwners() {
        return getCurrentSession().createQuery("from Owner").list();
    }

    
}
