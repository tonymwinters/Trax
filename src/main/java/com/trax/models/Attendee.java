package com.trax.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/24/13
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="public.attendee")
public class Attendee {

    @Id
    @Expose
    @GeneratedValue(generator = "Attendee_SequenceStyleGenerator")
    @GenericGenerator(name = "Attendee_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "attendee_seq"),
                    @Parameter(name = "optimizer", value = "hilo"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1") }
    )
    private Long id;

    @Expose
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="session_id")
    private Session session;

    @Expose
    @Column(name="arrival")
    private Date arrival;

    @Expose
    @Column(name="is_owner")
    private Boolean isOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Session getSession(){
        return this.session;
    }

    public void setSession(Session session){
        this.session = session;
    }

    public Boolean isOwner(){
        return this.isOwner;
    }

    public void setIsOwner(Boolean isOwner){
        this.isOwner = isOwner;
    }

    public Date getArrival(){
        return this.arrival;
    }

    public void setArrival(Date arrival){
        this.arrival = arrival;
    }
}
