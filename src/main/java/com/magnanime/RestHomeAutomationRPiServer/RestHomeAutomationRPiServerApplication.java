package com.magnanime.RestHomeAutomationRPiServer;

import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.SI7021Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RestHomeAutomationRPiServerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(RestHomeAutomationRPiServerApplication.class, args);
		SI7021Controller.makeMeasurements();
		System.out.println(SI7021Controller.getTemperatureC());
	}
}
