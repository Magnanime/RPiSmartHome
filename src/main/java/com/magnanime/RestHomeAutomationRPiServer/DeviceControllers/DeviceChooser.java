package com.magnanime.RestHomeAutomationRPiServer.DeviceControllers;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.Device;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.DeviceRopository;
import com.pi4j.io.i2c.I2CFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class DeviceChooser {

    public DeviceInterface determineDevice(Integer type) throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        if (type == 1){
            return new SI7021Controller();
        }
        return null;
    }
}
