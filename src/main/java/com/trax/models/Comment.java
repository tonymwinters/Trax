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
 * Date: 11/29/13
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="public.comment")
public class Comment {

    @Id
    @Expose
    @GeneratedValue(generator = "Comment_SequenceStyleGenerator")
    @GenericGenerator(name = "Comment_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "comment_seq"),
                    @Parameter(name = "optimizer", value = "hilo"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1") }
    )
    private Long id;

    @Expose
    @Column(name="content")
    private String content;

    @Expose
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Expose
    @SerializedName("dateCreated")
    @Column(name="date_created")
    private Date dateCreated;

    @Expose
    @SerializedName("lastUpdated")
    @Column(name="last_updated")
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name="session_id", updatable = false)
    private Session session;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Session getSession(){
        return this.session;
    }

    public void setSession(Session session){
        this.session = session;
    }
}
