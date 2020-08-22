package com.magnanime.RestHomeAutomationRPiServer.Controllers.WebControllers;

import com.magnanime.RestHomeAutomationRPiServer.Controllers.MeasurementControllers.MeasurementController;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalMeasurement;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import com.magnanime.RestHomeAutomationRPiServer.Controllers.DeviceControllers.I2CDeviceController;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.UniversalMeasurementRepository;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.UniversalDeviceRepository;
import com.pi4j.io.i2c.I2CFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/devices/{id}/")
public class DeviceWebController {

    @Autowired
    private UniversalDeviceRepository deviceRepository;

    @Autowired
    private UniversalMeasurementRepository measurementRepository;


    @GetMapping("/measurement")
    public UniversalMeasurement getMeasurement(@RequestParam Long id) throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        //Find device by if ()
        return measurementRepository.findTopByDeviceOrderByIdDesc(deviceRepository.findById(id).get());
    }

    //@GetMapping("/history")
    //public ArrayList<SI7021Measurement> getHistory(@PathVariable(value = "id") Integer id) {
    //    return Srepository.findByDeviceid(id);
   // }
}
