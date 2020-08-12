package com.magnanime.RestHomeAutomationRPiServer.Repositories;

        import com.magnanime.RestHomeAutomationRPiServer.DataModel.Measurements.SI7021Measurement;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.ArrayList;

@Repository
public interface SI7021MeasurementRepository extends JpaRepository<SI7021Measurement, Long> {
        ArrayList<SI7021Measurement> findByDeviceid(Integer deviceId);
}
