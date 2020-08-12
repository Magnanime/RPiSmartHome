package com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements;

import com.magnanime.RestHomeAutomationRPiServer.DeviceControllers.SI7021Controller;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "si7021measurements")
public class SI7021Measurement extends Measurement{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "temperaturec")
    private BigDecimal temperatureC;
    @Column(name = "temperaturef")
    private BigDecimal temperatureF;
    @Column(name = "humidity")
    private BigDecimal humidity;

    public SI7021Measurement () {
        this.temperatureC = SI7021Controller.getLastTemperatureC();
        this.temperatureF = SI7021Controller.getLastTemperatureF();
        this.humidity = SI7021Controller.getLastHumidity();
        this.setTimestamp(ZonedDateTime.now());
    }
}
