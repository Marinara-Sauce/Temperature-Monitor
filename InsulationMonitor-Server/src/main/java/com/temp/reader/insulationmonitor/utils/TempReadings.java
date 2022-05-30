package com.temp.reader.insulationmonitor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.temp.reader.insulationmonitor.model.Temperature;

public class TempReadings {

    private static String mostRecentReading;
    
    public static String readTemperature()
    {
        Process p;
        try {
            p = Runtime.getRuntime().exec("python read_temp.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();

            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @SuppressWarnings("unchecked")
    public static Temperature getOutdoorTemperature() throws ParseException, IOException
    {
        String apiURL = "https://api.weather.gov/gridpoints/BOX/60,56/forecast/hourly";
        Map<String, Object> response;

        response = HttpRequest.getHTTPRequest(apiURL);

        //Begin searching for the temperature
        response = (Map<String, Object>) response.get("properties");
        List<Object> periods = (List<Object>) response.get("periods");

        Map<String, Object> currentTemp = (Map<String, Object>) periods.get(0);
        
        int temp = (int) currentTemp.get("temperature");
        String dateTaken = (String) currentTemp.get("startTime");
        dateTaken = dateTaken.replace("T", " ");
        //dateTaken = dateTaken.split("-")[0];

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date taken = formatter.parse(dateTaken);

        return new Temperature(temp, taken.toString(), false);
    }

    public static String getMostRecentReading() {
        return mostRecentReading;
    }

    public static void setMostRecentReading(String mostRecentReading) {
        TempReadings.mostRecentReading = mostRecentReading;
    }
    
}
