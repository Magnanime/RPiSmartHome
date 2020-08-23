package com.magnanime.RestHomeAutomationRPiServer.Repositories;


import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "universalMeasurements", collectionResourceRel = "universalMeasurements", itemResourceRel = "universalMeasurement")
public interface UniversalMeasurementRepository extends JpaRepository<UniversalMeasurement, Long> {
    public UniversalMeasurement findTopByDeviceOrderByIdDesc(UniversalDevice device);
}
