/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supsms.entity;

import java.util.List;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author Ju
 */
@StaticMetamodel(Customer.class)
public class Customer_ {
    
    public static volatile SingularAttribute<Customer, Long> id;
    public static volatile SingularAttribute<Customer, String> firstName;
    public static volatile SingularAttribute<Customer, String> lastName;
    public static volatile SingularAttribute<Customer, String> phoneNumber;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile SingularAttribute<Customer, Boolean> isPremium;
    public static volatile SingularAttribute<Customer, Boolean> isAdmin;
    public static volatile SingularAttribute<Customer, String> creditCard;
    public static volatile SingularAttribute<Customer, String> emailAddress;
    public static volatile SingularAttribute<Customer, String> postalAddress;
    public static volatile ListAttribute<Customer, List<Contact>> contacts;
    public static volatile ListAttribute<Customer, List<Conversation>> conversations;
}

