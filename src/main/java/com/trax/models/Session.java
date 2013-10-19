package com.trax.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/19/13
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
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

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="start_time")
    private Timestamp startTime;

    @Column(name="end_time")
    private Timestamp endTime;

    @ManyToOne
    @JoinColumn(name="venue_id")
    private Venue venue;

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

    public Timestamp getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
