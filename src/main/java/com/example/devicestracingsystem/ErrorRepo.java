package com.example.devicestracingsystem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.Errors;

public interface ErrorRepo extends CrudRepository<DeviceErrors, Integer> {


}
