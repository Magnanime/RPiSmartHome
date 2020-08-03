package com.magnanime.RestHomeAutomationRPiServer.Repositories;


import com.magnanime.RestHomeAutomationRPiServer.DataModel.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "devices", collectionResourceRel = "devices", itemResourceRel = "Device")
public interface DeviceRopository extends JpaRepository<Device, Long> {
    Device getDeviceById(Long id);
}
