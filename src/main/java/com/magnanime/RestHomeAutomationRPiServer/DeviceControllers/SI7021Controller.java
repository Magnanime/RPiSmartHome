package com.magnanime.RestHomeAutomationRPiServer.DeviceControllers;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements.SI7021Measurement;
import com.magnanime.RestHomeAutomationRPiServer.Repositories.SI7021MeasurementRepository;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@Component
public class SI7021Controller implements DeviceInterface {

    private static BigDecimal lastTemperatureC;
    private static BigDecimal lastTemperatureF;
    private static BigDecimal lastHumidity;

    private I2CBus bus;
    private I2CDevice device;

    @Autowired
    private SI7021MeasurementRepository repository;


    public SI7021Controller() throws IOException, I2CFactory.UnsupportedBusNumberException {

        // Create I2C bus
        this.bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, SI7021 I2C address is 0x40(64)
        this.device = bus.getDevice(0x40);
        makeMeasurements();
    }


    public static BigDecimal getLastTemperatureC() {
        return lastTemperatureC;
    }

    public static BigDecimal getLastTemperatureF() {
        return lastTemperatureF;
    }

    public static BigDecimal getLastHumidity() {
        return lastHumidity;
    }

    public void makeMeasurements(){
        try {
            // Send humidity measurement command
            device.write((byte) 0xF5);
            Thread.sleep(300);
            // Read 2 bytes of humidity data, msb first
            byte[] data = new byte[2];
            device.read(data, 0, 2);
            lastHumidity = BigDecimal.valueOf((((((data[0] & 0xFF) * 256) + (data[1] & 0xFF)) * 125.0) / 65536.0) - 6);
            lastHumidity = lastHumidity.setScale(2, RoundingMode.HALF_UP);

            // Send temperature measurement command
            device.write((byte) 0xF3);
            Thread.sleep(300);
            // Read 2 bytes of temperature data, msb first
            device.read(data, 0, 2);
            // Convert temperature data
            lastTemperatureC = BigDecimal.valueOf((((((data[0] & 0xFF) * 256) + (data[1] & 0xFF)) * 175.72) / 65536.0) - 46.85);
            lastTemperatureC = lastTemperatureC.setScale(2, RoundingMode.HALF_UP);
            lastTemperatureF = (lastTemperatureC.multiply(BigDecimal.valueOf(1.8))).add(BigDecimal.valueOf(32));
            lastTemperatureF = lastTemperatureF.setScale(2, RoundingMode.HALF_UP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SI7021Measurement getData(Integer deviceId) {
        SI7021Measurement retValue = new SI7021Measurement();
        retValue.setDeviceid(deviceId);
        return retValue;
    }
}
