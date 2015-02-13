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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");
        Customer customer = null;
        
        if(!phoneNumber.isEmpty()) {
            customer = customerService.findCustomerByPhoneNumber(phoneNumber);
        }else{
            req.setAttribute("errors", true);
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
        
        if(customer != null){
            if (password == null || !password.equals(customer.getPassword())) {
                req.setAttribute("errors", true);
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            }else {
                req.getSession().setAttribute("customerId", customer.getId());
                req.getSession().setAttribute("firstName", customer.getFirstName());

                if(customer.getIsPremium() == true){
                    req.getSession().setAttribute("isPremium", customer.getIsPremium());
                }else{
                    req.getSession().setAttribute("isPremium", null);
                }
                if(customer.getIsAdmin() == true){
                    req.getSession().setAttribute("isAdmin", customer.getIsAdmin());
                }else{
                    req.getSession().setAttribute("isAdmin", null);
                }
                
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        }else{
            req.setAttribute("errors", true);
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }
}
