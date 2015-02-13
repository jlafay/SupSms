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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @EJB
    private CustomerService customerService;
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        boolean errors = false;
        
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String creditCard = req.getParameter("creditCard");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        
        Customer customerAlreadyExist = null;
        
        if(phoneNumber != null && !phoneNumber.trim().isEmpty()){
            customerAlreadyExist = customerService.findCustomerByPhoneNumber(phoneNumber);
        }
        
        if(customerAlreadyExist != null){
            errors = true;
        }
        
        if (firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() || email == null || email.trim().isEmpty() || phoneNumber == null || phoneNumber.trim().isEmpty() || creditCard == null || creditCard.trim().isEmpty() || password == null || password.trim().isEmpty() || !password.equals(password2)) {
            errors = true;
        }
        
        if(errors){
            req.setAttribute("errors", true);
	    req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
        }else {
            Customer customer = new Customer();
            customer.setFirstName(req.getParameter("firstName"));
            customer.setLastName(req.getParameter("lastName"));
            customer.setEmailAddress(req.getParameter("email"));
            customer.setPhoneNumber(req.getParameter("phoneNumber"));
            customer.setCreditCard(req.getParameter("creditCard"));
            customer.setPassword(req.getParameter("password"));
            customer.setIsPremium(false);
            customer.setIsAdmin(false);
            
            customerService.addCustomer(customer);
            
            req.getSession().setAttribute("customerId", customer.getId());
            req.getSession().setAttribute("firstName", customer.getFirstName());
            req.getSession().setAttribute("isPremium", null);
            req.getSession().setAttribute("isAdmin", null);
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}
