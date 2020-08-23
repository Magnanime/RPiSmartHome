package com.magnanime.RestHomeAutomationRPiServer.Common;

public enum DeviceTypes {
    TEMPSENSOR(1),
    HUMISENSOR(2);

    private final Integer type;

    DeviceTypes(Integer type) {
        this.type = type;
    }
    public Integer type(){
        return type;
    }
}
