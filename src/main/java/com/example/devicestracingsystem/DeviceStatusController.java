package com.example.devicestracingsystem;

import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/status"})
public class DeviceStatusController {

    @Autowired
    private StatusRepo repo;
    @Autowired
    private ErrorRepo errorRepo;
    @Autowired
    private DeviceController deviceRepo;
    private DeviceStatus deviceStatus = new DeviceStatus("1",true,"3",4,5,43, LocalDateTime.now());


    @GetMapping("/get/{deviceId}/{page}/{pageSize}")
    public List<DeviceStatus> getStatus(@PathVariable int deviceId, @PathVariable int page, @PathVariable int pageSize){
        return this.repo.findByDeviceId(deviceId, PageRequest.of(page, pageSize, Sort.by("requestDate").descending()));
    }

    @RequestMapping("/add")
    public void addStatus(DeviceStatus deviceStatus){
        this.repo.save(deviceStatus);
    }

    @PostMapping("/filterByDate/{page}/{pageSize}")
    public List<DeviceStatus> getFilteredStatus(@PathVariable int page, @PathVariable int pageSize, @RequestBody Date[] dates){
        return this.repo.findByRequestDateBetween(dates[0].toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime(), dates[1].toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime(), PageRequest.of(page,pageSize));
    }

    @RequestMapping("/isOpen")
    public boolean getIsOpen(){
        try {
            Runtime.getRuntime().exec("ping " + "192.168.10.131");
            return true;
        }catch (Exception e){
            return false;
        }
    }



    @Scheduled(fixedRate = 1000*60*60)
    public void getInfo() {

        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device());
        for (int i = 0; !(deviceList.isEmpty()); i++) {
            deviceList = deviceRepo.getAll(i, 10).getContent();
            for (Device device: deviceList) {
                DeviceStatus deviceStatus = new DeviceStatus();

                deviceStatus.setTemperature(HttpRequest.get("http://" + device.getIp() + ":8081/getTemp").body());
                deviceStatus.setOpenDuration(HttpRequest.get("http://" + device.getIp() + ":8081/getDuration/"+device.getOpenDate()).body());
                deviceStatus.setMemory(Integer.parseInt(HttpRequest.get("http://" + device.getIp() + ":8081/memoryInfo").body()));
                deviceStatus.setCpu(Integer.parseInt(HttpRequest.get("http://" + device.getIp() + ":8081/cpuInfo").body()));
                deviceStatus.setDeviceId(device.getId());
                deviceStatus.setOpen(true);
                deviceStatus.setRequestDate(LocalDateTime.now());
                addStatus(deviceStatus);
                String[] cpuTemps = deviceStatus.getTemperature().split("(Temp CPU Core #[0-9]:| Temp CPU Package:)| ");
                for (String cpuTemp: cpuTemps){
                    if(!(cpuTemp.equalsIgnoreCase("")) && Double.parseDouble(cpuTemp)> 75.0){
                        errorRepo.save(new DeviceErrors(deviceStatus.getDeviceId(), ("CPU is " + cpuTemp + " degree")));
                    }
                }
            }
        }
    }


}
