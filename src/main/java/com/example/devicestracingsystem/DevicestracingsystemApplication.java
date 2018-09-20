package com.example.devicestracingsystem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Timer;


@SpringBootApplication
@EnableScheduling
public class DevicestracingsystemApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DevicestracingsystemApplication.class, args);
	}
}
