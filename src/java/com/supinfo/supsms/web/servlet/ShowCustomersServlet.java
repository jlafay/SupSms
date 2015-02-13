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
@WebServlet(name = "ShowCustomersServlet", urlPatterns = {"/admin/customers"})
public class ShowCustomersServlet extends HttpServlet {

    @EJB
    private CustomerService customerService;
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Customer> customers = customerService.getCustomers();
        req.setAttribute("custo", customers);
        req.getRequestDispatcher("/jsp/admin/customers.jsp").forward(req, resp);
    }
}
