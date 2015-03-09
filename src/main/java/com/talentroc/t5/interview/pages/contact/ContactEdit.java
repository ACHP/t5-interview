package com.talentroc.t5.interview.pages.contact;


import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.talentroc.t5.interview.entities.Contact;
import com.talentroc.t5.interview.services.ContactManager;
import com.talentroc.t5.interview.utils.BusinessException;

public class ContactEdit {

    @Inject
    private ContactManager contactManager;
    
  
    @Property
    private Contact contact;
    

    void onActivate() {
        contact = new Contact();
    }
    
    Contact onPassivate() {
        return contact;
    }

    Boolean onActivate(Contact contact) {
        this.contact = contact;
        return Boolean.TRUE;
    }
    
  
     
    Object onSuccess() throws BusinessException{
    	
       	try {
       		if(contactManager.retrieveById(contact.getId())==null){
       			contactManager.create(contact);
       		}else{
       			contactManager.update(contact);
       		}   			

    	} catch (BusinessException e) {
    		
		e.printStackTrace();
    	}
		return ContactIndex.class;
    	
    	
    }
}
