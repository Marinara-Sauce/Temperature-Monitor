package com.temp.reader.insulationmonitor.controller;

import com.temp.reader.insulationmonitor.persistence.RecordDAO;
import com.temp.reader.insulationmonitor.model.Reading;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/readings")
public class ReadingsController 
{
    private RecordDAO recordDAO;

    public ReadingsController(RecordDAO recordDAO) {
        this.recordDAO = recordDAO;
    }

    @GetMapping("")
    public ResponseEntity<Reading[]> getReadings()
    {
        return new ResponseEntity<Reading[]>(recordDAO.getReadingsArray(), HttpStatus.OK);
    }
}
