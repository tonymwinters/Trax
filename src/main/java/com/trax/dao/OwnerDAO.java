package com.trax.dao;

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

    public void addOwner(Owner owner);
    public void updateOwner(Owner owner);
    public Owner getOwner(int id);
    public void deleteOwner(int id);
    public List getOwners();
}
