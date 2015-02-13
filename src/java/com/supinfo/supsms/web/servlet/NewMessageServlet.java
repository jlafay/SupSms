/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.entity.Sms;
import com.supinfo.supsms.service.ConversationService;
import com.supinfo.supsms.service.CustomerService;
import com.supinfo.supsms.service.SmsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
@WebServlet(name = "NewMessageServlet", urlPatterns = {"/premium/new-message"})
public class NewMessageServlet extends HttpServlet {
    
    @EJB
    private CustomerService customerService;
    
    @EJB
    private ConversationService conversationService;
    
    @EJB
    private SmsService smsService;
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        long customerId = (Long) req.getSession().getAttribute("customerId");
        Customer customer = customerService.findCustomerById(customerId);
        List<Contact> contacts = customer.getContacts();
        req.setAttribute("contactList", contacts);
        req.getRequestDispatcher("/jsp/premium/new-message.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String phoneNumber = req.getParameter("phoneNumber");
        String content = req.getParameter("content");
        String contactPhoneNumber = req.getParameter("contactSelected");
        Customer receiver = null;
        
        if(!phoneNumber.isEmpty() && !contactPhoneNumber.isEmpty()){
            req.setAttribute("errors", true);
            req.getRequestDispatcher("/jsp/premium/new-message.jsp").forward(req, resp);
        }else if(!phoneNumber.isEmpty()) {
            receiver = customerService.findCustomerByPhoneNumber(phoneNumber);
        }else if (!contactPhoneNumber.isEmpty()){
            receiver = customerService.findCustomerByPhoneNumber(contactPhoneNumber);
        }else{
            req.setAttribute("errors", true);
            req.getRequestDispatcher("/jsp/premium/new-message.jsp").forward(req, resp);
        }
        
        if(receiver != null){
            if (content == null) {
                req.setAttribute("errors", true);
                req.getRequestDispatcher("/jsp/premium/new-message.jsp").forward(req, resp);
            }else { 
                long customerId = (Long) req.getSession().getAttribute("customerId");
                Customer sender = customerService.findCustomerById(customerId);
                List<Conversation> senderConversations = sender.getConversations();
                Conversation initConversation = null;
                Boolean isConversationAlreadyExist = false;
                List<Customer> customerList = new ArrayList<Customer>();
                customerList.add(sender);
                customerList.add(receiver);
                
                for (Conversation conversation : senderConversations) {
                    List<Customer> customers = conversation.getCustomers();
                    for (Customer customer : customers) {
                        if (customer.getPhoneNumber() == receiver.getPhoneNumber()){
                            initConversation = conversation;
                            isConversationAlreadyExist = true;
                        }
                    }
                }
                
                if(isConversationAlreadyExist == false){
                    initConversation = new Conversation();
                    initConversation.setCustomers(customerList);
                    conversationService.addConversation(initConversation);
                }
                
                String jmsContent = sender.getPhoneNumber()+"\n\r"+content+"\n\r"+receiver.getPhoneNumber();
                jmsContent = content.toUpperCase();
                
                Sms sms = new Sms();
                sms.setContent(content);
                sms.setJmsContent(jmsContent);
                sms.setReceiver(receiver);
                sms.setSender(sender);
                Date date = new Date();
                sms.setSentDate(date);
                smsService.addSms(sms);
                List<Sms> smsList = initConversation.getSmss();
                if(smsList == null){
                    smsList = new ArrayList<Sms>();
                }
                smsList.add(sms);
                initConversation.setSmss(smsList);
                initConversation.setDateUpdated(date);
                conversationService.updateConversation(initConversation);
                
                if(isConversationAlreadyExist == false){
                    senderConversations.add(initConversation);
                    sender.setConversations(senderConversations);
                    customerService.updateCustomer(sender);
                    
                    List<Conversation> receiverConversations = receiver.getConversations();
                    receiverConversations.add(initConversation);
                    receiver.setConversations(senderConversations);
                    customerService.updateCustomer(receiver);
                }
                
                resp.sendRedirect(req.getContextPath() + "/premium/messages");
            }
        }else{
            req.setAttribute("errors", true);
            req.getRequestDispatcher("/jsp/premium/new-message.jsp").forward(req, resp);
        }
    }

}
