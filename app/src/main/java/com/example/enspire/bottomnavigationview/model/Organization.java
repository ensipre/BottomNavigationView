package com.example.enspire.bottomnavigationview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by enspire on 9/12/2017.
 */

public class Organization {

    @SerializedName("id")
    private Integer id;

    @SerializedName("swcno")
    private Integer swcno;

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private Integer phone;

    @SerializedName("contact")
    private String contact;

    @SerializedName("email")
    private String email;

    @SerializedName("description")
    private String description;

    @SerializedName("category")
    private String category;

    @SerializedName("website")
    private String website;

    @SerializedName("affiliation")
    private String affiliation;

    public Integer getId() {
        return id;
    }

    public Integer getSwcno() {
        return swcno;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPhone() {
        return phone;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getWebsite() {
        return website;
    }

    public String getAffiilation() {
        return affiliation;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSwcno(Integer swcno) {
        this.swcno = swcno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAffiliation(String affiliation) {this.affiliation = affiliation;}
}
