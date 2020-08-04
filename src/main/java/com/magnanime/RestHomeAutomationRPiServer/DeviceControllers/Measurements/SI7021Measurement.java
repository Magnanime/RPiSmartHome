package com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.Measurements;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SI7021Measurement implements Measurement{
    private double temperatureC;
    private double temperatureF;
    private double humidity;
}
