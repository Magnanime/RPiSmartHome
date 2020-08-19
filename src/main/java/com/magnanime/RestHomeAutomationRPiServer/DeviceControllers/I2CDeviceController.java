package com.magnanime.RestHomeAutomationRPiServer.DeviceControllers;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class I2CDeviceController {

    private static I2CBus bus = null;

    private int address;
    private int requestCommand;
    private int length;
    private Integer type;

    private I2CDevice device;

    public I2CDeviceController (UniversalDevice uDevice) throws IOException, I2CFactory.UnsupportedBusNumberException{
        if (bus == null){
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
        }

        this.address = Integer.decode(uDevice.getAddress());
        this.requestCommand = Integer.decode(uDevice.getCommand());
        this.type = uDevice.getType();
        this.length = uDevice.getLength();

        device = bus.getDevice(address);
    }

    public HashMap<Integer, byte[]> getData() throws IOException {
        HashMap<Integer, byte[]> retMap = new HashMap<Integer, byte[]>();
        byte[] data = new byte[length];
        device.read(data, 0, length);
        retMap.put(this.type, data);
        return retMap;
    }
}
