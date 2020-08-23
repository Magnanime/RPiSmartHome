package com.magnanime.RestHomeAutomationRPiServer.Controllers.MeasurementControllers;

import com.magnanime.RestHomeAutomationRPiServer.Common.CommonMath;
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

    public MeasurementController(HashMap<UniversalDevice, byte[]> measurement){
        for (UniversalDevice device: measurement.keySet()){
            this.measurement = measurement;
            this.universalMeasurement = new UniversalMeasurement();
            this.universalDevice = device;
        }
    }

    public UniversalMeasurement getData () {
        //Set common data
        universalMeasurement.setTimestamp(ZonedDateTime.now());
        universalMeasurement.setDevice(universalDevice);

        switch(universalDevice.getType()) {
            //Temperature sensor
            case 1:
                universalMeasurement.setValue(String.valueOf(BigDecimal.valueOf((((((
                           measurement.get(universalDevice)[0] & 0xFF) * 256)
                        + (measurement.get(universalDevice)[1] & 0xFF)) * 175.72) / 65536.0) - 46.85)
                          .setScale(CommonMath.DEFAULTSCALE.value(), RoundingMode.HALF_UP)));
                return universalMeasurement;
            //Humidity sensor
            case 2:
                universalMeasurement.setValue(String.valueOf(BigDecimal.valueOf((((((
                        measurement.get(universalDevice)[0] & 0xFF) * 256)
                        + (measurement.get(universalDevice)[1] & 0xFF)) * 125.0) / 65536.0) - 6)
                        .setScale(CommonMath.DEFAULTSCALE.value(), RoundingMode.HALF_UP)));
                return universalMeasurement;
            default:
                return universalMeasurement;
        }
    }


}
