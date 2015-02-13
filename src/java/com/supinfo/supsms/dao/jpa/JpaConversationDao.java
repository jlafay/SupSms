/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao.jpa;

import com.supinfo.supsms.dao.ConversationDao;
import com.supinfo.supsms.entity.Conversation;
import com.supinfo.supsms.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Ju
 */
@Stateless
public class JpaConversationDao implements ConversationDao{
    @PersistenceContext
    public EntityManager em;
    
    @Override
    public void addConversation(Conversation conversation) {
        em.persist(conversation);
    }
    
    @Override
    public void updateConversation(Conversation conversation) {
        em.merge(conversation);
    }
    
    @Override
    public Conversation findConversationById(Long conversationId) {
        return em.find(Conversation.class, conversationId);
    }
    
    @Override
    public List<Conversation> getConversations() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Conversation> query = cb.createQuery(Conversation.class);
        return em.createQuery(query).getResultList();
    }
    
    @Override
    public void deleteConversation(Conversation conversation){
        if (em.contains(conversation)){
            em.remove(conversation);
        }else{
            em.remove(em.merge(conversation));
        }
    }
}
