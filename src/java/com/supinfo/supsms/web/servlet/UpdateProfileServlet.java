/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.service.CustomerService;
import java.io.IOException;
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
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/auth/profile/update"})
public class UpdateProfileServlet extends HttpServlet {
    
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        long customerId = (Long) req.getSession().getAttribute("customerId");
        Customer customer = customerService.findCustomerById(customerId);
        req.setAttribute("customer", customer);
        
        req.getRequestDispatcher("/jsp/auth/edit-profile.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean errors = false;
                
        String phoneNumber = req.getParameter("phoneNumber");
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String creditCard = req.getParameter("creditCard");
        
        long customerId = (Long) req.getSession().getAttribute("customerId");
        Customer customer = customerService.findCustomerById(customerId);
        
        if (phoneNumber == null || phoneNumber.trim().isEmpty() || firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() || email == null || email.trim().isEmpty() || creditCard == null || creditCard.trim().isEmpty()) {
            errors = true;
        }

        if (errors) {
            req.setAttribute("customer", customer);
            req.setAttribute("errors", true);
            req.getRequestDispatcher("/jsp/auth/edit-profile.jsp").forward(req, resp);
        } else {
            customer.setFirstName(req.getParameter("firstName"));
            customer.setLastName(req.getParameter("lastName"));
            customer.setEmailAddress(req.getParameter("email"));
            customer.setPhoneNumber(req.getParameter("phoneNumber"));
            customer.setCreditCard(req.getParameter("creditCard"));

            customerService.updateCustomer(customer);

            resp.sendRedirect(req.getContextPath() + "/auth/profile");
        }
    }

}
