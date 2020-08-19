package com.magnanime.RestHomeAutomationRPiServer.Repositories;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UniversalDeviceRepository extends JpaRepository<UniversalDevice, Long> {
    ArrayList<UniversalDevice> findByType(Integer deviceType);
}
