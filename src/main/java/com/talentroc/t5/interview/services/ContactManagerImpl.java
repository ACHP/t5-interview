package com.talentroc.t5.interview.services;

import com.talentroc.t5.interview.entities.Contact;
import com.talentroc.t5.interview.utils.BusinessException;

import org.apache.tapestry5.jpa.annotations.CommitAfter;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.regex.Pattern;

public class ContactManagerImpl implements ContactManager {

    private final EntityManager entityManager;

    public ContactManagerImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void validate(final Contact contact) throws BusinessException {
        // TODO - Ecrire le code de validation du contact.
    }

    @Override
    @CommitAfter
    public void create(final Contact contact) throws BusinessException {
    	
    	// Cette solution n'est pas elegante mais pas le choix si on veut afficher les bonnes exceptions .
    	if (contact.getId() == null) { 
    		if(contact.getLastName()!=null){
    			if((contact.getLastName().length()>=3)&&(contact.getLastName().length()<=50)){
    				if(contact.getTelephone()!=null){
    					if((contact.getTelephone().length()==10)&&(contact.getTelephone().matches("^[0-9]+$"))){
    						if(contact.getFirstName()!=null){
    							if(contact.getLastName().length()>50){
    								throw new BusinessException("The firstname of this contact is too long");
    							}
    						}
    						validate(contact);
    						entityManager.persist(contact);
            			}else{
            				throw new BusinessException("The telephone number is not valid, it may be too long or contains chars");
            			}
        			}else{
        				throw new BusinessException("The telephone number do not exist");
        			}
    			}else{
    				throw new BusinessException("The lastname of this contact is too long or too short, it must contains between 3 and 50 chars");
    			}
    		}else{
    			throw new BusinessException("The lastname do not exist");
    		}
    	}else{
    		throw new BusinessException("This contact already exist in DB.");
    	}
    			
    }

    @Override
    @CommitAfter
    public void update(final Contact contact) throws BusinessException {
        if (contact.getId() == null) {
            throw new BusinessException("This contact does not exist in DB. Use create.");
        } else {
            validate(contact);
            entityManager.merge(contact);
        }
    }

    @Override
    @CommitAfter
    public void delete(final Contact contact) throws BusinessException {
        entityManager.remove(contact);
    }

    @Override
    public List<Contact> retrieveAll() {
        return entityManager.createNamedQuery(Contact.RETRIEVE_ALL).getResultList();
    }

    @Override
    public Contact retrieveById(final Long id) {
        return entityManager.find(Contact.class, id);
    }
}
