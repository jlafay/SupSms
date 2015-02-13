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
@StaticMetamodel(Sms.class)
public class Sms_ {
    public static volatile SingularAttribute<Sms, Long> id;
    public static volatile SingularAttribute<Sms, Customer> sender;
    public static volatile SingularAttribute<Sms, Customer> receiver;
    public static volatile SingularAttribute<Sms, String> content;
    public static volatile SingularAttribute<Sms, String> jmsContent;
    public static volatile SingularAttribute<Sms, Date> sentDate;
}
