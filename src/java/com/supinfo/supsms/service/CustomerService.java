/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.service;

import com.supinfo.supsms.dao.CustomerDao;
import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Ju
 */
@Stateless
public class CustomerService {
    
    @EJB
    private CustomerDao customerDao;
    
    @EJB
    private ContactService contactService;
    
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }
    
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        return customerDao.findCustomerByPhoneNumber(phoneNumber);
    }
    
    public Customer findCustomerById(Long customerId) {
        return customerDao.findCustomerById(customerId);
    } 
    
    public void addToContactList(Long customerId, Contact contact) {
        customerDao.addToContactList(findCustomerById(customerId), contact);
    }
    
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }
    
    public void deleteToContactList(Long customerId, Long contactId) {
        customerDao.deleteToContactList(findCustomerById(customerId), contactService.findContactById(contactId));
    }
    
    public void addToConversationList(Customer customer, Conversation conversation) {
        customerDao.addToConversationList(customer, conversation);
    }
    
    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }
    
    public void deleteCustomer(Customer customer) {
        customerDao.deleteCustomer(customer);
    }
    
    public long countCustomers() {
        return customerDao.countCustomers();
    }
}
