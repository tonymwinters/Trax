package com.trax.dao.location;

import com.trax.models.Location;
import com.trax.utilities.Alfred;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/15/13
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class LocationDAOImpl implements LocationDAO{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveLocation(Location location){
            getCurrentSession().saveOrUpdate(location);
    }

    public Location getLocation(Long id){
        return (Location) getCurrentSession().get(Location.class, id);
    }

    public void deleteLocation(Long id){
        Location location = getLocation(id);
        if (Alfred.notNull(location))
            getCurrentSession().delete(location);
    }

    public List<Location> getLocation(){
        return getCurrentSession().createQuery("from Location").list();
    }
}
