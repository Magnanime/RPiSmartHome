package com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public class Measurement {

    @Column(name = "timestamp")
    private ZonedDateTime timestamp;
    @Column(name = "deviceid")
    private Integer deviceid;
}
