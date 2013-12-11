package com.trax.dao.owner;

import com.trax.models.Owner;
import com.trax.utilities.Alfred;
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

    public void saveOwner(Owner owner) {
        getCurrentSession().saveOrUpdate(owner);
    }

    public Owner getOwner(Long id) {
        return (Owner) getCurrentSession().get(Owner.class, id);
    }

    public void deleteOwner(Long id) {
        Owner owner = getOwner(id);
        if (Alfred.notNull(owner))
            getCurrentSession().delete(owner);
    }

    public List<Owner> getOwners() {
        return getCurrentSession().createQuery("from Owner").list();
    }
}
