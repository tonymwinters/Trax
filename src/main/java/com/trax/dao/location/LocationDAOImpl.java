package com.trax.dao.location;

import com.trax.models.Location;
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
public class LocationDAOImpl {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addLocation(Location location){
        getCurrentSession().save(location);
    }

    public void updateLocation(Location location){
        Location locationToUpdate = getLocation(location.getId());
        //todo update stuff
        getCurrentSession().update(locationToUpdate);
    }

    public Location getLocation(Long id){
        return (Location) getCurrentSession().get(Location.class, id);
    }

    public void deleteLocation(Long id){
        Location location = getLocation(id);
        if (location != null)
            getCurrentSession().delete(location);
    }

    public List<Location> getLocation(){
        return getCurrentSession().createQuery("from Location").list();
    }
}
