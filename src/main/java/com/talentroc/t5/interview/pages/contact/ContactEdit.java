package com.talentroc.t5.interview.pages.contact;


import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;

import com.talentroc.t5.interview.entities.Contact;
import com.talentroc.t5.interview.services.ContactManager;
import com.talentroc.t5.interview.utils.BusinessException;

public class ContactEdit {

    @Inject
    private ContactManager contactManager;
	
    @Property
    private Contact contact;
    
    private Long id;

    void onActivate() {
        contact = new Contact();
    }

    Boolean onActivate(Contact contact) {
        this.contact = contact;
        this.id = contact.getId();
       // System.out.println(this.id);
        return Boolean.TRUE;
    }
    
  
    Object onSuccess() throws BusinessException{
    	//Problème : contact.getId() => null
    	//			this.id ==> null
    	//System.out.println(this.id);
    	try {
    		contactManager.create(contact);
    			//contactManager.update(contact);

    	} catch (BusinessException e) {
    		
		e.printStackTrace();
    	}
		return ContactIndex.class;
    	
    	
    }
}
