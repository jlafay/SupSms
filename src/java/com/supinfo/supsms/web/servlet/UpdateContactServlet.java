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
@WebServlet(name = "UpdateContactServlet", urlPatterns = {"/premium/contact/update"})
public class UpdateContactServlet extends HttpServlet {
    
    @EJB
    private ContactService contactService;
    
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringContactId = req.getParameter("id");
        req.getSession().setAttribute("contactId", stringContactId);
        long contactId;
		
        try {
            contactId = Long.valueOf(stringContactId);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/premium/contacts");
            return;
        }
        
        Contact contact = contactService.findContactById(contactId);
        
        req.setAttribute("contact", contact);
        req.getRequestDispatcher("/jsp/premium/edit-contact.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean errors = false;
        
        String sessionContactId = (String) req.getSession().getAttribute("contactId");
        long contactId;

        try {
            contactId = Long.valueOf(sessionContactId);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/premium/contacts");
            return;
        }
 
        String phoneNumber = req.getParameter("phoneNumber");
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String postalAddress = req.getParameter("postalAddress");
        Customer contactToAdd = null;

        if(!phoneNumber.isEmpty()) {
            contactToAdd = customerService.findCustomerByPhoneNumber(phoneNumber);
        }else{
            errors = true;
        }

        if (contactToAdd == null || phoneNumber == null || phoneNumber.trim().isEmpty() || firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() || email == null || email.trim().isEmpty() || postalAddress == null || postalAddress.trim().isEmpty()) {
            errors = true;
        }

        if (errors) {
            Contact contact = contactService.findContactById(contactId);

            req.setAttribute("contact", contact);
            req.setAttribute("errors", true);
            req.getRequestDispatcher("/jsp/premium/edit-contact.jsp").forward(req, resp);
        } else {

            Contact contact = contactService.findContactById(contactId);
            contact.setFirstName(req.getParameter("firstName"));
            contact.setLastName(req.getParameter("lastName"));
            contact.setEmailAddress(req.getParameter("email"));
            contact.setPhoneNumber(req.getParameter("phoneNumber"));
            contact.setPostalAddress(req.getParameter("postalAddress"));
            Date date = new Date();
            contact.setUpdateDate(date);

            contactService.updateContact(contact);

            resp.sendRedirect(req.getContextPath() + "/premium/contacts");
        }
    }
}
