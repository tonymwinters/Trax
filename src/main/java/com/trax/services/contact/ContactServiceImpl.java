package com.trax.services.contact;

import com.trax.dao.contact.ContactDAO;
import com.trax.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactDAO contactDAO;

    public Contact saveContact(Contact contact){
        contactDAO.saveContact(contact);
        return contact;
    }

    public Contact getContact(Long id){
        return contactDAO.getContact(id);
    }

    public void deleteContact(Long id){
        contactDAO.deleteContact(id);
    }

    public List<Contact> getContacts(){
        return contactDAO.getContacts();
    }

}
