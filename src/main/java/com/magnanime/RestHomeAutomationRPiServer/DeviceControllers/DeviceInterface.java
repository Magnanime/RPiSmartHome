package com.magnanime.RestHomeAutomationRPiServer.DeviceControllers;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements.Measurement;

public interface DeviceInterface {
    Measurement getData(Integer deviceId);
}
