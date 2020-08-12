package com.magnanime.RestHomeAutomationRPiServer.DeviceControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceChooser {

    @Autowired
    private SI7021Controller controller;

    public DeviceInterface determineDevice(Integer type) {
        if (type == 1){
            return controller;
        }
        return null;
    }
}
