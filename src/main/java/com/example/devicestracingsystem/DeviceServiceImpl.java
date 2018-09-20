package com.example.devicestracingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepo deviceRepo;

    @Autowired
    public DeviceServiceImpl(DeviceRepo deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    @Override
    public List<Device> getAllDevices() {
        return Lists.newArrayList(this.deviceRepo.findAll());
    }


}
