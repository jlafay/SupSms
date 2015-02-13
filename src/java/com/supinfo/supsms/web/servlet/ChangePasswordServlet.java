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
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/auth/password/update"})
public class ChangePasswordServlet extends HttpServlet {
    
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.getRequestDispatcher("/jsp/auth/change-password.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean errors = false;
                
        String oldPassword = req.getParameter("oldPassword");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        
        long customerId = (Long) req.getSession().getAttribute("customerId");

        Customer customer = customerService.findCustomerById(customerId);
        
        if (oldPassword == null || oldPassword.trim().isEmpty() || password == null || password.trim().isEmpty() || password2 == null || password2.trim().isEmpty() || !password.equals(password2) || !oldPassword.equals(customer.getPassword()) ) {
            errors = true;
        }

        if (errors) {
            req.setAttribute("errors", true);
            req.getRequestDispatcher("/jsp/auth/change-password.jsp").forward(req, resp);
        } else {
            customer.setPassword(req.getParameter("password"));

            customerService.updateCustomer(customer);

            resp.sendRedirect(req.getContextPath() + "/auth/profile");
        }
    }
}
