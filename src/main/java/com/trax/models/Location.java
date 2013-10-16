package com.trax.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/15/13
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Location {

    @Id
    @GeneratedValue(generator = "Contact_SequenceStyleGenerator")
    @GenericGenerator(name = "Contact_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "location_seq"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1") }
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;

    @Column(name="name")
    private String name;

    @Column(name="address_line_1")
    private String addressLine1;

    @Column(name="address_line_2")
    private String addressLine2;

    @Column(name="city")
    private String city;

    @Column(name="region_code")
    private String regionCode;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="country_code")
    private String countryCode;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public Owner getOwner(){
        return this.owner;
    }

    public void setOwner(Owner owner){
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1(){
        return this.addressLine1;
    }

    public void setAddressLine1(String addressLine1){
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2(){
        return this.addressLine2;
    }

    public void setAddressLine2(String addressLine2){
        this.addressLine2 = addressLine2;
    }

    public String getCity(){
        return this.city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getRegionCode(){
        return this.regionCode;
    }

    public void setRegionCode(String regionCode){
        this.regionCode = regionCode;
    }

    public String getPostalCode(){
        return this.postalCode;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public String getCountryCode(){
        return this.countryCode;
    }

    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }

}
