package com.temp.reader.insulationmonitor.persistence;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.temp.reader.insulationmonitor.model.Reading;
import com.temp.reader.insulationmonitor.model.Temperature;
import com.temp.reader.insulationmonitor.utils.TempReadings;

import org.springframework.stereotype.Component;

@Component
public class RecordDAO {
    
    private List<Reading> readings;

    private ObjectMapper mapper;

    private String filename;

    public RecordDAO(String filename) throws IOException
    {
        mapper = new ObjectMapper();
        this.filename = filename;

        load();
    }

    private boolean load() throws IOException {
        readings = new ArrayList<>();

        Reading[] readingsArr = mapper.readValue(new File(filename), Reading[].class);

        for (Reading r : readingsArr)
            readings.add(r);
        
        return true;
    }

    private boolean save() throws IOException {
        Reading[] readingsArr = getReadingsArray();
        mapper.writeValue(new File(filename), readingsArr);
        return true;
    }

    public Reading[] getReadingsArray()
    {
        Reading[] readingsArr = new Reading[readings.size()];

        for (int i = 0 ; i < readings.size() ; i++)
            readingsArr[i] = readings.get(i);
        
        return readingsArr;
    }

    public void captureReading() throws IOException, ParseException
    {
        int indoorTemp = Integer.parseInt(TempReadings.getMostRecentReading());
        Temperature indoor = new Temperature(indoorTemp, true);
        
        Temperature outdoor = TempReadings.getOutdoorTemperature();
        
        Reading r = new Reading(indoor, outdoor);

        readings.add(r);

        save();
    }

}
