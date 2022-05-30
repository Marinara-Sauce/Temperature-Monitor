package com.temp.reader.insulationmonitor;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.temp.reader.insulationmonitor.utils.ReadTempTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsulationMonitorApplication {

	public static void main(String[] args) {

		//Setup the temp reader
		TimerTask task = new ReadTempTask();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, new Date(), 10000);

		//Setup the recordings
		TimerTask recordingTask = new ReadTempTask();
		Timer recordingTimer = new Timer();
		recordingTimer.scheduleAtFixedRate(recordingTask, new Date(), 3600000);

		SpringApplication.run(InsulationMonitorApplication.class, args);
	}

}
