/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao;

import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ju
 */
@Local
public interface CustomerDao {
    
    public void addCustomer(Customer customer);
    public Customer findCustomerByPhoneNumber(String phoneNumber);
    public Customer findCustomerById(Long customerId);
    public void addToContactList(Customer findCustomerById, Contact contact);
    public void updateCustomer(Customer customer);
    public void deleteToContactList(Customer findCustomerById, Contact findContactById);
    public void addToConversationList(Customer customer, Conversation conversation);
    public List<Customer> getCustomers();
    public void deleteCustomer(Customer customer);
    public long countCustomers();
}
