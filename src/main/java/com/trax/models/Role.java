package com.trax.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="swag")
public class Role {

    @Id
    @Expose
    @GeneratedValue(generator = "Role_SequenceStyleGenerator")
    @GenericGenerator(name = "Role_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "role_seq"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1") }
    )

    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;


    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.replaceAll("(\\s+|\\t+|\\r+|\\n+)", "_").toUpperCase();
    }


    public String toString(){
        return this.name;
    }

}
