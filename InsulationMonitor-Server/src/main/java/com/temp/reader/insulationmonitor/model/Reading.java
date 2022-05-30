package com.temp.reader.insulationmonitor.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reading {

    @JsonProperty private Temperature indoor;
    @JsonProperty private Temperature outdoor;
    
    @JsonProperty private String date;

    public Reading(@JsonProperty Temperature indoor, @JsonProperty Temperature outdoor, @JsonProperty String date)
    {
        this.indoor = indoor;
        this.outdoor = outdoor;

        this.date = date;
    }

    public Reading(@JsonProperty Temperature indoor, @JsonProperty Temperature outdoor)
    {
        this.indoor = indoor;
        this.outdoor = outdoor;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.date = format.format(new Date());
    }

    public Temperature getIndoor() {
        return indoor;
    }

    public void setIndoor(Temperature indoor) {
        this.indoor = indoor;
    }

    public Temperature getOutdoor() {
        return outdoor;
    }

    public void setOutdoor(Temperature outdoor) {
        this.outdoor = outdoor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    
}
