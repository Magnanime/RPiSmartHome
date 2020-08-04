package com.magnanime.RestHomeAutomationRPiServer.DeviceControllers;

import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.Measurements.SI7021Measurement;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.IOException;

@Getter
@Setter
public class SI7021Controller implements DeviceInterface {
    private static double lastTemperatureC;
    private static double lastTemperatureF;
    private static double lastHumidity;

    private I2CBus bus;
    private I2CDevice device;


    //Singleton constructors
    public SI7021Controller() throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        // Create I2C bus
        this.bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, SI7021 I2C address is 0x40(64)
        this.device = bus.getDevice(0x40);
        makeMeasurements();
    }

    public void makeMeasurements() throws IOException, InterruptedException {
        // Send humidity measurement command
        device.write((byte) 0xF5);
        Thread.sleep(300);

        // Read 2 bytes of humidity data, msb first
        byte[] data = new byte[2];
        device.read(data, 0, 2);

        lastHumidity = (((((data[0] & 0xFF) * 256) + (data[1] & 0xFF)) * 125.0) / 65536.0) - 6;


        // Send temperature measurement command
        device.write((byte) 0xF3);
        Thread.sleep(300);

        // Read 2 bytes of temperature data, msb first
        device.read(data, 0, 2);

        // Send temperature measurement command
        device.write((byte) 0xF3);
        Thread.sleep(300);

        // Read 2 bytes of temperature data, msb first
        device.read(data, 0, 2);

        // Convert temperature data
        lastTemperatureC = (((((data[0] & 0xFF) * 256) + (data[1] & 0xFF)) * 175.72) / 65536.0) - 46.85;
        lastTemperatureF = (lastTemperatureC * 1.8) + 32;
    }
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("Temperature in C: ", lastTemperatureC);
        jo.put("Temperature in F: ", lastTemperatureF);
        jo.put("Humidity: ", lastHumidity);
        return jo;
    }

    @Override
    public SI7021Measurement getData() {
        SI7021Measurement data = new SI7021Measurement();
        data.setTemperatureC(lastTemperatureC);
        data.setTemperatureF(lastTemperatureF);
        data.setHumidity(lastHumidity);
        return data;
    }
}
