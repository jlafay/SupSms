/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.service.ConversationService;
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
@WebServlet(name = "DeleteConversationServlet", urlPatterns = {"/premium/conversation/delete"})
public class DeleteConversationServlet extends HttpServlet {
    
    @EJB
    private CustomerService customerService;
    
    @EJB
    private ConversationService conversationService;
    
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
        List<Conversation> conversations = customer.getConversations();
        Conversation conversation = conversationService.findConversationById(conversationId);
        conversations.remove(conversation);
        customer.setConversations(conversations);
        customerService.updateCustomer(customer);
        
        resp.sendRedirect(req.getContextPath() + "/premium/contacts");
    }
}
