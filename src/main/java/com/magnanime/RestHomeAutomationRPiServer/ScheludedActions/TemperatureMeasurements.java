package com.magnanime.RestHomeAutomationRPiServer.ScheludedActions;


import com.magnanime.RestHomeAutomationRPiServer.Common.DeviceTypes;
import com.magnanime.RestHomeAutomationRPiServer.Controllers.DeviceControllers.I2CDeviceController;
import com.magnanime.RestHomeAutomationRPiServer.Controllers.MeasurementControllers.MeasurementController;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.UniversalDeviceRepository;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.UniversalMeasurementRepository;
import com.pi4j.io.i2c.I2CFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;


@Component
public class TemperatureMeasurements {

    @Autowired
    private UniversalDeviceRepository deviceRepository;

    @Autowired
    private UniversalMeasurementRepository measurementRepository;

    @Scheduled(fixedRate = 600000)
        public void makeMeasurements() throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        ArrayList<UniversalDevice> devices = deviceRepository.findByType(DeviceTypes.TEMPSENSOR.type());
        for (UniversalDevice device : devices) {
            I2CDeviceController ctr = new I2CDeviceController(device);
            MeasurementController manager = new MeasurementController(ctr.getData());
            measurementRepository.save(manager.getData());
        }
    }
}
