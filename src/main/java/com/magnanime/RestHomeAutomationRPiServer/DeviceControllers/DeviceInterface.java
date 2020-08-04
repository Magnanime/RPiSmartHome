package com.magnanime.RestHomeAutomationRPiServer.DeviceControllers;

import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.Measurements.Measurement;
import org.json.JSONObject;

public interface DeviceInterface {
    public Measurement getData();
}
