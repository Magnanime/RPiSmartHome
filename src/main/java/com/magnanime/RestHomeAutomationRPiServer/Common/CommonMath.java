package com.magnanime.RestHomeAutomationRPiServer.Common;

public enum CommonMath {
    DEFAULTSCALE(2);

    private final Integer value;

    CommonMath(Integer value) {
        this.value = value;
    }
    public Integer value(){
        return value;
    }
}
