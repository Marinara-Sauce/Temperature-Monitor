package com.temp.reader.insulationmonitor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public static String getMostRecentReading() {
        return mostRecentReading;
    }

    public static void setMostRecentReading(String mostRecentReading) {
        TempReadings.mostRecentReading = mostRecentReading;
    }
    
}
