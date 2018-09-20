package com.example.devicestracingsystem;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table (name = "deviceinformation")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "URL")
    private String url;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Column(name = "IP")
    private String ip;

    @Column(name = "open_date")
    private LocalDateTime openDate;


    public Device(String url, DeviceType type, String ip, LocalDateTime openDate) {
        this.url = url;
        this.type = type;
        this.ip = ip;
        this.openDate = openDate;
    }

    protected  Device (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDateTime openDate) {
        this.openDate = openDate;
    }
}
