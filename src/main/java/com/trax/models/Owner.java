package com.trax.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */



@Entity
@Table(name="owner")
public class Owner {

    @Id
    @GeneratedValue(generator = "Owner_SequenceStyleGenerator")
    @GenericGenerator(name = "Owner_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "owner_seq"),
                    @Parameter(name = "optimizer", value = "hilo"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1") }
    )

    @Expose
    private Long id;

    @Expose
    @Column(name="name")
    private String name;

    @Expose
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private Contact contact;

    @Expose
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="location_id")
    private Location location;

    @Expose
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Venue> venues = new HashSet<Venue>();


    @Expose
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<User>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Venue> getVenues() {
        return this.venues;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public String toString(){
        return this.name;
    }

}
