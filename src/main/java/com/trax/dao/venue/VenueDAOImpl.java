package com.trax.dao.venue;

import com.trax.models.Venue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/15/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class VenueDAOImpl implements VenueDAO{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addVenue(Venue venue){
        getCurrentSession().save(venue);
    }

    public void updateVenue(Venue venue){
        Venue venueToUpdate = getVenue(venue.getId());
        //todo add update stuff
        getCurrentSession().update(venueToUpdate);
    }

    public Venue getVenue(Long id){
        return (Venue) getCurrentSession().get(Venue.class, id);
    }

    public void deleteVenue(Long id){
        Venue venue = getVenue(id);
        if(venue != null)
            getCurrentSession().delete(venue);
    }

    public List<Venue> getVenues(){
        return getCurrentSession().createQuery("from Venue").list();
    }
}
