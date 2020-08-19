package com.magnanime.RestHomeAutomationRPiServer.Repositories;


import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversalMeasurementRepository extends JpaRepository<UniversalMeasurement, Long> {
}
