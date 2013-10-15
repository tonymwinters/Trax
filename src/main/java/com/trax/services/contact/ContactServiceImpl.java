package com.trax.services.contact;

import com.trax.dao.contact.ContactDAO;
import com.trax.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ContactServiceImpl {

    @Autowired
    private ContactDAO contactDAO;

    public void addContact(Contact contact){
        contactDAO.addContact(contact);
    }

    public void updateContact(Contact contact){
        contactDAO.updateContact(contact);
    }

    public Contact getContact(int id){
        return contactDAO.getContact(id);
    }

    public void deleteContact(int id){
        contactDAO.deleteContact(id);
    }

    public List<Contact> getContact(){
        return contactDAO.getContacts();
    }

}
