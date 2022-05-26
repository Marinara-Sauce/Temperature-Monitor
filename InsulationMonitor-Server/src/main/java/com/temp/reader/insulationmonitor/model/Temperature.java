package com.temp.reader.insulationmonitor.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature 
{
    @JsonProperty String dateTaken;
    @JsonProperty int temperature;

    @JsonProperty boolean indoor;

    public Temperature(@JsonProperty int temperature, @JsonProperty String dateTaken, @JsonProperty boolean indoor)
    {
        this.temperature = temperature;
        this.dateTaken = dateTaken;
        this.indoor = indoor;
    }

    public Temperature(@JsonProperty int temperature, @JsonProperty boolean indoor)
    {
        this.temperature = temperature;
        
        Date today = new Date();
        this.dateTaken = today.toString();

        this.indoor = indoor;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }
}