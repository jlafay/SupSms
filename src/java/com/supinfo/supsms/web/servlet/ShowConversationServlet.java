/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.service.ConversationService;
import com.supinfo.supsms.service.CustomerService;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "ShowConversationServlet", urlPatterns = {"/premium/conversation/read"})
public class ShowConversationServlet extends HttpServlet {
    
    @EJB
    private ConversationService conversationService;
    
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringConversationId = req.getParameter("id");
        long conversationId;
		
        try {
            conversationId = Long.valueOf(stringConversationId);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/premium/conversations");
            return;
        }
        long customerId = (Long) req.getSession().getAttribute("customerId");
        
        Customer customer = customerService.findCustomerById(customerId);
        Conversation conversation = conversationService.findConversationById(conversationId);
        
        List<Contact> contacts = customer.getContacts();
        List<Customer> customers = conversation.getCustomers();
        for (Customer c : customers) {
            if (customerId != c.getId()){
                for (Contact contact : contacts){
                    if(contact.getPhoneNumber().equals(c.getPhoneNumber())){
                        c.setPhoneNumber(contact.getFirstName());
                    }
                }

                List<Customer> otherCustomer = new ArrayList<Customer>();
                otherCustomer.add(c);
                conversation.setCustomers(otherCustomer);
            }
        }
        
        
        
        req.setAttribute("convers", conversation);
        req.getRequestDispatcher("/jsp/premium/conversation.jsp").forward(req, resp);
    }
}
