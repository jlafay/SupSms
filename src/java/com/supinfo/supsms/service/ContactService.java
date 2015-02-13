/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.service;

import com.supinfo.supsms.dao.ContactDao;
import com.supinfo.supsms.entity.Contact;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Ju
 */
@Stateless
public class ContactService {
    
    @EJB
    private ContactDao contactDao;
    
    public void addContact(Contact contact) {
        contactDao.addContact(contact);
    }
    
    public Contact findContactById(Long contactId) {
        return contactDao.findContactById(contactId);
    }
    
    public void updateContact(Contact contact) {
        contactDao.updateContact(contact);
    }
    
    public void deleteContact(Long contactId) {
        contactDao.deleteContact(findContactById(contactId));
    }
}
