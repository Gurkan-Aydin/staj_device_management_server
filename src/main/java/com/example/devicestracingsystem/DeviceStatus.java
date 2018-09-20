package com.example.devicestracingsystem;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "devicestatus")
public class DeviceStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int statusId;

    @Column(name = "temperature")
    private String  temperature;

    @Column(name = "is_open")
    private boolean isOpen;

    @Column(name = "open_duration")
    private String openDuration;

    @Column(name = "memory")
    private int memory;

    @Column(name = "cpu")
    private int cpu;

    @Column(name = "device_id")
    private int deviceId;

    @Column(name = "request_date")
    private LocalDateTime requestDate;


    public DeviceStatus(){}

    public DeviceStatus(String temperature, boolean isOpen, String openDuration, int memory, int cpu, int deviceId, LocalDateTime requestDate) {
        this.temperature = temperature;
        this.openDuration = openDuration;
        this.isOpen = isOpen;
        this.memory = memory;
        this.cpu = cpu;
        this.deviceId = deviceId;
        this.requestDate = requestDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getOpenDuration() {
        return openDuration;
    }

    public void setOpenDuration(String openDuration) {
        this.openDuration = openDuration;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

}