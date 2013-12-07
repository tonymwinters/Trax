package com.trax.services.owner;

import com.google.gson.JsonDeserializer;
import com.trax.models.Owner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OwnerService {

    public void addOwner(Owner owner);
    public void updateOwner(Owner owner);
    public Owner getOwner(Long id);
    public Owner deserializeOwner(String json);
    public JsonDeserializer<Owner> getOwnerJsonDeserializer();
    public void deleteOwner(Long id);
    public List<Owner> getOwners();

}
