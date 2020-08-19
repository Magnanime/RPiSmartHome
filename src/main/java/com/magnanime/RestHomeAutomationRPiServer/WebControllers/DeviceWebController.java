package com.magnanime.RestHomeAutomationRPiServer.WebControllers;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.Device;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements.SI7021Measurement;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.DeviceChooser;
import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.DeviceInterface;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements.Measurement;
import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.I2CDeviceController;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.DeviceRepository;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.SI7021MeasurementRepository;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.uDeviceRepository;
import com.pi4j.io.i2c.I2CFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;


@RestController
@RequestMapping("/devices/{id}/")
public class DeviceWebController {

    private I2CDeviceController ctr;

    @Autowired
    private DeviceChooser chooser;

    @Autowired
    private DeviceRepository repository;

    @Autowired
    private uDeviceRepository urepository;

    @Autowired
    private SI7021MeasurementRepository Srepository;

    @GetMapping("/measurement")
    public Measurement getMeasurement(@PathVariable(value = "id") long id) throws IOException, I2CFactory.UnsupportedBusNumberException {
        UniversalDevice uDevice = urepository.findById(id).get();
        ctr = new I2CDeviceController(uDevice);
        ctr.getData();
        Device device = repository.findById(id).get();
        DeviceInterface specificDevice = chooser.determineDevice(device.getDeviceType());
        return specificDevice.getData((int) id);
    }

    @GetMapping("/history")
    public ArrayList<SI7021Measurement> getHistory(@PathVariable(value = "id") Integer id) {
        return Srepository.findByDeviceid(id);
    }
}