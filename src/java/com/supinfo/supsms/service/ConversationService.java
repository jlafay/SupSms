/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.service;

import com.supinfo.supsms.dao.ConversationDao;
import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Ju
 */
@Stateless
public class ConversationService {
    
    @EJB
    private ConversationDao conversationDao;
    
    public void addConversation(Conversation conversation) {
        conversationDao.addConversation(conversation);
    }
    
    public void updateConversation(Conversation conversation) {
        conversationDao.updateConversation(conversation);
    }
    
    public Conversation findConversationById(Long conversationId) {
        return conversationDao.findConversationById(conversationId);
    } 
    
    public List<Conversation> getConversations(){
        return conversationDao.getConversations();
    }
    
    public void deleteConversation(Conversation conversation){
        conversationDao.deleteConversation(conversation);
    }
}
