package com.trax.services.venue;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.trax.models.Venue;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/19/13
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VenueService {

    public Venue saveVenue(Venue venue);
    public Venue getVenue(Long id);
    public Venue saveVenue(String json);
    public Venue saveVenue(JsonElement json);
    public Set saveVenues(String json);
    public Set saveVenues(JsonElement json);
    public JsonDeserializer<Venue> getVenueJsonDeserializer();
    public JsonDeserializer<Set<Venue>> getVenuesJsonDeserializer();
    public void deleteVenue(Long id);
    public List<Venue> getVenues();
}
