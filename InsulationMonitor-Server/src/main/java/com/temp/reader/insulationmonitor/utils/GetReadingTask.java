package com.temp.reader.insulationmonitor.utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.TimerTask;

import com.temp.reader.insulationmonitor.persistence.RecordDAO;

public class GetReadingTask extends TimerTask {

    @Override
    public void run() {
        
        try {
            RecordDAO dao = new RecordDAO("records.json");
            dao.captureReading();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }

}
