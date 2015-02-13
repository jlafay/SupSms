/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ShowMessagesServlet", urlPatterns = {"/premium/messages"})
public class ShowMessagesServlet extends HttpServlet {

    @EJB
    private CustomerService customerService;
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        long customerId = (Long) req.getSession().getAttribute("customerId");
        Customer owner = customerService.findCustomerById(customerId);
        List<Conversation> conversations = owner.getConversations();
        List<Contact> contacts = owner.getContacts();
        List<Integer> numberOfSentMessage = new ArrayList<Integer>();
        
        for (Conversation conversation : conversations) {
            List<Customer> customers = conversation.getCustomers();
            for (Customer customer : customers) {
                if (customerId != customer.getId()){
                    for (Contact contact : contacts){
                        if(contact.getPhoneNumber().equals(customer.getPhoneNumber())){
                            customer.setPhoneNumber(contact.getFirstName());
                        }
                    }
                    
                    List<Customer> otherCustomer = new ArrayList<Customer>();
                    otherCustomer.add(customer);
                    conversation.setCustomers(otherCustomer);
                }
            }
            int countSentMessage = conversation.getSmss().size();
            numberOfSentMessage.add(countSentMessage);
        }
        req.setAttribute("conversations", conversations);
        req.setAttribute("customerId", customerId);
        req.setAttribute("numberOfSentMessage", numberOfSentMessage);
        req.getRequestDispatcher("/jsp/premium/messages.jsp").forward(req, resp);
    }

}
