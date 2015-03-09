package com.talentroc.t5.interview.pages.contact;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;

import com.talentroc.t5.interview.entities.Contact;
import com.talentroc.t5.interview.services.ContactManager;
import com.talentroc.t5.interview.utils.BusinessException;

public class ContactIndex {
    @Inject
    private ContactManager contactManager;

    @Property
    private Contact contact;

    public List<Contact> getContacts() {
        return contactManager.retrieveAll();
    }
    
   
    
    void onActionFromDelete(Contact c) throws BusinessException{
    	contactManager.delete(c);
    }
    
}
