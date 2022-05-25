package com.temp.reader.insulationmonitor.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature 
{
    @JsonProperty String dateTaken;
    @JsonProperty int temperature;

    public Temperature(@JsonProperty int temperature, @JsonProperty String dateTaken)
    {
        this.temperature = temperature;
        this.dateTaken = dateTaken;
    }

    public Temperature(@JsonProperty int temperature)
    {
        this.temperature = temperature;
        
        Date today = new Date();
        this.dateTaken = today.toString();
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
}