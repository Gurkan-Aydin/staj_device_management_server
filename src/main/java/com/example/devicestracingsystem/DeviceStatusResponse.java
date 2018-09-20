package com.example.devicestracingsystem;

import java.util.List;

public class DeviceStatusResponse {
    private List<DeviceStatus> deviceStatus;

    public List<DeviceStatus> getDeviceStatus() {
        return deviceStatus;
    }

    public void setDevicesStatus(List<DeviceStatus> deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
