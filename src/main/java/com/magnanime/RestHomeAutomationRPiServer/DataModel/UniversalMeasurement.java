package com.magnanime.RestHomeAutomationRPiServer.DataModel;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "universal_measurement")
public class UniversalMeasurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "timestamp")
    private ZonedDateTime timestamp;
    @Column(name = "deviceid")
    private Long deviceId;
    @Column(name = "value")
    private String value;
}
