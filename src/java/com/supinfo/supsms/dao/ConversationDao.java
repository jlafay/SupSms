/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao;

import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ju
 */
@Local
public interface ConversationDao {
    
    public void addConversation(Conversation conversation);
    public void updateConversation(Conversation conversation);
    public Conversation findConversationById(Long conversationId);
    public List<Conversation> getConversations();
    public void deleteConversation(Conversation conversation);
}
