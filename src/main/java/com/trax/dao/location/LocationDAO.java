package com.trax.dao.location;

import com.trax.models.Location;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/15/13
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LocationDAO {

    public void saveLocation(Location location);
    public Location getLocation(Long id);
    public void deleteLocation(Long id);
    public List<Location> getLocation();
}
