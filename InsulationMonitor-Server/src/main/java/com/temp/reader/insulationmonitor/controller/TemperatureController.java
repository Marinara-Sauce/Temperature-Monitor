package com.temp.reader.insulationmonitor.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.temp.reader.insulationmonitor.persistence.TemperatureDAO;

import org.python.util.PythonInterpreter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("temp")
public class TemperatureController {

    private TemperatureDAO tempDao;

    private PythonInterpreter interperter;
    private Runtime rt;

    public TemperatureController(TemperatureDAO tempDao) {
        this.tempDao = tempDao;

        interperter = new PythonInterpreter();
        rt = Runtime.getRuntime();
    }

    @GetMapping("")
    public String getTemperature() throws IOException {

        String[] commands = {"python", "read_temp.py"};
        Process proc = rt.exec(commands);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        //BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        String temp = stdInput.readLine();

        return temp;
    }
    
}
