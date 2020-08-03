package com.magnanime.RestHomeAutomationRPiServer.DeviceControllers;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.IOException;

public class SI7021Controller implements DeviceInterface {
    @Getter
    @Setter
    private static double temperatureC;
    private static double temperatureF;
    private static double humidity;

    private static I2CBus bus;
    private static I2CDevice device;

    private static SI7021Controller instance;

    static {
        try {
            instance = new SI7021Controller();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            e.printStackTrace();
        }
    }

    //Singleton constructors
    private SI7021Controller() throws IOException, I2CFactory.UnsupportedBusNumberException {
        // Create I2C bus
        this.bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, SI7021 I2C address is 0x40(64)
        this.device = bus.getDevice(0x40);
    }

    public static SI7021Controller getInstance(){
        return instance;
    }

    public static void makeMeasurements() throws IOException, InterruptedException {
        // Send humidity measurement command
        device.write((byte) 0xF5);
        Thread.sleep(300);

        // Read 2 bytes of humidity data, msb first
        byte[] data = new byte[2];
        device.read(data, 0, 2);

        humidity = (((((data[0] & 0xFF) * 256) + (data[1] & 0xFF)) * 125.0) / 65536.0) - 6;


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
        temperatureC = (((((data[0] & 0xFF) * 256) + (data[1] & 0xFF)) * 175.72) / 65536.0) - 46.85;
        temperatureF = (temperatureC * 1.8) + 32;
    }
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("Temperature in C: ", temperatureC);
        jo.put("Temperature in F: ", temperatureF);
        jo.put("Humidity: ", humidity);
        return jo;
    }

    @Override
    public static JSONObject getData() {
        return toJson();
    }
}
