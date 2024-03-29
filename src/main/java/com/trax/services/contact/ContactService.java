package com.trax.services.contact;

import com.trax.models.Contact;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/14/13
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ContactService {

    public Contact saveContact(Contact contact);
    public Contact getContact(Long id);
    public void deleteContact(Long id);
    public List<Contact> getContacts();

}
