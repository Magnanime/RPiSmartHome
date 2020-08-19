package com.magnanime.RestHomeAutomationRPiServer.Controllers.DeviceControllers;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
import java.util.HashMap;

public class I2CDeviceController {

    private static I2CBus bus = null;

    private int address;
    private int requestCommand;
    private int length;
    private UniversalDevice universalDevice;

    private I2CDevice device;

    public I2CDeviceController (UniversalDevice uDevice) throws IOException, I2CFactory.UnsupportedBusNumberException{
        if (bus == null){
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
        }

        this.universalDevice = uDevice;
        this.address = Integer.decode(uDevice.getAddress());
        this.requestCommand = Integer.decode(uDevice.getCommand());
        this.length = uDevice.getLength();

        device = bus.getDevice(address);
    }

    public HashMap<UniversalDevice, byte[]> getData() throws IOException, InterruptedException {
        HashMap<UniversalDevice, byte[]> retMap = new HashMap<UniversalDevice, byte[]>();
        device.write((byte) this.requestCommand);
        Thread.sleep(300);
        byte[] data = new byte[length];
        device.read(data, 0, length);
        retMap.put(this.universalDevice, data);
        return retMap;
    }
}
