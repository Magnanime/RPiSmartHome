package com.magnanime.RestHomeAutomationRPiServer.ScheludedActions;


import com.magnanime.RestHomeAutomationRPiServer.DataModel.Device;
import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.SI7021Controller;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.DeviceRepository;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.SI7021MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class ScheduledSI7021Measurement {

    @Autowired
    SI7021Controller controller;

    @Autowired
    SI7021MeasurementRepository measurementRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Scheduled(fixedRate = 600000)
        public void makeMeasurements() {
        ArrayList<Device> devices = deviceRepository.findByDeviceType(1);
        for (Device device : devices) {
            //choose channel
            controller.makeMeasurements();
            measurementRepository.save(controller.getData(device.getId().intValue()));
        }
    }
}
