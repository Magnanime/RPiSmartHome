package com.magnanime.RestHomeAutomationRPiServer.DataModel.Devices;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements.Measurement;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements.SI7021Measurement;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "devices")
public class Device implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "devicetype")
    private Integer deviceType;
    @Column(name = "channel")
    private Integer channel;
    @OneToMany(mappedBy = "device")
    private List<Measurement> measurementList;
}

