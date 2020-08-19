package com.magnanime.RestHomeAutomationRPiServer.Controllers.WebControllers;

import com.magnanime.RestHomeAutomationRPiServer.Controllers.MeasurementControllers.MeasurementController;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalMeasurement;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import com.magnanime.RestHomeAutomationRPiServer.Controllers.DeviceControllers.I2CDeviceController;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.UniversalMeasurementRepository;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.UniversalDeviceRepository;
import com.pi4j.io.i2c.I2CFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/devices/{id}/")
public class DeviceWebController {

    @Autowired
    private UniversalDeviceRepository deviceRepository;

    @Autowired
    private UniversalMeasurementRepository measurementRepository;


    @GetMapping("/measurement")
    public UniversalMeasurement getMeasurement(@PathVariable(value = "id") long id) throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        //Find device by if ()
        UniversalDevice device = deviceRepository.findById(id).get();
        I2CDeviceController ctr = new I2CDeviceController(device);
        MeasurementController manager = new MeasurementController(ctr.getData());
        measurementRepository.save(manager.getData());
        return manager.getData();
    }

    //@GetMapping("/history")
    //public ArrayList<SI7021Measurement> getHistory(@PathVariable(value = "id") Integer id) {
    //    return Srepository.findByDeviceid(id);
   // }
}
