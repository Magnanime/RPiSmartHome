package com.magnanime.RestHomeAutomationRPiServer.Controllers.MeasurementControllers;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalMeasurement;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.HashMap;

public class MeasurementController {

    private UniversalMeasurement universalMeasurement;
    private UniversalDevice universalDevice;
    private HashMap<UniversalDevice, byte[]> measurement;
    private ZonedDateTime timestamp = null;

    public MeasurementController(HashMap<UniversalDevice, byte[]> measurement){
        for (UniversalDevice device: measurement.keySet()){
            this.measurement = measurement;
            this.universalMeasurement = new UniversalMeasurement();
            this.universalDevice = device;
        }
    }

    public UniversalMeasurement getData () {
        switch(universalDevice.getType()) {
            //Temperature sensor
            case 1:
                universalMeasurement.setTimestamp(timestamp = ZonedDateTime.now());
                universalMeasurement.setDevice(universalDevice);
                universalMeasurement.setValue(String.valueOf(BigDecimal.valueOf((((((
                           measurement.get(universalDevice)[0] & 0xFF) * 256)
                        + (measurement.get(universalDevice)[1] & 0xFF)) * 175.72) / 65536.0) - 46.85)
                          .setScale(2, RoundingMode.HALF_UP)));
                return universalMeasurement;
            //humidity sensor
            case 2:
                return universalMeasurement;
            default:
                return universalMeasurement;
        }
    }


}
