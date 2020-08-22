package com.magnanime.RestHomeAutomationRPiServer.Repositories;

import com.magnanime.RestHomeAutomationRPiServer.DataModel.UniversalDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Repository
@RepositoryRestResource(path = "devices", collectionResourceRel = "devices", itemResourceRel = "UniversalDevice")
public interface UniversalDeviceRepository extends JpaRepository<UniversalDevice, Long> {
    ArrayList<UniversalDevice> findByType(@RequestParam(value = "type", required = false) Integer deviceType);
}
