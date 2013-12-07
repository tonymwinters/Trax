package com.trax.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/23/13
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="public.room")
public class Room {
    @Id
    @Expose
    @GeneratedValue(generator = "Room_SequenceStyleGenerator")
    @GenericGenerator(name = "Room_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "room_seq"),
                    @Parameter(name = "optimizer", value = "hilo"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1") }
    )
    private Long id;

    @Expose
    @SerializedName("name")
    @Column(name="name")
    private String name;

    @Expose
    @SerializedName("description")
    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="venue_id", updatable = false)
    private Venue venue;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room")
    private Set<Session> sessions = new HashSet<Session>();

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Session> getSessions() {
        return this.sessions;
    }

    public Venue getVenue(){
        return this.venue;
    }

    public void setVenue(Venue venue){
        this.venue = venue;
    }
}
