package com.trax.dao.contact;

import com.trax.models.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/14/13
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ContactDAOImpl {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addContact(Contact contact){
        getCurrentSession().save(contact);
    }

    public void updateContact(Contact contact){
        Contact contactToUpdate = getContact(contact.getId());
        //todo update contact fields
        getCurrentSession().update(contact);

    }

    public Contact getContact(int id){
        return (Contact) getCurrentSession().get(Contact.class, id);
    }

    public void deleteContact(int id){
        Contact contact = getContact(id);
        if(contact != null)
            getCurrentSession().delete(contact);
    }

    public List<Contact> getContacts(){
        return getCurrentSession().createQuery("from Contact").list();
    }
}
