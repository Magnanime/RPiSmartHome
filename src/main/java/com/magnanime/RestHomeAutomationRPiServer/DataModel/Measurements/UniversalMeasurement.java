package com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

public class UniversalMeasurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "timestamp")
    private ZonedDateTime timestamp;
    @Column(name = "deviceid")
    private Integer deviceId;
    @Column(name = "value")
    private String value;
}
