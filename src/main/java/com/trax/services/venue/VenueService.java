package com.trax.services.venue;

import com.google.gson.JsonDeserializer;
import com.trax.models.Venue;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/19/13
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VenueService {

    public void addVenue(Venue venue);
    public void updateVenue(Venue venue);
    public Venue getVenue(Long id);
    public Venue deserializeVenue(String json);
    public JsonDeserializer<Venue> getVenueJsonDeserializer();
    public void deleteVenue(Long id);
    public List<Venue> getVenues();
}
