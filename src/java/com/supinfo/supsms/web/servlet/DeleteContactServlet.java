/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.service.ContactService;
import com.supinfo.supsms.service.CustomerService;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ju
 */
@WebServlet(name = "DeleteContactServlet", urlPatterns = {"/premium/contact/delete"})
public class DeleteContactServlet extends HttpServlet {
    
    @EJB
    private ContactService contactService;
    
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringContactId = req.getParameter("id");
        long contactId;
		
        try {
            contactId = Long.valueOf(stringContactId);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/premium/contacts");
            return;
        }
        long customerId = (Long) req.getSession().getAttribute("customerId");
        
        Contact contact = contactService.findContactById(contactId);
        Customer customer = customerService.findCustomerById(customerId);
        List<Conversation> conversations = customer.getConversations();
        Conversation conversationToDelete = null;
        
        for(Conversation conversation : conversations){
            for(Customer c : conversation.getCustomers()){
                if(c.getPhoneNumber().equals(contact.getPhoneNumber())){
                    conversationToDelete = conversation;
                }
            }  
        }
        
        if(conversationToDelete != null){
            conversations.remove(conversationToDelete);
            customer.setConversations(conversations);
            customerService.updateCustomer(customer);
        }
        
        customerService.deleteToContactList(customerId, contactId);
        contactService.deleteContact(contactId);
        
        resp.sendRedirect(req.getContextPath() + "/premium/contacts");
    }
}
