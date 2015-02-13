/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao;

import com.supinfo.supsms.entity.Contact;
import javax.ejb.Local;

/**
 *
 * @author Ju
 */
@Local
public interface ContactDao {
    public void addContact(Contact contact);
    public Contact findContactById(Long contactId);
    public void updateContact(Contact contact);
    public void deleteContact(Contact findContactById);
}
