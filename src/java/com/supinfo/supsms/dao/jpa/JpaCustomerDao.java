/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao.jpa;

import com.supinfo.supsms.dao.CustomerDao;
import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.entity.Customer_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Ju
 */
@Stateless
public class JpaCustomerDao implements CustomerDao{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addCustomer(Customer customer) {
        em.persist(customer);
    }
    
    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        try{    
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
            Root<Customer> customer = q.from(Customer.class);

            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(cb.equal(customer.get(Customer_.phoneNumber), phoneNumber));

            q.where((javax.persistence.criteria.Predicate[]) predicates.toArray(new Predicate[predicates.size()]));

            return em.createQuery(q).getSingleResult();
        } catch (NoResultException e) {
            Customer customer = null;
            return customer;
	}
    }
    
    @Override
    public Customer findCustomerById(Long customerId) {
        return em.find(Customer.class, customerId);
    }
    
    @Override
    public void addToContactList(Customer customer, Contact contact) {
        List<Contact> contactList = customer.getContacts();
        contactList.add(contact);
        customer.setContacts(contactList);
        em.persist(customer);
    }
    
    @Override
    public void updateCustomer(Customer customer) {
        em.merge(customer);
    }
    
    @Override
    public void deleteToContactList(Customer customer, Contact contact) {
        List<Contact> contactList = customer.getContacts();
        contactList.remove(contact);
        customer.setContacts(contactList);
        em.persist(customer);
    }
    
    @Override
    public void addToConversationList(Customer customer, Conversation conversation) {
        List<Conversation> conversationList = customer.getConversations();
        conversationList.add(conversation);
        customer.setConversations(conversationList);
        em.persist(customer);
    }
    
    @Override
    public List<Customer> getCustomers() {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        return em.createQuery(query).getResultList();
    }
    
    @Override
    public void deleteCustomer(Customer customer){
        if (em.contains(customer)){
            em.remove(customer);
        }else{
            em.remove(em.merge(customer));
        }
    }
    
    @Override
    public long countCustomers(){
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Customer.class)));
        return em.createQuery(cq).getSingleResult();
    }
}
