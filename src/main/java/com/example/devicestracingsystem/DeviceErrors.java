package com.example.devicestracingsystem;

import javax.persistence.*;

@Entity
@Table(name = "errors")
public class DeviceErrors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "device_id")
    private int deviceId;

    @Column(name = "comment")
    private String comment;

    public DeviceErrors(){}

    public DeviceErrors(int deviceId, String comment) {
        this.deviceId = deviceId;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
