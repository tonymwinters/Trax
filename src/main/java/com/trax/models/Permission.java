package com.trax.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 12/21/13
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="permission")
public class Permission {

    @Id
    @Expose
    @GeneratedValue(generator = "Permission_SequenceStyleGenerator")
    @GenericGenerator(name = "Permission_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "permission_seq"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1") }
    )
    private Long id;

    @Expose
    @Column(name="name")
    private String name;

    @Expose
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
