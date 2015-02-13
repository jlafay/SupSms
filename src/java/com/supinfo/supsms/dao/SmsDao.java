/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao;

import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.entity.Sms;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ju
 */
@Local
public interface SmsDao {
    
    public void addSms(Sms sms);
    public List<Sms> getSmss();
    public List<Sms> findSmssBySender(Customer customer);
    public List<Sms> findSmssByReceiver(Customer customer);
    public void deleteSms(Sms sms);
    public long countSmss();
}
