package com.example.mobilestyx.retrofit;

/**
 * Created by mobilestyx on 24/08/16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class Actor {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("spouse")
    @Expose
    private String spouse;
    @SerializedName("children")
    @Expose
    private String children;
    @SerializedName("image")
    @Expose
    private String image;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     *
     * @param dob
     * The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The height
     */
    public String getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The spouse
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     *
     * @param spouse
     * The spouse
     */
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    /**
     *
     * @return
     * The children
     */
    public String getChildren() {
        return children;
    }

    /**
     *
     * @param children
     * The children
     */
    public void setChildren(String children) {
        this.children = children;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}



class Example {

    @SerializedName("actors")
    @Expose
    private List<Actor> actors = new ArrayList<Actor>();

    /**
     *
     * @return
     * The actors
     */
    public List<Actor> getActors() {
        return actors;
    }

    /**
     *
     * @param actors
     * The actors
     */
    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

}