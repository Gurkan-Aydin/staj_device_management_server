package com.example.devicestracingsystem;

import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.common.collect.Lists;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/"})
public class DeviceController {

    @Autowired
    private DeviceRepo repo;
    private int page, pageSize;
    private String filterValue = "@", filterColumn;

    @Autowired
    private DeviceService deviceService;


    @PostMapping("/add")
    public void addDevice(@RequestBody Device device) {
        repo.save(device);
    }


    @GetMapping(value = "/delete/{id}")
    public void deleteDeviceById(@PathVariable int id) {
        repo.deleteById(id);
    }


    @GetMapping("/refresh")
    public ResponseEntity refresh(){
        return  getDevices(this.page, this.pageSize, this.filterValue, this.filterColumn);
    }

    @PostMapping("/get/{filterValue}/{page}/{pageSize}")
    public ResponseEntity getDevices(@PathVariable int page, @PathVariable int pageSize, @PathVariable String filterValue, @RequestBody String filterColumn){
        this.page = page; this.pageSize = pageSize; this.filterValue = filterValue; this.filterColumn = filterColumn;
        DeviceResponse deviceResponse = new DeviceResponse();
        if(filterValue.equalsIgnoreCase("@")){  deviceResponse.setDevices(getAll(this.page, this.pageSize).getContent()); }
        else if(filterColumn.equalsIgnoreCase("id")){
            deviceResponse.setDevices(getFindById(Integer.parseInt(filterValue))); }
        else if(filterColumn.equalsIgnoreCase("url")){
            deviceResponse.setDevices(repo.findByUrl(filterValue, PageRequest.of(page, pageSize)));}
        else if(filterColumn.equalsIgnoreCase("type")){
            deviceResponse.setDevices(repo.findByType(DeviceType.valueOf(filterValue), PageRequest.of(page, pageSize)));}
        else if(filterColumn.equalsIgnoreCase("ip")){
            deviceResponse.setDevices(repo.findByIp(filterValue, PageRequest.of(page, pageSize)));}
        return new  ResponseEntity<>(deviceResponse, HttpStatus.OK );
    }

    public List<Device> getFindById(int id){
        return repo.findById(id, PageRequest.of(0, pageSize));
    }

    @GetMapping("/get/{page}/{size}")
    public PageImpl<Device> getAll(@PathVariable int page, @PathVariable int size){
       return repo.findAll(PageRequest.of(page, size));
    }

    @PostMapping("/update/{id}")
    public void updateDevice(@PathVariable int id, @RequestBody Device device){
       Device preDevice = getFindById(id).get(0);
       preDevice.setUrl(device.getUrl());
       preDevice.setType(device.getType());
       preDevice.setIp(device.getIp());
       repo.save(preDevice);
    }

    @GetMapping("/changePage/{direction}")
    public ResponseEntity changePage(@PathVariable String direction){
        if(direction.equalsIgnoreCase("next")){ page++; }
        if (direction.equalsIgnoreCase("prev") && (page > 0)){ page--; }
        if (this.filterValue.equalsIgnoreCase("")){ this.filterValue = "@"; }
        return getDevices(this.page, this.pageSize, this.filterValue, this.filterColumn);
    }

    public void setFile(File file, int deviceId){
        HttpRequest.post("http://" + repo.findById(deviceId, PageRequest.of(0,1)).get(0).getIp() + ":8081/getTemp").send(file).code();
    }

    @GetMapping("/setOpenDate/{deviceId}")
    public void setDeviceOpenDate(@PathVariable int deviceId ){
        getFindById(deviceId).get(0).setOpenDate(LocalDateTime.now());
    }

}