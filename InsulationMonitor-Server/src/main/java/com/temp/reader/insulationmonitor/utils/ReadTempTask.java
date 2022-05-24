package com.temp.reader.insulationmonitor.utils;

import java.util.TimerTask;

public class ReadTempTask extends TimerTask {
    @Override
    public void run() {
        TempReadings.setMostRecentReading( TempReadings.readTemperature());
    }
}
