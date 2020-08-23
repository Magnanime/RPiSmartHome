package com.magnanime.RestHomeAutomationRPiServer.Controllers.WebControllers;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalMeasurement;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.UniversalMeasurementRepository;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.UniversalDeviceRepository;
import com.pi4j.io.i2c.I2CFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/devices/{id}/measurements/")
public class DeviceWebController {

    @Autowired
    private UniversalDeviceRepository deviceRepository;

    @Autowired
    private UniversalMeasurementRepository measurementRepository;

    @GetMapping("/latest")
    public UniversalMeasurement getMeasurement(@PathVariable("id") Long id){
        //Find device by id and display latest measurement with same device id
        return measurementRepository.findTopByDeviceOrderByIdDesc(deviceRepository.findById(id).get());
    }
}
