package com.temp.reader.insulationmonitor.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.temp.reader.insulationmonitor.model.Temperature;
import com.temp.reader.insulationmonitor.persistence.TemperatureDAOFile;
import com.temp.reader.insulationmonitor.utils.HttpRequest;
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

    private TemperatureDAOFile tempDao;

    public TemperatureController(TemperatureDAOFile tempDao) {
        this.tempDao = tempDao;
    }

    @GetMapping("")
    @CrossOrigin
    public ResponseEntity<Temperature> getTemperature() throws IOException {
        try
        {
            int temperature = (int) Double.parseDouble(TempReadings.getMostRecentReading());
            Temperature temp = new Temperature(temperature, true);

            tempDao.addTemperature(temp);
    
            return new ResponseEntity<Temperature>(temp, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/outdoor")
    @CrossOrigin
    @SuppressWarnings("unchecked")
    public ResponseEntity<Temperature> getOutdoorTemperature() throws IOException, ParseException {

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

        Temperature temperature = new Temperature(temp, taken.toString(), false);

        tempDao.addTemperature(temperature);

        return new ResponseEntity<Temperature>(temperature, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<Temperature[]> getAllTemperatures() throws IOException {
        return new ResponseEntity<Temperature[]>(tempDao.getTemperatureArray(), HttpStatus.OK);
    }

    @GetMapping("/all/indoor")
    @CrossOrigin
    public ResponseEntity<Temperature[]> getAllIndoorTemperatures() throws IOException {
        return new ResponseEntity<Temperature[]>(tempDao.getSpecificTemp(true), HttpStatus.OK);
    }

    @GetMapping("/all/outdoor")
    @CrossOrigin
    public ResponseEntity<Temperature[]> getAllOutdoorTemperatures() throws IOException {
        return new ResponseEntity<Temperature[]>(tempDao.getSpecificTemp(false), HttpStatus.OK);
    }
}
