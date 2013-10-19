package com.trax.services.venue;

import com.trax.dao.venue.VenueDAO;
import com.trax.models.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/19/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional
public class VenueServiceImpl implements VenueService{

    @Autowired
    private VenueDAO venueDAO;

    public void addVenue(Venue venue) {
        venueDAO.addVenue(venue);
    }

    public void updateVenue(Venue venue) {
        venueDAO.updateVenue(venue);
    }

    public Venue getVenue(Long id) {
        return venueDAO.getVenue(id);
    }

    public void deleteVenue(Long id) {
        venueDAO.deleteVenue(id);
    }

    public List<Venue> getVenues() {
        return venueDAO.getVenues();
    }
}
