/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.service.CustomerService;
import com.supinfo.supsms.service.SmsService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ju
 */
@Stateless
@WebServlet(name = "CustomerStatServlet", urlPatterns = {"/home"})
public class CustomerStatServlet extends HttpServlet {
    
    @EJB
    private CustomerService customerService;  
    
    @EJB
    private SmsService smsService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long nbCustomer = customerService.countCustomers();
        long nbSentSms = smsService.countSmss();
        
        req.setAttribute("nbCustomer", nbCustomer);
        req.setAttribute("nbSentSms", nbSentSms);
        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }
}
