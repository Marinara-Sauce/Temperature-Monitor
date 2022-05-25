package com.temp.reader.insulationmonitor.controller;

import java.io.IOException;

import com.temp.reader.insulationmonitor.model.Temperature;
import com.temp.reader.insulationmonitor.persistence.TemperatureDAO;
import com.temp.reader.insulationmonitor.utils.TempReadings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @CrossOrigin
    public ResponseEntity<Temperature> getTemperature() throws IOException {
        try
        {
            int temperature = (int) Double.parseDouble(TempReadings.getMostRecentReading());
            Temperature temp = new Temperature(temperature);
    
            return new ResponseEntity<Temperature>(temp, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
