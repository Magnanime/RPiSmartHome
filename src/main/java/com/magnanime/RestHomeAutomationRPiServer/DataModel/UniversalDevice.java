package com.magnanime.RestHomeAutomationRPiServer.DataModel;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "udevices")
public class UniversalDevice implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private Integer type;
    @Column(name = "channel")
    private int channel;
    @Column(name = "address")
    private String address;
    @Column(name = "command")
    private String command;
    @Column(name = "length")
    private int length;
}
