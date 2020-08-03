package com.magnanime.RestHomeAutomationRPiServer.WebControllers;

import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.SI7021Controller;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/devices/{id}/")
public class DeviceWebController {
    @GetMapping("/")
    public JSONObject getAllUsers() {
        return SI7021Controller.toJson();
    }
}
