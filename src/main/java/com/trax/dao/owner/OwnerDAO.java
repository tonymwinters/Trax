package com.trax.dao.owner;

import com.trax.models.Owner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OwnerDAO {

    public void saveOwner(Owner owner);
    public Owner getOwner(Long id);
    public void deleteOwner(Long id);
    public List<Owner> getOwners();
}
