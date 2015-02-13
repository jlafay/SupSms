/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao.jpa;

import com.supinfo.supsms.dao.ContactDao;
import com.supinfo.supsms.entity.Contact;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ju
 */
@Stateless
public class JpaContactDao implements ContactDao{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addContact(Contact contact) {
        em.persist(contact);
    }
    
    @Override
    public Contact findContactById(Long contactId) {
        return em.find(Contact.class, contactId);
    }
    
    @Override
    public void updateContact(Contact contact) {
        em.merge(contact);
    }
    
    @Override
    public void deleteContact(Contact contact) {
        em.remove(contact);
    }
}
