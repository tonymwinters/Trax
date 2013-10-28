package com.trax.models;

import com.trax.services.session.SessionService;
import com.trax.services.venue.VenueService;
import com.trax.utilities.Alfred;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.google.gson.annotations.SerializedName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @GeneratedValue(generator = "User_SequenceStyleGenerator")
    @GenericGenerator(name = "User_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "session_seq"),
                    @Parameter(name = "optimizer", value = "hilo"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1") }
    )
    private Long id;

    @SerializedName("name")
    @Column(name="name")
    private String name;

    @SerializedName("description")
    @Column(name="description")
    private String description;

    @SerializedName("startTime")
    @Column(name="start_time")
    private Date startTime;

    @SerializedName("endTime")
    @Column(name="end_time")
    private Date endTime;

    @ManyToOne
    @JoinColumn(name="venue_id")
    private Venue venue;

    @Transient
    @SerializedName("venueId")
    private Long venueId;

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
        this.venueId = venue.getId();
    }

    public Long getVenueId(){
        if(Alfred.notNull(this.venueId)){
            return this.venueId;
        }
        return this.venue.getId();
    }

    public void setVenueId(Long venueId){
        this.venueId = venueId;

    }
}
