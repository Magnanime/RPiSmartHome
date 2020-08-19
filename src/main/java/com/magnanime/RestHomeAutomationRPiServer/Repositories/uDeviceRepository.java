package com.magnanime.RestHomeAutomationRPiServer.Repositories;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface uDeviceRepository extends JpaRepository<UniversalDevice, Long> {
}
