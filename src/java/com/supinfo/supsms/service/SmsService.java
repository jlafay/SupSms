/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.service;

import com.supinfo.supsms.dao.SmsDao;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.entity.Sms;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Ju
 */
@Stateless
public class SmsService {
    
    @EJB
    private SmsDao smsDao;
    
    public void addSms(Sms sms) {
        smsDao.addSms(sms);
    }
    
    public List<Sms> findSmssBySender(Customer customer){
        return smsDao.findSmssBySender(customer);
    }
    
    public List<Sms> findSmssByReceiver(Customer customer){
        return smsDao.findSmssByReceiver(customer);
    }
    
    public void deleteSms(Sms sms){
        smsDao.deleteSms(sms);
    }
    
    public long countSmss() {
        return smsDao.countSmss();
    }
}
