package com.magnanime.RestHomeAutomationRPiServer.WebControllers;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.Device;
import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.DeviceChooser;
import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.DeviceInterface;
import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.Measurements.Measurement;
import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.SI7021Controller;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.DeviceRopository;
import com.pi4j.io.i2c.I2CFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/devices/{id}/")
public class DeviceWebController {

    @Autowired
    private DeviceRopository repository;

    @GetMapping("/measurement")
    public Measurement getMeasurement(@PathVariable(value = "id") long id) throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        Device device = repository.findById(id).get();
        DeviceInterface specificDevice = new DeviceChooser().determineDevice(device.getType());
        return specificDevice.getData();
    }
}
