/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "BecomePremiumServlet", urlPatterns = {"/auth/become-premium"})
public class BecomePremiumServlet extends HttpServlet {

   @EJB
   private CustomerService customerService;
   
   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        long customerId = (Long) req.getSession().getAttribute("customerId");
        Customer customer = customerService.findCustomerById(customerId);
        customer.setIsPremium(true);
        customerService.updateCustomer(customer);
        req.getSession().setAttribute("isPremium", customer.getIsPremium());
        resp.sendRedirect(req.getContextPath() + "/premium/contacts");
    }

}
