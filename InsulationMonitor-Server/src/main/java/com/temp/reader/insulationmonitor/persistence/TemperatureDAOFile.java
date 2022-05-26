package com.temp.reader.insulationmonitor.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.temp.reader.insulationmonitor.model.Temperature;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TemperatureDAOFile 
{
    private List<Temperature> temperatures;

    private ObjectMapper mapper;
    private String filename;

    public TemperatureDAOFile(@Value("${temperature_list.file}") String filename, ObjectMapper mapper) throws IOException {
        this.filename = filename;
        this.mapper = mapper;
        load();
    }

    private boolean load() throws IOException {
        temperatures = new ArrayList<>();

        Temperature[] tempArray = mapper.readValue(new File(filename), Temperature[].class);
        
        for (Temperature t : tempArray)
            temperatures.add(t);

        return true;
    }

    private boolean save() throws IOException
    {
        Temperature[] tempArray = (Temperature[]) temperatures.toArray();

        mapper.writeValue(new File(filename), tempArray);

        return true;
    }

    public Temperature[] getTemperatureArray()
    {
        return (Temperature[]) temperatures.toArray();
    }

    public Temperature[] getSpecificTemp(boolean indoor)
    {
        List<Temperature> indoorTemps = new ArrayList<>();

        for (Temperature t : getTemperatureArray())
        {
            if (t.isIndoor() == indoor)
                indoorTemps.add(t);
        }

        return (Temperature[]) indoorTemps.toArray();
    }

    public Temperature getTemperatureAtDate(String date)
    {
        for (int i = 0 ; i < temperatures.size() ; i++)
        {
            Temperature t = temperatures.get(i);

            if (t.getDateTaken().equalsIgnoreCase(date))
                return t;
        }

        return null;
    }

    public void addTemperature(Temperature t) throws IOException
    {
        temperatures.add(t);
        save();
    }
}
