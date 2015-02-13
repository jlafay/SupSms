/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.entity.Customer;
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
@WebServlet(name = "ShowContactsServlet", urlPatterns = {"/premium/contacts"})
public class ShowContactsServlet extends HttpServlet {
    
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        long customerId = (Long) req.getSession().getAttribute("customerId");
        Customer customer = customerService.findCustomerById(customerId);
        List<Contact> contacts = customer.getContacts();
        req.setAttribute("contactList", contacts);
        
        req.getRequestDispatcher("/jsp/premium/contacts.jsp").forward(req, resp);
    }
}
