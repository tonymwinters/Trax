package com.trax.models;

import com.google.gson.annotations.Expose;
import com.trax.services.session.SessionService;
import com.trax.services.venue.VenueService;
import com.trax.utilities.Alfred;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;
import com.google.gson.annotations.SerializedName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/19/13
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="public.session")
public class Session {

    @Id
    @Expose
    @GeneratedValue(generator = "Session_SequenceStyleGenerator")
    @GenericGenerator(name = "Session_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "session_seq"),
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

    @Expose
    @SerializedName("startTime")
    @Column(name="start_time")
    private Date startTime;

    @Expose
    @SerializedName("endTime")
    @Column(name="end_time")
    private Date endTime;

    @ManyToOne
    @JoinColumn(name="venue_id")
    private Venue venue;

    @Expose
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @Expose
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "session", cascade = CascadeType.ALL)
    private Set<Attendee> attendees = new HashSet<Attendee>();

    @Expose
    @Column(name="capacity")
    private Integer capacity;

    @Expose
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "session", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<Comment>();

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

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Venue getVenue(){
        return this.venue;
    }

    public void setVenue(Venue venue){
        this.venue = venue;
    }

    public Room getRoom(){
        return this.room;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    public Set<Attendee> getAttendeess() {
        return this.attendees;
    }

    public void setAttendees(Set attendees){
        this.attendees = attendees;
    }

    public void setCapacity(Integer capacity){
        this.capacity = capacity;
    }

    public Integer getCapacity(){
        return this.capacity;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set comments){
        this.comments = comments;
    }
}
