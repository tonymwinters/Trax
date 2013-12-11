package com.trax.dao.venue;

import com.trax.models.Venue;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/15/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VenueDAO {

    public void saveVenue(Venue venue);
    public Venue getVenue(Long id);
    public void deleteVenue(Long id);
    public List<Venue> getVenues();
}
