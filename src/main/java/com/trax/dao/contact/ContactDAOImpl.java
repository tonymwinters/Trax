package com.trax.dao.contact;

import com.trax.models.Contact;
import com.trax.utilities.Alfred;
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
public class ContactDAOImpl implements ContactDAO{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveContact(Contact contact){
        getCurrentSession().merge(contact);
        getCurrentSession().saveOrUpdate(contact);
    }

    public Contact getContact(Long id){
        return (Contact) getCurrentSession().get(Contact.class, id);
    }

    public void deleteContact(Long id){
        Contact contact = getContact(id);
        if(Alfred.notNull(contact))
            getCurrentSession().delete(contact);
    }

    public List<Contact> getContacts(){
        return getCurrentSession().createQuery("from Contact").list();
    }
}
