/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.service.ContactService;
import com.supinfo.supsms.service.CustomerService;
import java.io.IOException;
import java.util.Date;
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
@WebServlet(name = "AddContactServlet", urlPatterns = {"/premium/contact/add"})
public class AddContactServlet extends HttpServlet {
    
    @EJB
    private ContactService contactService;
    
    @EJB
    private CustomerService customerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        boolean errors = false;
        
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String postalAddress = req.getParameter("postalAddress");
        Customer contactToAdd = null;
        
        if(!phoneNumber.isEmpty()) {
            contactToAdd = customerService.findCustomerByPhoneNumber(phoneNumber);
        }else{
            req.setAttribute("errors", true);
            req.getRequestDispatcher("/jsp/premium/add-contact.jsp").forward(req, resp);
        }
        
        if (contactToAdd == null || firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() || email == null || email.trim().isEmpty() || phoneNumber == null || phoneNumber.trim().isEmpty() || postalAddress == null || postalAddress.trim().isEmpty()) {
            errors = true;
        }
        
        if(errors){
            req.setAttribute("errors", true);
	    req.getRequestDispatcher("/jsp/premium/add-contact.jsp").forward(req, resp);
        }else {
            Contact contact = new Contact();
            contact.setFirstName(req.getParameter("firstName"));
            contact.setLastName(req.getParameter("lastName"));
            contact.setEmailAddress(req.getParameter("email"));
            contact.setPhoneNumber(req.getParameter("phoneNumber"));
            contact.setPostalAddress(req.getParameter("postalAddress"));
            Date date = new Date();
            contact.setUpdateDate(date);
            
            contactService.addContact(contact);
            
            long customerId = (Long) req.getSession().getAttribute("customerId");
            customerService.addToContactList(customerId, contact);
            
            resp.sendRedirect(req.getContextPath() + "/premium/contacts");
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/premium/add-contact.jsp").forward(req, resp);
    }
}
