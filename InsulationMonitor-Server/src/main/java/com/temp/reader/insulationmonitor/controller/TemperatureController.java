package com.temp.reader.insulationmonitor.controller;

import java.io.IOException;

import com.temp.reader.insulationmonitor.persistence.TemperatureDAO;
import com.temp.reader.insulationmonitor.utils.TempReadings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("temp")
public class TemperatureController {

    private TemperatureDAO tempDao;

    public TemperatureController(TemperatureDAO tempDao) {
        this.tempDao = tempDao;
    }

    public TemperatureController() {

    }

    @GetMapping("")
    public String getTemperature() throws IOException {

        return TempReadings.getMostRecentReading();

    }
    
}
