package com.trax.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Parameter;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="contact")
public class Contact {


    @Id
    @Expose
    @GeneratedValue(generator = "Contact_SequenceStyleGenerator")
    @GenericGenerator(name = "Contact_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "contact_seq"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1") }
    )

    private Long id;

    @Expose
    @Column(name="name")
    private String name;

    @Expose
    @Column(name="phone_number")
    private String phoneNumber;

    @Expose
    @Column(name="email_address")
    private String emailAddress;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
