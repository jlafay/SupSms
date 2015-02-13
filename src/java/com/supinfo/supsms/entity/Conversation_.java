/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author Ju
 */
@StaticMetamodel(Conversation.class)
public class Conversation_ {
    public static volatile SingularAttribute<Conversation, Long> id;
    public static volatile ListAttribute<Conversation, List<Customer>> customers;
    public static volatile ListAttribute<Conversation, List<Sms>> smss;
    public static volatile SingularAttribute<Conversation, Date> dateUpdated;
}
