package com.example.devicestracingsystem;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepo extends CrudRepository<Device, Integer> {

    List<Device> findById(int id, Pageable pageable);

    List<Device> findByUrl(String url, Pageable pageable);

    List<Device> findByType(DeviceType type, Pageable pageable);

    List<Device> findByIp(String ip, Pageable pageable);

    PageImpl<Device> findAll(Pageable pageable);

    List<Device> findByIp(String ip);

}