package com.trax.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/15/13
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Venue {

    @Id
    @GeneratedValue(generator = "User_SequenceStyleGenerator")
    @GenericGenerator(name = "User_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "venue_seq"),
                    @Parameter(name = "optimizer", value = "hilo"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1") }
    )
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private Contact contact;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="location_id")
    private Location location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner(){
        return this.owner;
    }

    public void setOwner(Owner owner){
        this.owner = owner;
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
}
