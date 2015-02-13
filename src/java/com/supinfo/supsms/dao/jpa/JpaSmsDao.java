/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.dao.jpa;

import com.supinfo.supsms.dao.SmsDao;
import com.supinfo.supsms.entity.Customer;
import com.supinfo.supsms.entity.Sms;
import com.supinfo.supsms.entity.Sms_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class JpaSmsDao implements SmsDao{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addSms(Sms sms) {
        em.persist(sms);
    }
    
    @Override
    public List<Sms> getSmss() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sms> query = cb.createQuery(Sms.class);
        return em.createQuery(query).getResultList();
    }
    
    @Override
    public List<Sms> findSmssBySender(Customer customer) {
        try{    
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Sms> q = cb.createQuery(Sms.class);
            Root<Sms> sms = q.from(Sms.class);

            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(cb.equal(sms.get(Sms_.sender), customer));

            q.where((javax.persistence.criteria.Predicate[]) predicates.toArray(new Predicate[predicates.size()]));

            return em.createQuery(q).getResultList();
        } catch (NoResultException e) {
            List<Sms> smss = null;
            return smss;
	}
    }
    
    @Override
    public List<Sms> findSmssByReceiver(Customer customer) {
        try{    
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Sms> q = cb.createQuery(Sms.class);
            Root<Sms> sms = q.from(Sms.class);

            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(cb.equal(sms.get(Sms_.receiver), customer));

            q.where((javax.persistence.criteria.Predicate[]) predicates.toArray(new Predicate[predicates.size()]));

            return em.createQuery(q).getResultList();
        } catch (NoResultException e) {
            List<Sms> smss = null;
            return smss;
	}
    }
    
    @Override
    public void deleteSms(Sms sms){
        if (em.contains(sms)){
            em.remove(sms);
        }else{
            em.remove(em.merge(sms));
        }
    }
    
    @Override
    public long countSmss(){
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Sms.class)));
        return em.createQuery(cq).getSingleResult();
    }
}
