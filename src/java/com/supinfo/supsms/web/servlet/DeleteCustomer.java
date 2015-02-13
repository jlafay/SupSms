/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.entity.Sms;
import com.supinfo.supsms.service.ConversationService;
import com.supinfo.supsms.service.CustomerService;
import com.supinfo.supsms.service.SmsService;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeleteCustomer", urlPatterns = {"/admin/customer/delete"})
public class DeleteCustomer extends HttpServlet {
    
    @EJB
    private CustomerService customerService; 
    
    @EJB
    private ConversationService conversationService; 
    
    @EJB
    private SmsService smsService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringCustomerId = req.getParameter("id");
        long customerId;
		
        try {
            customerId = Long.valueOf(stringCustomerId);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/premium/conversations");
            return;
        }
        
        Customer customer = customerService.findCustomerById(customerId);
        List<Conversation> conversations = conversationService.getConversations();
        for(Conversation conversation : conversations){
            List<Customer> customers = conversation.getCustomers();
            for(Customer customerToTest : customers){
                if(customerToTest.getPhoneNumber().equals(customer.getPhoneNumber())){
                    for(Customer customerToTestForPersonal : customers){
                        if(!customerToTestForPersonal.getPhoneNumber().equals(customer.getPhoneNumber())){
                            List<Conversation> personalConversations = customerToTestForPersonal.getConversations();
                            personalConversations.remove(conversation);
                            customerToTestForPersonal.setConversations(personalConversations);
                            customerService.updateCustomer(customerToTestForPersonal);
                        }
                    }
                }
                conversationService.deleteConversation(conversation);
            }
        }

        List<Sms> smsToDeleteFromTheSender = smsService.findSmssBySender(customer);
        for(Sms sms : smsToDeleteFromTheSender){
            smsService.deleteSms(sms);
        }
        
        List<Sms> smsToDeleteForTheReceiver = smsService.findSmssByReceiver(customer);
        for(Sms sms : smsToDeleteForTheReceiver){
            
            customer.setConversations(null);
            customerService.updateCustomer(customer);
            smsService.deleteSms(sms);
        }
        
        customerService.deleteCustomer(customer);
        
        resp.sendRedirect(req.getContextPath() + "/admin/customers");
    }
}
