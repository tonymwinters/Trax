package com.trax.services.owner;

import java.util.List;

import com.trax.dao.owner.OwnerDAO;
import com.trax.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */


@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerDAO ownerDAO;

    public void addOwner(Owner owner) {
        ownerDAO.addOwner(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDAO.updateOwner(owner);
    }

    public Owner getOwner(int id) {
        return ownerDAO.getOwner(id);
    }

    public void deleteOwner(int id) {
        ownerDAO.deleteOwner(id);
    }

    public List<Owner> getOwners() {
        return ownerDAO.getOwners();
    }

}
