package com.example.devicestracingsystem;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface StatusRepo extends CrudRepository<DeviceStatus, Integer> {

    List<DeviceStatus> findByDeviceId(int deviceId, Pageable pageable);
    List<DeviceStatus> findByDeviceId(int deviceId);

    //List<DeviceStatus> findByRequestDateBetween(Date start, Date end, Pageable pageable);
    List<DeviceStatus> findByRequestDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

}
